package com.sudhu.elasticapp.elastic.helper;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
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
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.common.helper.GeneralUtils;
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

	public boolean deleteType(String indexName) throws ElasticException {
		boolean acknowledged = false;
		try {

			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				DeleteIndexRequestBuilder deleteIndexRequestBuilder = client.admin().indices().prepareDelete(indexName);
				DeleteIndexResponse deleteIndexResponse = deleteIndexRequestBuilder.execute().actionGet();
				acknowledged = deleteIndexResponse.isAcknowledged();
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return acknowledged;
	}

	public void insertBulkData(List<Map<String, Object>> dataToInsert, String indexName, String typeName,
			String idColumn) throws ElasticException {

		try {
			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
				idColumn = idColumn.toLowerCase();
				for (Map<String, Object> dataRow : dataToInsert) {
					bulkRequestBuilder.add(client.prepareIndex(indexName, typeName).setSource(dataRow)
							.setId(dataRow.get(idColumn).toString()));
				}

				BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
				if (bulkResponse.hasFailures()) {
					throw new ElasticException(bulkResponse.buildFailureMessage());
				}
			} else {
				throw new ElasticException("No Such Index Exists");
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
	}

	public boolean createAlias(String indexName, String aliasName) throws ElasticException {
		boolean acknowledged = false;
		try {
			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				IndicesAliasesRequestBuilder aliasBuilder = client.admin().indices().prepareAliases()
						.addAlias(indexName, aliasName);
				IndicesAliasesResponse aliasesResponse = aliasBuilder.execute().actionGet();
				acknowledged = aliasesResponse.isAcknowledged();
			} else {
				throw new ElasticException("No Such Index Exists");
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return acknowledged;
	}

	public boolean modifyAlias(String indexName, String newIndexName, String aliasName) throws ElasticException {
		boolean acknowledged = false;
		try {
			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				IndicesAliasesRequestBuilder aliasBuilder = client.admin().indices().prepareAliases()
						.removeAlias(indexName, aliasName).addAlias(newIndexName, aliasName);
				IndicesAliasesResponse aliasesResponse = aliasBuilder.execute().actionGet();
				acknowledged = aliasesResponse.isAcknowledged();
			} 
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return acknowledged;
	}

	public List<Map<String, String>> doAdvancedSearchOperation(String indexName, String typeName,
			SearchCriteria searchCriteria) throws ElasticException {
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

				String fieldName;
				String value;
				String from;
				String to;
				String operator;

				for (SearchField field : searchCriteria.getFewMatch()) {
					fieldName = field.getName();
					value = field.getValue();
					from = field.getFrom();
					to = field.getTo();
					operator = field.getOperator();
					if (GeneralUtils.isValidString(value) && !GeneralUtils.isValidString(operator)) {
						if (field.getWildcard()) {
							queryBuilder.should(QueryBuilders.queryStringQuery("*" + value + "*").defaultField(fieldName));
						} else {
							queryBuilder.should(QueryBuilders.queryStringQuery(value).defaultField(fieldName));
						}
					} else if (null != from && null == to) {
						queryBuilder.should(QueryBuilders.rangeQuery(fieldName).from(from));
					} else if (null == from && null != to) {
						queryBuilder.should(QueryBuilders.rangeQuery(fieldName).to(to));
					} else if (null != from && null != to) {
						queryBuilder.should(QueryBuilders.rangeQuery(fieldName).from(from).to(to));
					} else if (null != operator) {
						switch (operator) {
						case "gt":
							queryBuilder.should(QueryBuilders.rangeQuery(fieldName).gt(value));
							break;
						case "ge":
							queryBuilder.should(QueryBuilders.rangeQuery(fieldName).gte(value));
							break;
						case "lt":
							queryBuilder.should(QueryBuilders.rangeQuery(fieldName).lt(value));
							break;
						case "le":
							queryBuilder.should(QueryBuilders.rangeQuery(fieldName).lte(value));
							break;
						default:
							break;
						}
					}

				}

				for (SearchField field : searchCriteria.getAllMatch()) {

					fieldName = field.getName();
					value = field.getValue();
					from = field.getFrom();
					to = field.getTo();
					operator = field.getOperator();

					if (GeneralUtils.isValidString(value) && !GeneralUtils.isValidString(operator)) {
						if (field.getWildcard()) {
							queryBuilder.must(QueryBuilders.queryStringQuery("*" + value + "*").defaultField(fieldName));
						} else {
							queryBuilder.must(QueryBuilders.queryStringQuery(value).defaultField(fieldName));
						}
					} else if (null != from && null == to) {
						queryBuilder.must(QueryBuilders.rangeQuery(fieldName).from(from));
					} else if (null == from && null != to) {
						queryBuilder.must(QueryBuilders.rangeQuery(fieldName).to(to));
					} else if (null != from && null != to) {
						queryBuilder.must(QueryBuilders.rangeQuery(fieldName).from(from).to(to));
					} else if (null != operator) {
						switch (operator) {
						case "gt":
							queryBuilder.must(QueryBuilders.rangeQuery(fieldName).gt(value));
							break;
						case "ge":
							queryBuilder.must(QueryBuilders.rangeQuery(fieldName).gte(value));
							break;
						case "lt":
							queryBuilder.must(QueryBuilders.rangeQuery(fieldName).lt(value));
							break;
						case "le":
							queryBuilder.must(QueryBuilders.rangeQuery(fieldName).lte(value));
							break;
						default:
							break;
						}
					}

				}

				for (SearchField field : searchCriteria.getNoMatch()) {
					fieldName = field.getName();
					value = field.getValue();
					from = field.getFrom();
					to = field.getTo();
					operator = field.getOperator();

					if (GeneralUtils.isValidString(value) && !GeneralUtils.isValidString(operator)) {
						if (field.getWildcard()) {
							queryBuilder.mustNot(QueryBuilders.queryStringQuery("*" + value + "*").defaultField(fieldName));
						} else {
							queryBuilder.mustNot(QueryBuilders.queryStringQuery(value).defaultField(fieldName));
						}
					} else if (null != from && null == to) {
						queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).from(from));
					} else if (null == from && null != to) {
						queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).to(to));
					} else if (null != from && null != to) {
						queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).from(from).to(to));
					} else if (null != operator) {
						switch (operator) {
						case "gt":
							queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).gt(value));
							break;
						case "ge":
							queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).gte(value));
							break;
						case "lt":
							queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).lt(value));
							break;
						case "le":
							queryBuilder.mustNot(QueryBuilders.rangeQuery(fieldName).lte(value));
							break;
						default:
							break;
						}
					}

				}

				searchRequestBuilder.setQuery(queryBuilder);
				searchRequestBuilder.setSize(searchCriteria.getSize());
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				Map<String, String> results = null;
				for (SearchHit hit : searchResponse.getHits().getHits()) {
					results = new HashMap<>();

					if (searchCriteria.getFields().isEmpty()) {
						for (String key : hit.getSource().keySet()) {
							results.put(key, hit.getSource().get(key).toString());
						}
					} else {
						for (String key : hit.getFields().keySet()) {
							results.put(key, hit.getFields().get(key).getValue());
						}
					}

					data.add(results);
				}

			} else {
				throw new ElasticException("No Such Index Exists");
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return data;
	}

	public List<Map<String, String>> doSearchOperation(String indexName, String typeName, List<String> fields,
			String field, boolean wildcard) throws ElasticException {
		List<Map<String, String>> data = new ArrayList<>();
		try {

			IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).execute()
					.actionGet();
			if (existsResponse.isExists()) {
				SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName).setTypes(typeName);

				if (fields.isEmpty()) {
					searchRequestBuilder.addField("_source");
				} else {
					fields.forEach(searchRequestBuilder::addField);
				}
				QueryBuilder queryBuilder;
				if (wildcard) {
					queryBuilder = QueryBuilders.queryStringQuery("*" + field + "*");
				} else {
					queryBuilder = QueryBuilders.queryStringQuery(field);
				}
				searchRequestBuilder.setQuery(queryBuilder);
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				Map<String, String> results = null;
				for (SearchHit hit : searchResponse.getHits().getHits()) {
					results = new HashMap<>();

					if (fields.isEmpty()) {
						for (String key : hit.getSource().keySet()) {
							results.put(key, hit.getSource().get(key).toString());
						}
					} else {
						for (String key : hit.getFields().keySet()) {
							results.put(key, hit.getFields().get(key).getValue());
						}
					}

					data.add(results);
				}
			} else {
				throw new ElasticException("No Such Index Exists");
			}
		} catch (Exception e) {
			throw new ElasticException(e);
		}
		return data;
	}

	public static void main(String[] args) throws ElasticException {
		ElasticHelper elasticHelper = new ElasticHelper();
		elasticHelper.init();

		// SearchCriteria searchCriteria = new SearchCriteria();
		// List<Map<String, String>> data =
		// elasticHelper.doAdvancedSearchOperation("testone", "testone",
		// searchCriteria);
		List<Map<String, String>> data = elasticHelper.doSearchOperation("testone", "testone", Arrays.asList("_source"),
				"lewis", true);
		data.forEach(System.out::println);
		elasticHelper.destroyConnection();
	}

}
