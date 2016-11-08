package com.sudhu.elasticapp.module.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.RequestHeaderVO;
import com.sudhu.elasticapp.module.domain.RequestVO;
import com.sudhu.elasticapp.module.domain.UserVO;

/**
 * Created by sudha on 06-Oct-16.
 */
@Service
public class ElasticAppService {

	@Autowired
	private ElasticAppDAO elasticAppDAO;

	public UserVO getUser(String userPin) {
		return elasticAppDAO.getUser(userPin);
	}

	public boolean checkUniqueQueryName(String queryName) {
		return elasticAppDAO.checkUniqueQueryName(queryName);
	}

	public void saveQueryRequest(RequestVO requestVO) {
		elasticAppDAO.saveQueryRequest(requestVO);
	}

	public List<RequestHeaderVO> searchResults(Map<String, String> searchCriteria) {
		return elasticAppDAO.searchResults(searchCriteria);
	}

	public RequestVO getRequest(int requestId) {
		return elasticAppDAO.getRequest(requestId);
	}
}
