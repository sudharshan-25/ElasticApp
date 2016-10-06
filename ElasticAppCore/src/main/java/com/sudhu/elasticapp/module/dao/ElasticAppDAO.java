package com.sudhu.elasticapp.module.dao;

import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.UserVO;

import java.util.List;

/**
 * Created by sudha on 05-Oct-16.
 */
public interface ElasticAppDAO {
    List<DomainVO> getApplciationList();

    List<DomainVO> getQueryTypes();

    List<DomainVO> getStatusList();

    List<DomainVO> getFrequencyList();

    List<DomainVO> getDBTypeList();

    UserVO getUser(String userPin);
}
