package com.sudhu.elasticapp.generic.dao.helper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import com.sudhu.elasticapp.generic.dao.exception.DBConnectionException;
import com.sudhu.elasticapp.module.domain.ModuleVO;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionHelper {

	private static DBConnectionHelper instance;

	public final Logger LOGGER = LogManager.getLogger(DBConnectionHelper.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	private DBConnectionHelper() {

	}

	private static synchronized void initialize() {
		if (null == instance) {
			instance = new DBConnectionHelper();
		}
	}

	public static DBConnectionHelper getInstance() {
		if (null == instance) {
			initialize();
		}
		return instance;
	}

	public void checkConnection(DBConnectionVO connectionVO) throws DBConnectionException {

		Connection connection = null;
		try {
			Driver driver = connectionVO.getDbType().newInstance();
			DriverManager.registerDriver(driver);
			String connectionString = connectionVO.getConnectionString();
			String userName = connectionVO.getUserName();
			String passWord = connectionVO.getPassword();

			if (null == userName) {
				connection = DriverManager.getConnection(connectionString);
			} else {
				connection = DriverManager.getConnection(connectionString, userName, passWord);
			}

		} catch (Exception ex) {
			throw new DBConnectionException(ex);
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	public Map<String, Integer> checkQuery(DBConnectionVO connectionVO, String query) throws DBConnectionException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		Map<String, Integer> columnDataType = new LinkedHashMap<>();
		try {
			Driver driver = connectionVO.getDbType().newInstance();
			DriverManager.registerDriver(driver);
			String connectionString = connectionVO.getConnectionString();
			String userName = connectionVO.getUserName();
			String passWord = connectionVO.getPassword();

			if (null == userName) {
				connection = DriverManager.getConnection(connectionString);
			} else {
				connection = DriverManager.getConnection(connectionString, userName, passWord);
			}

			if (query.contains("LIMIT")) {
				query = query.replaceAll("LIMIT\\s*\\d+", " LIMIT 1");
			} else {
				query = query.concat(" LIMIT 1");
			}

			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					columnDataType.put(rsmd.getColumnLabel(i + 1), rsmd.getColumnType(i + 1));
				}

			}

		} catch (Exception ex) {
			throw new DBConnectionException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException exception) {

				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException exception) {

				}
			}

			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
			}
		}

		return columnDataType;
	}

	public DBConnectionVO getConnectionVO(ModuleVO moduleVO) {
		DBConnectionVO connectionVO = new DBConnectionVO();

		if (null != moduleVO) {
			String vendorID = moduleVO.getDatabaseVendorId();
			String connectionURL = "";
			if ("1".equals(vendorID)) {
				connectionURL = "jdbc:mysql://";
				connectionVO.setDbType(com.mysql.cj.jdbc.Driver.class);
			} else if ("2".equals(vendorID)) {
				connectionURL = "jdbc:microsoft:sqlserver://";
				connectionVO.setDbType(null);
			}

			connectionURL += moduleVO.getDbServerName() + ":" + moduleVO.getDbPortNumber();

			if ("1".equals(vendorID)) {
				connectionURL += "/" + moduleVO.getDataBaseName();
				connectionURL += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			} else if ("2".equals(vendorID)) {
				connectionURL += ";DataBaseName=" + moduleVO.getDataBaseName();
			}
			connectionVO.setConnectionString(connectionURL);
			connectionVO.setUserName(moduleVO.getDbUserName());
			connectionVO.setPassword(moduleVO.getDbPassword());

		}

		return connectionVO;
	}

	public List<Map<String, Object>> getDataFromTable(DBConnectionVO connectionVO, String query,
			String lastUpdatedColumn, Timestamp lastUpdatedTime) throws DBConnectionException {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String, Object>> tableData = new ArrayList<>();
		Map<String, Object> rowData = null;
		try {
			Driver driver = connectionVO.getDbType().newInstance();
			DriverManager.registerDriver(driver);
			String connectionString = connectionVO.getConnectionString();
			String userName = connectionVO.getUserName();
			String passWord = connectionVO.getPassword();

			if (null == userName) {
				connection = DriverManager.getConnection(connectionString);
			} else {
				connection = DriverManager.getConnection(connectionString, userName, passWord);
			}

			statement = connection.createStatement();

			rs = statement.executeQuery(query);
			while (rs.next()) {
				rowData = new HashMap<>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 0; i < columnCount; i++) {

					int columnType = rsmd.getColumnType(i + 1);

					if (columnType == Types.DATE || columnType == Types.TIMESTAMP) {
						rowData.put(rsmd.getColumnLabel(i + 1), dateFormat.format(rs.getTimestamp(i + 1)));
					} else {
						rowData.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
					}
				}
				tableData.add(rowData);
			}

		} catch (Exception ex) {
			throw new DBConnectionException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException exception) {

				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException exception) {

				}
			}

			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
			}
		}

		return tableData;

	}

}
