package com.sudhu.elasticapp.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.generic.dao.domain.DBConnectionVO;
import com.sudhu.elasticapp.generic.dao.helper.DBConnectionHelper;
import com.sudhu.elasticapp.home.domain.AbstractResponseVO;
import com.sudhu.elasticapp.home.form.CreateRequestForm;
import com.sudhu.elasticapp.home.form.SearchRequestForm;
import com.sudhu.elasticapp.home.service.QueryRequestService;
import com.sudhu.elasticapp.module.domain.ModuleVO;
import com.sudhu.elasticapp.module.domain.RequestVO;
import com.sudhu.elasticapp.module.domain.UserVO;
import com.sudhu.elasticapp.module.service.DomainService;
import com.sudhu.elasticapp.module.service.ElasticAppService;

/**
 * Created by sudha on 01-Oct-16.
 */
@Controller
public class HomeController {

	@Autowired
	private DomainService domainService;

	@Autowired
	private QueryRequestService requestService;

	@Autowired
	private ElasticAppService applicationService;

	@RequestMapping("/")
	public ModelAndView goHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView(CommonConstants.HOME_PAGE);
		return view;
	}

	@RequestMapping("/newRequest")
	public ModelAndView newRequest(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CreateRequestForm requestForm) {
		ModelAndView view = new ModelAndView(CommonConstants.NEW_REQUEST_PAGE);
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.USER_VO);
		requestForm.setUserId(userVO.getUserID());
		requestForm.setUserName(userVO.getUserName());

		requestForm.setAvailableProjects(domainService.getApplciationList());
		requestForm.setQueryTypes(domainService.getQueryTypes());
		requestForm.setDbTypes(domainService.getDBTypeList());
		requestForm.setUpdateFreqList(domainService.getFrequencyList());

		ModuleVO module = new ModuleVO();
		requestForm.setModuleVO(module);
		view.addObject(CommonConstants.NEW_REQUEST_FORM, requestForm);
		return view;
	}

	@RequestMapping("/saveNewRequest")
	public ModelAndView saveNewRequest(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CreateRequestForm requestForm) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			RequestVO requestVO = new RequestVO();
			requestVO.setProjectId(requestForm.getProjectId());
			requestVO.setQuery(requestForm.getQuery());
			requestVO.setQueryName(requestForm.getQueryName());
			requestVO.setQueryType(requestForm.getQueryType());
			requestVO.setUpdateFreq(requestForm.getUpdateFreq());
			requestVO.setModuleVO(requestForm.getModuleVO());
			DBConnectionHelper dbConnectionHelper = DBConnectionHelper.getInstance();
			DBConnectionVO dbConnectionVO = dbConnectionHelper.getConnectionVO(requestVO.getModuleVO());
			dbConnectionHelper.checkConnection(dbConnectionVO);
			requestVO.getModuleVO().setDbConnectionURL(dbConnectionVO.getConnectionString());

			requestService.checkUniqueQueryName(requestVO.getQueryName());
			applicationService.saveQueryRequest(requestVO);
			modelAndView.addObject(CommonConstants.NEW_REQUEST_FORM, requestForm);

			modelAndView.setViewName(CommonConstants.SUMMARY_PAGE);
		} catch (Exception ex) {
			modelAndView.addObject(CommonConstants.NEW_REQUEST_FORM, requestForm);
			requestForm.setErrorMessages(ex.getMessage());
			modelAndView.setViewName(CommonConstants.NEW_REQUEST_PAGE);
		}

		requestForm.setAvailableProjects(domainService.getApplciationList());
		requestForm.setQueryTypes(domainService.getQueryTypes());
		requestForm.setDbTypes(domainService.getDBTypeList());
		requestForm.setUpdateFreqList(domainService.getFrequencyList());
		modelAndView.addObject(CommonConstants.NEW_REQUEST_FORM, requestForm);
		return modelAndView;
	}

	@RequestMapping(value = "/testDBConnection")
	@ResponseBody
	public AbstractResponseVO testLocalConnection(HttpServletRequest request, HttpServletResponse response) {
		AbstractResponseVO responseVO = new AbstractResponseVO();
		try {
			String module = request.getParameter("module");
			ObjectMapper mapper = new ObjectMapper();
			ModuleVO moduleVO = mapper.readValue(module, ModuleVO.class);
			DBConnectionHelper dbConnectionHelper = DBConnectionHelper.getInstance();
			DBConnectionVO dbConnectionVO = dbConnectionHelper.getConnectionVO(moduleVO);
			dbConnectionHelper.checkConnection(dbConnectionVO);
			responseVO.setMessage("Connection was established successfully");

		} catch (Exception e) {
			responseVO.setErrorMessage(e.getMessage());
		}
		return responseVO;
	}

	@RequestMapping(value = "/searchRequests")
	public ModelAndView searchRequestsPage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute SearchRequestForm searchRequestForm) {
		ModelAndView modelAndView = new ModelAndView(CommonConstants.SEARCH_REQUEST_PAGE);

		return modelAndView;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchRequests(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute SearchRequestForm searchRequestForm) {
		ModelAndView modelAndView = new ModelAndView(CommonConstants.SEARCH_REQUEST_PAGE);

		return modelAndView;
	}

}
