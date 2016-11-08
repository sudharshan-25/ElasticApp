package com.sudhu.elasticapp.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudhu.elasticapp.common.helper.GeneralUtils;
import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import com.sudhu.elasticapp.generic.dao.helper.DBConnectionHelper;
import com.sudhu.elasticapp.home.form.AbstractResponseVO;
import com.sudhu.elasticapp.home.form.RequestForm;
import com.sudhu.elasticapp.home.form.SearchRequestForm;
import com.sudhu.elasticapp.module.domain.ColumnMappingVO;
import com.sudhu.elasticapp.module.domain.ModuleVO;
import com.sudhu.elasticapp.module.domain.RequestHeaderVO;
import com.sudhu.elasticapp.module.domain.RequestVO;
import com.sudhu.elasticapp.module.service.DomainService;
import com.sudhu.elasticapp.module.service.ElasticAppService;

@RestController
@RequestMapping(value = "/request")
public class RequestRestController {

	@Autowired
	private DomainService domainService;

	@Autowired
	private ElasticAppService requestService;

	@RequestMapping(value = "/fetchForm")
	public ResponseEntity<RequestForm> fetchRequestForm() {
		RequestForm requestForm = new RequestForm();
		requestForm.setAvailableProjects(domainService.getApplciationList());
		requestForm.setDbTypes(domainService.getDBTypeList());
		requestForm.setQueryTypes(domainService.getQueryTypes());
		requestForm.setUpdateFreqList(domainService.getFrequencyList());
		requestForm.setDataTypeList(domainService.getDataTypeList());
		requestForm.setStatusList(domainService.getStatusList());
		return new ResponseEntity<RequestForm>(requestForm, HttpStatus.OK);
	}

	@RequestMapping(value = "/testConnection", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractResponseVO> testDBConnection(@RequestBody ModuleVO moduleVO) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		DBConnectionHelper dbConnectionHelper = DBConnectionHelper.getInstance();
		DBConnectionVO dbConnectionVO = dbConnectionHelper.getConnectionVO(moduleVO);
		try {
			dbConnectionHelper.checkConnection(dbConnectionVO);
			responseVO.setMessage("Connection was established successfully");
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUniqueName/{queryName}")
	public ResponseEntity<AbstractResponseVO> checkUniqueName(@PathVariable String queryName) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		try {
			boolean uniqueStatus = false;
			uniqueStatus = requestService.checkUniqueQueryName(queryName);
			if (!uniqueStatus) {
				throw new Exception(" Query Name already exists.!!! Please choose another query Name ");
			}
			responseVO.setMessage("Free to use the QueryName");
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}

		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/fetchColumnMapping", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractResponseVO> fetchColumnMapping(@RequestBody RequestVO requestVO) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		List<ColumnMappingVO> columnMappingList = new ArrayList<>();

		try {
			DBConnectionHelper dbConnectionHelper = DBConnectionHelper.getInstance();
			DBConnectionVO dbConnectionVO = dbConnectionHelper.getConnectionVO(requestVO.getModuleVO());
			Map<String, Integer> columnDataMap = dbConnectionHelper.checkQuery(dbConnectionVO, requestVO.getQuery());
			for (String column : columnDataMap.keySet()) {
				ColumnMappingVO columnVO = new ColumnMappingVO();
				columnVO.setColumnName(column);
				columnVO.setQueryDataType("");
				columnMappingList.add(columnVO);
			}
			responseVO.setData(columnMappingList);
			responseVO.setMessage("true");
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);

	}

	@RequestMapping(value = "/saveRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractResponseVO> saveRequest(@RequestBody RequestVO requestVO) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		try {
			requestService.saveQueryRequest(requestVO);
			responseVO.setMessage("Request Saved successfully");
			responseVO.setData(requestVO);
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractResponseVO> searchRequests(@RequestBody SearchRequestForm searchRequestForm) {
		AbstractResponseVO responseVO = new AbstractResponseVO();

		try {
			Map<String, String> searchCriteria = new HashMap<>();

			searchCriteria.put("query_name", searchRequestForm.getQueryName());
			if (GeneralUtils.isValidString(searchRequestForm.getQueryType())) {
				searchCriteria.put("query_type_id", new Integer(searchRequestForm.getQueryType()).toString());
			}
			if (GeneralUtils.isValidString(searchRequestForm.getProjectId())) {
				searchCriteria.put("query_app_id", new Integer(searchRequestForm.getProjectId()).toString());
			}
			if (GeneralUtils.isValidString(searchRequestForm.getDbType())) {
				searchCriteria.put("db_id", new Integer(searchRequestForm.getDbType()).toString());
			}
			if (GeneralUtils.isValidString(searchRequestForm.getUpdateFreq())) {
				searchCriteria.put("query_freq_id", new Integer(searchRequestForm.getUpdateFreq()).toString());
			}
			if (GeneralUtils.isValidString(searchRequestForm.getStatusId())) {
				searchCriteria.put("query_status_id", new Integer(searchRequestForm.getStatusId()).toString());
			}

			List<RequestHeaderVO> searchResults = requestService.searchResults(searchCriteria);
			responseVO.setData(searchResults);

		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);

	}

	@RequestMapping(value = "/getRequest/{requestId}")
	public ResponseEntity<AbstractResponseVO> getRequests(@PathVariable int requestId) {
		AbstractResponseVO responseVO = new AbstractResponseVO();

		try {
			RequestVO requestVO =requestService.getRequest(requestId);
			responseVO.setData(requestVO);
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}

}
