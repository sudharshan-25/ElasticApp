package com.sudhu.elasticapp.elastic.job;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sudhu.elasticapp.elastic.helper.ElasticHelper;
import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.RequestVO;

public class ElasticSearchRequestFetcher extends Thread {

	private String freqId;

	private int time;

	private TimeUnit timeUnit;

	private ElasticAppDAO elasticAppDAO;

	private ElasticHelper elasticHelper;

	private ScheduledExecutorService executor;

	public ElasticSearchRequestFetcher(String freqId, int time, TimeUnit timeUnit, ElasticAppDAO elasticAppDAO,
			ElasticHelper elasticHelper) {
		super();
		this.freqId = freqId;
		this.time = time;
		this.timeUnit = timeUnit;
		this.elasticAppDAO = elasticAppDAO;
		this.elasticHelper = elasticHelper;
	}

	@Override
	public void run() {
		List<RequestVO> requests = elasticAppDAO.getRequestsForFrequency(freqId);
		executor = Executors.newScheduledThreadPool(requests.size());
		for (RequestVO requestVO : requests) {
			ElasticSearchDataFetcher fetcher = new ElasticSearchDataFetcher(requestVO, elasticHelper, elasticAppDAO);
			//executor.scheduleAtFixedRate(fetcher, 0, time, timeUnit);
		}

	}

}
