package com.sudhu.elasticapp.module.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.sudhu.elasticapp.module.domain.ColumnMappingVO;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.ModuleVO;
import com.sudhu.elasticapp.module.domain.RequestHeaderVO;
import com.sudhu.elasticapp.module.domain.RequestVO;
import com.sudhu.elasticapp.module.domain.UserVO;

/**
 * Created by sudha on 05-Oct-16.
 */
@Repository
public class ElasticAppDAOImpl implements ElasticAppDAO {

	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;

	@Override
	public List<DomainVO> getApplicationList() {

		String sqlString = " SELECT app_id, app_key, app_name from t_mapplication";

		List<DomainVO> applicationList = jdbcOperations.query(sqlString, new DomainVORowMapper());

		return applicationList;
	}

	@Override
	public List<DomainVO> getQueryTypes() {
		String sqlString = " SELECT query_type_id, query_type_key, query_type_desc from t_mquery_types";

		List<DomainVO> queryTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());

		return queryTypeList;
	}

	@Override
	public List<DomainVO> getStatusList() {
		String sqlString = " SELECT status_id, status_key, status_desc from t_mstatus where status_id != :statusid ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("statusid", 4);
		List<DomainVO> statusList = jdbcOperations.query(sqlString, map, new DomainVORowMapper());
		return statusList;
	}

	@Override
	public List<DomainVO> getFrequencyList() {
		String sqlString = " SELECT freq_id, freq_key, freq_desc from t_mfrequency ";
		List<DomainVO> frequencyList = jdbcOperations.query(sqlString, new DomainVORowMapper());
		return frequencyList;
	}

	@Override
	public List<DomainVO> getDBTypeList() {
		String sqlString = " SELECT db_id, db_driver, db_desc from t_mdbconfig ";
		List<DomainVO> dbTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());
		return dbTypeList;
	}

	@Override
	public List<DomainVO> getDataTypeList() {
		String sqlString = " SELECT data_id, data_key, data_desc from t_mdatatype ";
		List<DomainVO> dbTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());
		return dbTypeList;
	}

	@Override
	public List<DomainVO> getAnalyserList() {
		String sqlString = " SELECT analyser_id,analyser_key,analyser_desc FROM t_manalyser_type ";
		List<DomainVO> dbTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());
		return dbTypeList;
	}

	@Override
	public UserVO getUser(String userPin) {
		String sqlString = " SELECT user_id, user_name, user_pin, user_email, dateOfBirth, dateOfJoining, lastLogin FROM t_musers where user_pin = :userpin ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("userpin", userPin);

		UserVO userVO = jdbcOperations.query(sqlString, map, new ResultSetExtractor<UserVO>() {
			@Override
			public UserVO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				UserVO userVO = null;
				if (resultSet.next()) {
					userVO = new UserVO();
					userVO.setUserID(resultSet.getInt(1));
					userVO.setUserName(resultSet.getString(2));
					userVO.setUserPin(resultSet.getString(3));
					userVO.setUserEmail(resultSet.getString(4));
					userVO.setDateofBirth(resultSet.getDate(5).toLocalDate());
					userVO.setDateOfJoining(resultSet.getDate(6).toLocalDate());
				}
				return userVO;
			}
		});

		return userVO;
	}

	@Override
	public boolean checkUniqueQueryName(String queryName) {

		String sqlString = " SELECT query_name from t_mquery where query_name = :queryName ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("queryName", queryName);
		boolean uniqueName = jdbcOperations.query(sqlString, map, new ResultSetExtractor<Boolean>() {

			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				return !rs.next();
			}
		});

		return uniqueName;
	}

	public void saveQueryRequest(RequestVO requestVO) {

		String sqlString = "";
		MapSqlParameterSource map = new MapSqlParameterSource();
		if (requestVO.getRequestId() == null || requestVO.getRequestId().trim().isEmpty()) {
			sqlString = "SELECT max(query_id) from t_mquery";

			int requestId = jdbcOperations.query(sqlString, new ResultSetExtractor<Integer>() {

				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					return rs.next() ? rs.getInt(1) : 0;
				}
			});
			requestVO.setRequestId(" " + (requestId + 1));
			sqlString = " INSERT INTO t_mquery "
					+ "(query_id, query_name, query_app_id, query_type_id, query_string, query_freq_id, query_status_id, query_app_token, id_column, admin_email,  "
					+ " db_server, db_port, db_name, db_username, db_password, db_connection_url, db_id, added_by, added_on  )"
					+ " values (:query_id, :query_name, :query_app_id, :query_type_id, :query_string, :query_freq_id, "
					+ " :query_status_id, :query_app_token, :id_column, :admin_email, :db_server, :db_port, :db_name, :db_username, :db_password, "
					+ " :db_connection_url, :db_id, :added_by, :added_on  )";
			map.addValue("added_on", Calendar.getInstance().getTime());
			map.addValue("added_by", "1");
			String appToken = requestVO.getQueryName() + ":" + requestId;
			requestVO.setAppToken(appToken);
			map.addValue("query_app_token", appToken);
			requestVO.setStatusId("1");
		} else {
			sqlString = " UPDATE t_mquery set " + " query_app_id = :query_app_id,"
					+ " query_type_id = :query_type_id, query_string = :query_string, query_freq_id = :query_freq_id,"
					+ " id_column = :id_column, admin_email = :admin_email, "
					+ " query_status_id = :query_status_id, db_server = :db_server, db_port = :db_port, db_name = :db_name,"
					+ " db_username = :db_username, db_password= :db_password, db_connection_url = :db_connection_url,"
					+ " db_id = :db_id, modified_by = :modified_by, modified_on = :modified_on where query_id = :query_id ";
			map.addValue("modified_on", Calendar.getInstance().getTime());
			map.addValue("modified_by", "1");
		}

		map.addValue("query_id", requestVO.getRequestId());
		map.addValue("query_name", requestVO.getQueryName());
		map.addValue("query_app_id", requestVO.getProjectId());
		map.addValue("query_type_id", requestVO.getQueryType());
		map.addValue("query_string", requestVO.getQuery());
		map.addValue("query_freq_id", requestVO.getUpdateFreq());
		map.addValue("query_status_id", requestVO.getStatusId());
		map.addValue("id_column", requestVO.getIdColumn());
		map.addValue("admin_email", requestVO.getEmailNotification());
		map.addValue("db_server", requestVO.getModuleVO().getDbServerName());
		map.addValue("db_port", requestVO.getModuleVO().getDbPortNumber());
		map.addValue("db_name", requestVO.getModuleVO().getDataBaseName());
		map.addValue("db_username", requestVO.getModuleVO().getDbUserName());
		map.addValue("db_id", requestVO.getModuleVO().getDatabaseVendorId());
		map.addValue("db_password", requestVO.getModuleVO().getDbPassword().getBytes());
		map.addValue("db_connection_url", requestVO.getModuleVO().getDbConnectionURL());

		jdbcOperations.update(sqlString, map);

		String deleteMapping = " DELETE from t_mcolumn_mapping where query_id = :query_id ";
		map = new MapSqlParameterSource();
		map.addValue("query_id", requestVO.getRequestId());
		jdbcOperations.update(deleteMapping, map);

		List<ColumnMappingVO> columnMappingList = requestVO.getColumnMapping();
		MapSqlParameterSource[] array = new MapSqlParameterSource[columnMappingList.size()];
		int i = 0;
		sqlString = " INSERT INTO t_mcolumn_mapping(query_id,column_name,column_data_id, column_analysed, column_analyser_type) values(:query_id,:column_name,:column_data_id, :column_analysed, :column_analyser_type)";
		for (ColumnMappingVO column : columnMappingList) {
			column.setQueryId(requestVO.getRequestId());
			MapSqlParameterSource mapSql = new MapSqlParameterSource();
			mapSql.addValue("query_id", column.getQueryId());
			mapSql.addValue("column_name", column.getColumnName());
			mapSql.addValue("column_data_id", column.getQueryDataType());
			mapSql.addValue("column_analysed", column.getAnalysed());
			mapSql.addValue("column_analyser_type",
					column.getQueryAnalyserType().trim().equals("") ? null : column.getQueryAnalyserType());
			array[i++] = mapSql;
		}
		jdbcOperations.batchUpdate(sqlString, array);

	}

	@Override
	public List<RequestHeaderVO> searchResults(Map<String, String> searchCriteria) {

		StringBuilder builder = new StringBuilder();
		builder.append(
				"select t_mquery.query_id, t_mquery.query_name,  t_mquery.query_app_token, t_mquery.db_connection_url, ");
		builder.append(
				"t_mapplication.app_name, t_mquery_types.query_type_desc, t_mfrequency.freq_desc, t_mstatus.status_desc ");
		builder.append("from t_mquery ");
		builder.append("inner join t_mquery_types on t_mquery_types.query_type_id = t_mquery.query_type_id ");
		builder.append("inner join t_mapplication on t_mapplication.app_id = t_mquery.query_app_id ");
		builder.append("inner join t_mdbconfig on t_mdbconfig.db_id = t_mquery.db_id ");
		builder.append("inner join t_mfrequency on t_mfrequency.freq_id = t_mquery.query_freq_id ");
		builder.append("inner join t_mstatus on t_mstatus.status_id = t_mquery.query_status_id ");

		if (!searchCriteria.isEmpty()) {
			builder.append(" where ");
		}
		MapSqlParameterSource map = new MapSqlParameterSource();

		for (String key : searchCriteria.keySet()) {
			String value = searchCriteria.get(key);
			if (value.matches("\\d+")) {
				builder.append("t_mquery.").append(key).append(" = :").append(key);
			} else {
				builder.append("t_mquery.").append(key).append(" like :").append(key);
			}
			builder.append(" and ");
			map.addValue(key, value);
		}

		String sqlString = builder.substring(0, builder.lastIndexOf(" and "));

		List<RequestHeaderVO> searchResults = jdbcOperations.query(sqlString, map, new RowMapper<RequestHeaderVO>() {

			@Override
			public RequestHeaderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				RequestHeaderVO searchRequest = new RequestHeaderVO();
				searchRequest.setQueryId(rs.getString(1));
				searchRequest.setQueryName(rs.getString(2));
				searchRequest.setAppToken(rs.getString(3));
				searchRequest.setConnectionURL(rs.getString(4));
				searchRequest.setQueryApplication(rs.getString(5));
				searchRequest.setQueryType(rs.getString(6));
				searchRequest.setQueryFrequency(rs.getString(7));
				searchRequest.setQueryStatus(rs.getString(8));
				return searchRequest;
			}

		});

		return searchResults;
	}

	@Override
	public RequestVO getRequest(int requestId) {

		String sqlString = "SELECT query_id, query_name, query_app_id, query_type_id, query_string, "
				+ " query_freq_id, query_status_id, db_server, db_port, db_name, db_password, db_username,"
				+ " db_id, query_app_token, id_column, admin_email " + " FROM t_mquery WHERE query_id = :query_id";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_id", requestId);

		RequestVO requestVO = jdbcOperations.query(sqlString, map, new ResultSetExtractor<RequestVO>() {

			@Override
			public RequestVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				RequestVO requestVO = null;
				if (rs.next()) {
					requestVO = new RequestVO();
					requestVO.setRequestId(rs.getString(1));
					requestVO.setQueryName(rs.getString(2));
					requestVO.setProjectId(rs.getString(3));
					requestVO.setQueryType(rs.getString(4));
					requestVO.setQuery(rs.getString(5));
					requestVO.setUpdateFreq(rs.getString(6));
					requestVO.setStatusId(rs.getString(7));
					ModuleVO moduleVO = new ModuleVO();
					moduleVO.setDbServerName(rs.getString(8));
					moduleVO.setDbPortNumber(rs.getInt(9));
					moduleVO.setDataBaseName(rs.getString(10));
					try {
						moduleVO.setDbPassword(new String(rs.getBytes(11), "UTF-8"));
					} catch (Exception e) {
						moduleVO.setDbPassword(null);
					}
					moduleVO.setDbUserName(rs.getString(12));
					moduleVO.setDatabaseVendorId(rs.getString(13));
					requestVO.setAppToken(rs.getString(14));
					requestVO.setIdColumn(rs.getString(15));
					requestVO.setEmailNotification(rs.getString(16));
					requestVO.setModuleVO(moduleVO);
				}
				return requestVO;
			}
		});

		sqlString = " SELECT query_id,column_name,column_data_id, column_analysed, column_analyser_type from t_mcolumn_mapping where query_id = :query_id ";
		map = new MapSqlParameterSource();
		map.addValue("query_id", requestId);
		List<ColumnMappingVO> columnMappingList = jdbcOperations.query(sqlString, map,
				new RowMapper<ColumnMappingVO>() {

					@Override
					public ColumnMappingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						ColumnMappingVO mappingVO = new ColumnMappingVO();
						mappingVO.setQueryId(rs.getString(1));
						mappingVO.setColumnName(rs.getString(2));
						mappingVO.setQueryDataType(rs.getString(3));
						mappingVO.setAnalysed(rs.getBoolean(4));
						mappingVO.setQueryAnalyserType(rs.getString(5));
						return mappingVO;
					}

				});
		requestVO.setColumnMapping(columnMappingList);

		return requestVO;
	}

	@Override
	public List<RequestVO> getRequestsForFrequency(String frequencyId) {

		String sqlString = "SELECT query_id, query_name, query_app_id, query_type_id, query_string, "
				+ " query_freq_id, query_status_id, db_server, db_port, db_name, db_password, db_username,"
				+ " db_id, query_app_token, id_column, admin_email "
				+ " FROM t_mquery WHERE query_freq_id = :query_freq_id and query_status_id = :query_status_id";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_freq_id", frequencyId);
		map.addValue("query_status_id", 1);

		List<RequestVO> requestList = jdbcOperations.query(sqlString, map, new RowMapper<RequestVO>() {

			@Override
			public RequestVO mapRow(ResultSet rs, int row) throws SQLException, DataAccessException {
				RequestVO requestVO = null;
				requestVO = new RequestVO();
				requestVO.setRequestId(rs.getString(1));
				requestVO.setQueryName(rs.getString(2));
				requestVO.setProjectId(rs.getString(3));
				requestVO.setQueryType(rs.getString(4));
				requestVO.setQuery(rs.getString(5));
				requestVO.setUpdateFreq(rs.getString(6));
				requestVO.setStatusId(rs.getString(7));
				ModuleVO moduleVO = new ModuleVO();
				moduleVO.setDbServerName(rs.getString(8));
				moduleVO.setDbPortNumber(rs.getInt(9));
				moduleVO.setDataBaseName(rs.getString(10));
				try {
					moduleVO.setDbPassword(new String(rs.getBytes(11), "UTF-8"));
				} catch (Exception e) {
					moduleVO.setDbPassword(null);
				}
				moduleVO.setDbUserName(rs.getString(12));
				moduleVO.setDatabaseVendorId(rs.getString(13));
				requestVO.setAppToken(rs.getString(14));
				requestVO.setIdColumn(rs.getString(15));
				requestVO.setEmailNotification(rs.getString(16));
				requestVO.setModuleVO(moduleVO);

				return requestVO;
			}
		});

		sqlString = " SELECT query_id,column_name,column_data_id, column_analysed, column_analyser_type from t_mcolumn_mapping where query_id = :query_id ";
		for (RequestVO requestVO : requestList) {
			map = new MapSqlParameterSource();
			map.addValue("query_id", requestVO.getRequestId());
			List<ColumnMappingVO> columnMappingList = jdbcOperations.query(sqlString, map,
					new RowMapper<ColumnMappingVO>() {

						@Override
						public ColumnMappingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
							ColumnMappingVO mappingVO = new ColumnMappingVO();
							mappingVO.setQueryId(rs.getString(1));
							mappingVO.setColumnName(rs.getString(2));
							mappingVO.setQueryDataType(rs.getString(3));
							mappingVO.setAnalysed(rs.getBoolean(4));
							mappingVO.setQueryAnalyserType(rs.getString(5));
							return mappingVO;
						}

					});
			requestVO.setColumnMapping(columnMappingList);

		}

		return requestList;
	}

	private static class DomainVORowMapper implements RowMapper<DomainVO> {
		@Override
		public DomainVO mapRow(ResultSet resultSet, int i) throws SQLException {
			DomainVO domainVO = new DomainVO();
			domainVO.setId(resultSet.getString(1));
			domainVO.setKey(resultSet.getString(2));
			domainVO.setValue(resultSet.getString(3));
			return domainVO;
		}
	}

	@Override
	public Timestamp getLastUpdatedDate(String queryId) {
		String sql = "SELECT last_updated_time from t_dquery_update where query_id = :query_id ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_id", queryId);
		Timestamp timestamp = jdbcOperations.query(sql, map, new ResultSetExtractor<Timestamp>() {

			@Override
			public Timestamp extractData(ResultSet rs) throws SQLException, DataAccessException {
				long date = new Date().getTime();
				return rs.next() ? rs.getTimestamp(1) : new Timestamp(date);
			}
		});
		return timestamp;

	}

	@Override
	public void updatedLastDate(String queryId, int currentSequence) {
		String sql = "UPDATE t_dquery_update set current_index = :current_index, last_updated_time = :last_updated_time where query_id = :query_id ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_id", queryId);
		map.addValue("current_index", currentSequence);
		map.addValue("last_updated_time", new Date());
		jdbcOperations.update(sql, map);
	}

	@Override
	public void insertLastDate(String queryId, int currentSequence) {
		String sql = "INSERT INTO t_dquery_update(query_id,current_index,last_updated_time ) values (:query_id, :current_index, :last_updated_time)";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_id", queryId);
		map.addValue("current_index", currentSequence);
		map.addValue("last_updated_time", new Date());
		jdbcOperations.update(sql, map);
	}

	@Override
	public int getCurrentSequenceId(String queryId) {
		int currentSeqId = 0;
		String sql = " select current_index from t_dquery_update where query_id = :query_id ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("query_id", queryId);
		currentSeqId = jdbcOperations.query(sql, map, (rs) -> {
			return rs.next() ? rs.getInt(1) : 0;
		});

		return currentSeqId;
	}

	@Override
	public String getCurrentIndexForToken(String appToken) {
		String indexName = null;
		String sql = " select concat(query_name , \"_\" , current_index) from t_dquery_update inner join t_mquery on t_mquery.query_id = t_dquery_update.query_id "
				+ " where t_mquery.query_app_token = :appToken ";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("appToken", appToken);
		indexName = jdbcOperations.query(sql, map, (rs) -> {
			return rs.next() ? rs.getString(1) : null;
		});

		return indexName;
	}

}
