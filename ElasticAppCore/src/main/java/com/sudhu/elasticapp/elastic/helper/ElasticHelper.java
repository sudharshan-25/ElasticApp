package com.sudhu.elasticapp.elastic.helper;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.elastic.domain.SearchCriteria;
import com.sudhu.elasticapp.elastic.domain.SearchField;
import com.sudhu.elasticapp.elastic.exception.ElasticException;
import com.sudhu.elasticapp.module.domain.ColumnMappingVO;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.service.DomainService;

@Service
public class ElasticHelper {

	@Autowired
	private DomainService domainService;

	private TransportClient client;

	@PostConstruct
	public void init() throws ElasticException {
		try {
			Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	@PreDestroy
	public void destroyConnection() throws ElasticException {
		try {
			client.close();
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	public void createType(String indexName, String typeName, List<ColumnMappingVO> columnMappings)
			throws ElasticException {
		try {
			CreateIndexResponse indexResponse = client.admin().indices().create(new CreateIndexRequest(indexName))
					.actionGet();
			if (indexResponse.isAcknowledged()) {

				Map<String, DomainVO> analyserMap = domainService.getAnalyserMap();
				Map<String, DomainVO> dataTypeMap = domainService.getDataTypeMap();

				XContentBuilder builder = jsonBuilder().prettyPrint();

				builder.startObject();
				builder.startObject(typeName);
				builder.startObject("properties");
				String analyzer = null;
				String dataType = null;
				for (ColumnMappingVO column : columnMappings) {
					builder.startObject(column.getColumnName());

					analyzer = analyserMap.get(column.getQueryAnalyserType()).getKey();
					dataType = dataTypeMap.get(column.getQueryDataType()).getKey();

					if (column.getAnalysed()) {
						builder.field("index", "analyzed");
						if ("string".equalsIgnoreCase(dataType)) {
							builder.field("analyzer", analyzer);
						}
						if ("date".equalsIgnoreCase(dataType)) {
							builder.field("format", "yyyy-MM-dd HH:mm:ss");
						}

					} else {
						builder.field("index", "not_analyzed");
					}
					builder.field("type", dataType);
					builder.endObject();
				}
				builder.endObject();
				builder.endObject();
				builder.endObject();

				PutMappingRequestBuilder putMappingbuilder = client.admin().indices().preparePutMapping(indexName);

				PutMappingResponse putMappingResponse = putMappingbuilder.setType(typeName).setSource(builder).execute()
						.actionGet();
				System.out.println(putMappingResponse.isAcknowledged());

			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	public void deleteType(String indexName, String typeName) throws ElasticException {
		try {

			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				DeleteIndexRequestBuilder deleteIndexRequestBuilder = client.admin().indices().prepareDelete(indexName);
				deleteIndexRequestBuilder.execute().actionGet();
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	public void insertBulkData(List<Map<String, Object>> dataToInsert, String indexName, String typeName,
			String idColumn) throws ElasticException {

		try {
			BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

			for (Map<String, Object> dataRow : dataToInsert) {
				bulkRequestBuilder.add(client.prepareIndex(indexName, typeName).setSource(dataRow)
						.setId(dataRow.get(idColumn).toString()));
			}

			BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				throw new ElasticException(bulkResponse.buildFailureMessage());
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	public List<Map<String, String>> doSearchOperation(String indexName, String typeName, SearchCriteria searchCriteria)
			throws ElasticException {
		List<Map<String, String>> data = new ArrayList<>();
		try {

			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName).setTypes(typeName);
				BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
				if (searchCriteria.getFields().isEmpty()) {
					searchRequestBuilder.addFields("_source");
				} else {
					searchCriteria.getFields().forEach(searchRequestBuilder::addField);
				}
				for (SearchField field : searchCriteria.getFewMatch()) {
					if (null != field.getValue()) {
						queryBuilder.should(QueryBuilders.wildcardQuery(field.getName(), "*" + field.getValue() + "*"));
					} else if (null != field.getFrom() && null == field.getTo()) {
						queryBuilder.should(QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()));
					} else if (null == field.getFrom() && null != field.getTo()) {
						queryBuilder.should(QueryBuilders.rangeQuery(field.getName()).to(field.getTo()));
					} else if (null != field.getFrom() && null != field.getTo()) {
						queryBuilder.should(
								QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()).to(field.getTo()));
					}

				}

				for (SearchField field : searchCriteria.getAllMatch()) {
					if (null != field.getValue()) {
						queryBuilder.must(QueryBuilders.wildcardQuery(field.getName(), "*" + field.getValue() + "*"));
					} else if (null != field.getFrom() && null == field.getTo()) {
						queryBuilder.must(QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()));
					} else if (null == field.getFrom() && null != field.getTo()) {
						queryBuilder.must(QueryBuilders.rangeQuery(field.getName()).to(field.getTo()));
					} else if (null != field.getFrom() && null != field.getTo()) {
						queryBuilder.must(
								QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()).to(field.getTo()));
					}

				}

				for (SearchField field : searchCriteria.getNoMatch()) {
					if (null != field.getValue()) {
						queryBuilder
								.mustNot(QueryBuilders.wildcardQuery(field.getName(), "*" + field.getValue() + "*"));
					} else if (null != field.getFrom() && null == field.getTo()) {
						queryBuilder.mustNot(QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()));
					} else if (null == field.getFrom() && null != field.getTo()) {
						queryBuilder.mustNot(QueryBuilders.rangeQuery(field.getName()).to(field.getTo()));
					} else if (null != field.getFrom() && null != field.getTo()) {
						queryBuilder.mustNot(
								QueryBuilders.rangeQuery(field.getName()).from(field.getFrom()).to(field.getTo()));
					}

				}

				searchRequestBuilder.setQuery(queryBuilder);
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				Map<String, String> results = null;
				for (SearchHit hit : searchResponse.getHits().getHits()) {
					results = new HashMap<>();
					for (String key : hit.getSource().keySet()) {
						results.put(key, hit.getSource().get(key).toString());
					}
					data.add(results);
				}

			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return data;
	}

	public static void main(String[] args) throws ElasticException {
		ElasticHelper elasticHelper = new ElasticHelper();
		elasticHelper.init();

		SearchCriteria searchCriteria = new SearchCriteria();
		List<Map<String, String>> data = elasticHelper.doSearchOperation("testone", "testone", searchCriteria);
		data.forEach(System.out::println);
		elasticHelper.destroyConnection();
	}

}
