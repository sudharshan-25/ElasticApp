package com.sudhu.elasticapp.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<DomainVO> getApplicationList() {
		return elasticAppDAO.getApplicationList();
	}

	public List<DomainVO> getQueryTypes() {
		return elasticAppDAO.getQueryTypes();
	}

	public List<DomainVO> getStatusList() {
		return elasticAppDAO.getStatusList();
	}

	public List<DomainVO> getFrequencyList() {
		return elasticAppDAO.getFrequencyList();
	}

	public List<DomainVO> getDBTypeList() {
		return elasticAppDAO.getDBTypeList();
	}

	public List<DomainVO> getDataTypeList() {
		return elasticAppDAO.getDataTypeList();
	}

	public Map<String, DomainVO> getDataTypeMap() {
		List<DomainVO> dataTypeList = elasticAppDAO.getDataTypeList();
		Map<String, DomainVO> map = new HashMap<>();
		for (DomainVO domain : dataTypeList) {
			map.put(domain.getId(), domain);
		}
		return map;
	}

	public List<DomainVO> getAnalyserList() {
		return elasticAppDAO.getAnalyserList();
	}

	public Map<String, DomainVO> getAnalyserMap() {
		List<DomainVO> analyserList = elasticAppDAO.getAnalyserList();
		Map<String, DomainVO> map = new HashMap<>();
		for (DomainVO domain : analyserList) {
			map.put(domain.getId(), domain);
		}
		return map;
	}

}
