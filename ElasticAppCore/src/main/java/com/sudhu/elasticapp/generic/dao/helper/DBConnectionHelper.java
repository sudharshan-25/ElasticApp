package com.sudhu.elasticapp.generic.dao.helper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import com.sudhu.elasticapp.generic.dao.exception.DBConnectionException;
import com.sudhu.elasticapp.module.domain.ModuleVO;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionHelper {

	private static DBConnectionHelper instance;

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
			this.closeConnection(null, null, connection);
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

			query = query.toLowerCase();

			if (query.contains("limit") && connectionVO.getDbType().equals(com.mysql.cj.jdbc.Driver.class)) {
				query = query.replaceAll("limit\\s*\\d+", " limit 1");
			} else if (connectionVO.getDbType().equals(com.microsoft.sqlserver.jdbc.SQLServerDriver.class)){
				query = query.replace("select", "select top 1 ");
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
			this.closeConnection(rs, statement, connection);
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
				connectionURL = "jdbc:sqlserver://";
				connectionVO.setDbType(SQLServerDriver.class);
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

	public List<Map<String, Object>> getDataFromTable(DBConnectionVO connectionVO, String query)
			throws DBConnectionException {

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
			query = query.toLowerCase();
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
			this.closeConnection(rs, statement, connection);
		}

		return tableData;

	}

	private void closeConnection(ResultSet rs, Statement statement, Connection connection) {
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

}
