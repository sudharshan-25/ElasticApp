package com.sudhu.elasticapp.elastic.job;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.sudhu.elasticapp.elastic.helper.ElasticHelper;
import com.sudhu.elasticapp.module.dao.ElasticAppDAO;
import com.sudhu.elasticapp.module.domain.DomainVO;
import com.sudhu.elasticapp.module.service.DomainService;

@Service
@EnableScheduling
public class ElasticDataFetchScheduler {

	private ScheduledExecutorService executor;

	@Autowired
	private DomainService domainService;

	@Autowired
	private ElasticAppDAO elasticAppDAO;

	@Autowired
	private ElasticHelper elasticHelper;

	@PostConstruct
	public void initDataFetchers() {
		List<DomainVO> frequencyList = domainService.getFrequencyList();
		executor = Executors.newScheduledThreadPool(frequencyList.size());

		for (DomainVO domainVO : frequencyList) {
			ElasticSearchRequestFetcher fetcher = null;
			switch (domainVO.getId()) {
			case "1":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 5, TimeUnit.MINUTES, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;
			case "2":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 2, TimeUnit.HOURS, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;
			case "3":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 4, TimeUnit.HOURS, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;
			case "4":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 6, TimeUnit.HOURS, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;
			case "5":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 12, TimeUnit.HOURS, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;
			case "6":
				fetcher = new ElasticSearchRequestFetcher(domainVO.getId(), 1, TimeUnit.DAYS, elasticAppDAO,
						elasticHelper);
				executor.execute(fetcher);
				break;

			default:
				break;
			}

		}

	}

	@PreDestroy
	public void destroy() {
		executor.shutdown();
	}

}
