package com.sudhu.elasticapp.home.controller;

import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.module.domain.UserVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by sudha on 04-Oct-16.
 */
public class ApplicationFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        UserVO userVO = (UserVO) session.getAttribute(CommonConstants.USER_VO);

        if(null == userVO) {
            //TODO DB Call to fetch the user
            userVO = new UserVO();
            userVO.setUserName("Sudharshan");
            userVO.setUserPin("EA001");
            session.setAttribute(CommonConstants.USER_VO, userVO);
        }
        return super.preHandle(request, response, handler);
    }
}
