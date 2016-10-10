package com.sudhu.elasticapp.generic.dao.helper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    private DBConnectionHelper(){

    }

    private static synchronized void initialize(){
        if(null == instance){
            instance = new DBConnectionHelper();
        }
    }

    public static DBConnectionHelper getInstance(){
        if(null == instance){
            initialize();
        }
        return instance;
    }

    public void checkConnection(DBConnectionVO connectionVO) throws DBConnectionException{
        
        Connection connection = null;
        try {
            Driver driver = connectionVO.getDbType().newInstance();
            DriverManager.registerDriver(driver);
            String connectionString = connectionVO.getConnectionString();
            String userName = connectionVO.getUserName();
            String passWord = connectionVO.getPassword();

            if (null == userName){
                connection = DriverManager.getConnection(connectionString);
            }else{
                connection = DriverManager.getConnection(connectionString,userName,passWord);
            }
            
        }catch (Exception ex){
            throw new DBConnectionException(ex);
        }finally {
            if (null != connection){
                try {
                    connection.close();
                }catch (SQLException e){

                }
            }
        }
    }

    public DBConnectionVO getConnectionVO(ModuleVO moduleVO){
        DBConnectionVO connectionVO = new DBConnectionVO();

        if(null != moduleVO){
            int vendorID = moduleVO.getDatabaseVendorId();
            String connectionURL = "";
            if(vendorID==1){
                connectionURL = "jdbc:mysql://";
                connectionVO.setDbType(com.mysql.cj.jdbc.Driver.class);
            }else if (vendorID == 2){
                connectionURL = "jdbc:microsoft:sqlserver://";
                connectionVO.setDbType(null);
            }

            connectionURL += moduleVO.getDbServerName() + ":" + moduleVO.getDbPortNumber();

            if(vendorID==1){
                connectionURL += "/" + moduleVO.getDataBaseName();
                connectionURL += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                
            }else if (vendorID == 2){
                connectionURL += ";DataBaseName=" + moduleVO.getDataBaseName();
            }
            connectionVO.setConnectionString(connectionURL);
            connectionVO.setUserName(moduleVO.getDbUserName());
            connectionVO.setPassword(moduleVO.getDbPassword());

        }

        return connectionVO;
    }
}
