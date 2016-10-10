package com.sudhu.elasticapp.module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.DomainVO;

/**
 * Created by sudha on 05-Oct-16.
 */
@Service
public class DomainService {

    @Autowired
    private ElasticAppDAO elasticAppDAO;

    
    public List<DomainVO> getApplciationList(){
        return elasticAppDAO.getApplciationList();
    }

    
    public List<DomainVO> getQueryTypes(){
        return elasticAppDAO.getQueryTypes();
    }

    
    public List<DomainVO> getStatusList(){
        return elasticAppDAO.getStatusList();
    }

    
    public List<DomainVO> getFrequencyList(){
        return elasticAppDAO.getFrequencyList();
    }
    
    
    public List<DomainVO> getDBTypeList(){
        return elasticAppDAO.getDBTypeList();
    }
}
