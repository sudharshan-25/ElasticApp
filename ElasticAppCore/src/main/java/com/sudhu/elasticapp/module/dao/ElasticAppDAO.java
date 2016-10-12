package com.sudhu.elasticapp.module.dao;

import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.domain.RequestHeaderVO;
import com.sudhu.elasticapp.module.domain.RequestVO;
import com.sudhu.elasticapp.module.domain.UserVO;

import java.util.List;
import java.util.Map;

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

	boolean checkUniqueQueryName(String queryName);
	
	void saveQueryRequest(RequestVO requestVO) ;

	List<RequestHeaderVO> searchResults(Map<String, String> searchCriteria);

	RequestVO getRequest(int requestId);
}
