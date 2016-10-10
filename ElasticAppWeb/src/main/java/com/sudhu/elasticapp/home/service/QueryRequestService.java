/**
 * 
 */
package com.sudhu.elasticapp.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.home.controller.QueryRequestException;
import com.sudhu.elasticapp.module.service.ElasticAppService;

/**
 * @author sudha
 *
 */
@Service
public class QueryRequestService {

	@Autowired
	private ElasticAppService appService;

	public boolean checkUniqueQueryName(String queryName) throws QueryRequestException {
		boolean uniqueStatus = false;
		uniqueStatus = appService.checkUniqueQueryName(queryName);
		if(!uniqueStatus){
			throw new QueryRequestException(" Query Name already exists.!!! Please choose another query Name ");
		}
		return uniqueStatus;
	}

}
