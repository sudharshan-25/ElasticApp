package com.sudhu.elasticapp.module.service;

import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sudha on 05-Oct-16.
 */
@org.springframework.stereotype.Service
public class DomainService {

    @Autowired
    private ElasticAppDAO elasticAppDAO;


    public List<DomainVO> getApplciationList(){
        return elasticAppDAO.getApplciationList();
    }

    public List<DomainVO> getQueryTypes(){
        return elasticAppDAO.getQueryTypes();
    }

    public UserVO getUser(String userPin){
        return elasticAppDAO.getUser(userPin);
    }

}
