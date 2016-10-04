package com.sudhu.elasticapp.home.controller;

import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.module.domain.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sudha on 01-Oct-16.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView goHome(HttpServletRequest request, HttpServletResponse response ){
        ModelAndView view = new ModelAndView(CommonConstants.HOME_PAGE);


        return view;
    }
}
