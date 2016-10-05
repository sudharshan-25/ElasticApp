package com.sudhu.elasticapp.generic.dao.helper;

import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sudha on 01-Oct-16.
 */
public class DBConnectionHelper {

    private static DBConnectionHelper instance;

    private final Logger LOGGER = LogManager.getLogger(DBConnectionHelper.class);

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

    public boolean checkConnection(DBConnectionVO connectionVO){
        boolean isConnectionAvailable  = false;
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
            isConnectionAvailable = true;
        }catch (Exception ex){
            isConnectionAvailable = false;
        }finally {
            if (null != connection){
                try {
                    connection.close();
                }catch (SQLException e){

                }
            }
        }


        return isConnectionAvailable;
    }

}
