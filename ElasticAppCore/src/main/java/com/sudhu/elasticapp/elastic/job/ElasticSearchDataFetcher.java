package com.sudhu.elasticapp.elastic.job;

import java.util.List;
import java.util.Map;

import com.sudhu.elasticapp.elastic.exception.ElasticException;
import com.sudhu.elasticapp.elastic.helper.ElasticHelper;
import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import com.sudhu.elasticapp.generic.dao.exception.DBConnectionException;
import com.sudhu.elasticapp.generic.dao.helper.DBConnectionHelper;
import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.RequestVO;

public class ElasticSearchDataFetcher extends Thread {

	private RequestVO requestVO;

	private ElasticHelper elasticHelper;

	private ElasticAppDAO elasticAppDAO;

	public ElasticSearchDataFetcher(RequestVO requestVO, ElasticHelper elasticHelper, ElasticAppDAO elasticAppDAO) {
		super();
		this.requestVO = requestVO;
		this.elasticHelper = elasticHelper;
		this.elasticAppDAO = elasticAppDAO;
	}

	public void run() {

		try {
			DBConnectionHelper dbConnectionHelper = DBConnectionHelper.getInstance();
			DBConnectionVO dbConnectionVO = dbConnectionHelper.getConnectionVO(requestVO.getModuleVO());
			List<Map<String, Object>> tableData = dbConnectionHelper.getDataFromTable(dbConnectionVO,
					requestVO.getQuery());
			elasticHelper.insertBulkData(tableData, requestVO.getQueryName(), requestVO.getQueryName(),
					requestVO.getIdColumn());
			elasticAppDAO.updatedLastDate(requestVO.getRequestId());

		} catch (DBConnectionException | ElasticException e) {

			e.printStackTrace();
		}

	}
}
