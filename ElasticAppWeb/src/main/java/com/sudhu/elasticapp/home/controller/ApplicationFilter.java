package com.sudhu.elasticapp.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.module.domain.UserVO;
import com.sudhu.elasticapp.module.service.ElasticAppService;

/**
 * Created by sudha on 04-Oct-16.
 */
public class ApplicationFilter extends HandlerInterceptorAdapter {

    @Autowired
    private ElasticAppService elasticAppService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        UserVO userVO = (UserVO) session.getAttribute(CommonConstants.USER_VO);

        if(null == userVO) {
            userVO = elasticAppService.getUser("A58FWZZ");
            session.setAttribute(CommonConstants.USER_VO, userVO);
        }
        return super.preHandle(request, response, handler);
    }
}
