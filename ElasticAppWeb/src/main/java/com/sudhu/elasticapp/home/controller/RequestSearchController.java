package com.sudhu.elasticapp.home.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudhu.elasticapp.common.helper.GeneralUtils;
import com.sudhu.elasticapp.elastic.domain.SearchCriteria;
import com.sudhu.elasticapp.elastic.helper.ElasticHelper;
import com.sudhu.elasticapp.home.form.AbstractResponseVO;
import com.sudhu.elasticapp.module.service.ElasticAppService;

@RestController
@RequestMapping(value = "/dataSearch")
public class RequestSearchController {

	@Autowired
	private ElasticAppService requestService;

	@Autowired
	private ElasticHelper elasticHelper;

	@RequestMapping(value = "/simpleSearch", method = RequestMethod.GET)
	public ResponseEntity<AbstractResponseVO> fetchSimpleSearch(@RequestParam("appToken") String appToken,
			@RequestParam("term") String term,
			@RequestParam(value = "fields", required = false, defaultValue = "") String fields,
			@RequestParam(value = "wildcard", required = false, defaultValue = "false") boolean wildcard) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		List<Map<String, String>> data = new ArrayList<>();
		try {
			String indexName = requestService.getCurrentIndexForToken(appToken);
			List<String> searchFields = null;
			if(GeneralUtils.isValidString(fields)){
				searchFields = Arrays.asList(fields.split(","));
			}else{
				searchFields = new ArrayList<>();
			}
			data = elasticHelper.doSearchOperation(indexName, indexName, searchFields, term, wildcard);
			responseVO.setData(data);
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/advancedSearch")
	public ResponseEntity<AbstractResponseVO> fetchAdvancedSearch(@RequestBody SearchCriteria searchCriteria) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		List<Map<String, String>> data = new ArrayList<>();
		try {
			String appToken = searchCriteria.getAppToken();
			String indexName = requestService.getCurrentIndexForToken(appToken);
			data = elasticHelper.doAdvancedSearchOperation(indexName, indexName, searchCriteria);
			responseVO.setData(data);
		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<AbstractResponseVO>(responseVO, HttpStatus.OK);
	}
}
