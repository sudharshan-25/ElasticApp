package com.sudhu.elasticapp.module.service;

import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sudha on 06-Oct-16.
 */
@Service
public class ElasticAppService {

    @Autowired
    private ElasticAppDAO elasticAppDAO;

    public UserVO getUser(String userPin){
        return elasticAppDAO.getUser(userPin);
    }
}
