package com.sudhu.elasticapp.module.dao;

import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by sudha on 05-Oct-16.
 */
@Repository
public class ElasticAppDAOImpl implements ElasticAppDAO {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;


    @Override
    public List<DomainVO> getApplciationList(){

        String sqlString = " SELECT app_id, app_key, app_name from t_mapplication";

        List<DomainVO> applicationList = jdbcOperations.query(sqlString, new DomainVORowMapper());

        return applicationList;
    }

    @Override
    public List<DomainVO> getQueryTypes(){
        String sqlString = " SELECT query_type_id, query_type_key, query_type_desc from t_mquery_types";

        List<DomainVO> queryTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());

        return queryTypeList;
    }

    @Override
    public List<DomainVO> getStatusList(){
        String sqlString = " SELECT status_id, status_key, status_desc from t_mstatus where status_id != :statusid ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("statusid", 4);
        List<DomainVO> statusList = jdbcOperations.query(sqlString,map, new DomainVORowMapper());
        return statusList;
    }

    @Override
    public List<DomainVO> getFrequencyList(){
        String sqlString = " SELECT freq_id, freq_key, freq_desc from t_mfrequency ";
        List<DomainVO> frequencyList = jdbcOperations.query(sqlString, new DomainVORowMapper());
        return frequencyList;
    }

    @Override
    public List<DomainVO> getDBTypeList(){
        String sqlString = " SELECT db_id, db_driver, db_desc from t_mdbconfig ";
        List<DomainVO> dbTypeList = jdbcOperations.query(sqlString, new DomainVORowMapper());
        return dbTypeList;
    }

    @Override
    public UserVO getUser(String userPin){
        String sqlString = " SELECT user_id, user_name, user_pin, user_email, dateOfBirth, dateOfJoining, lastLogin FROM t_musers where user_pin = :userpin ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("userpin", userPin);

        UserVO userVO = jdbcOperations.query(sqlString, map, new ResultSetExtractor<UserVO>() {
            @Override
            public UserVO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                UserVO userVO = null;
                if(resultSet.next()){
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


    private static class DomainVORowMapper implements RowMapper<DomainVO> {
        @Override
        public DomainVO mapRow(ResultSet resultSet, int i) throws SQLException {
            DomainVO domainVO = new DomainVO();
            domainVO.setId(resultSet.getInt(1));
            domainVO.setKey(resultSet.getString(2));
            domainVO.setValue(resultSet.getString(3));
            return domainVO;
        }
    }
}
