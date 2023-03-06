package com.project.gpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MenuController {
	
	@RequestMapping(value="/menu", method=RequestMethod.POST)
	public ModelAndView go(@RequestParam(required = false) String menu, HttpServletRequest request){
		ModelAndView nextView = new ModelAndView(menu);
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("menu", menu);
		return nextView;
	}
}
