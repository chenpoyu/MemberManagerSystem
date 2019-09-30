package com.poyu.mms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poyu.mms.service.ILoginService;

@Controller
public class MainController {

	@Autowired
	private ILoginService loginService;

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

}
