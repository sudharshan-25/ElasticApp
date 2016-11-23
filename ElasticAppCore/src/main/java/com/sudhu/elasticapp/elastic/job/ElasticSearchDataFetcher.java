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
			int currentSeqId = elasticAppDAO.getCurrentSequenceId(requestVO.getRequestId());
			String aliasName = requestVO.getQueryName();
			String currentIndex = aliasName + "_" + currentSeqId;
			currentSeqId++;
			String futureIndex = aliasName + "_" + currentSeqId;
			List<Map<String, Object>> tableData = dbConnectionHelper.getDataFromTable(dbConnectionVO,
					requestVO.getQuery());
			elasticHelper.createType(futureIndex, futureIndex, requestVO.getColumnMapping());
			if (currentSeqId == 1) {
				elasticHelper.createAlias(futureIndex, aliasName);
				elasticAppDAO.insertLastDate(requestVO.getRequestId(), currentSeqId);
			}
			elasticHelper.insertBulkData(tableData, futureIndex, futureIndex, requestVO.getIdColumn());
			if (currentSeqId != 1) {
				elasticHelper.modifyAlias(currentIndex, futureIndex, aliasName);
				elasticHelper.deleteType(currentIndex);
			}
			elasticAppDAO.updatedLastDate(requestVO.getRequestId(), currentSeqId);

		} catch (DBConnectionException | ElasticException e) {

			e.printStackTrace();
		}

	}
}
