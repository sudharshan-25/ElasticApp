/**
 * 
 */
package com.sudhu.elasticapp.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sudha
 *
 */
@Controller
public class HomeController {

	@RequestMapping(method = RequestMethod.GET, value="/")
	public String getIndexPage() {
		return "index";
	}

}
