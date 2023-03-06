package com.project.gpc.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.gpc.entity.Admin;
import com.project.gpc.entity.dto.AdminDto;
import com.project.gpc.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private LoginService LoginService;
	
	@RequestMapping("/")
	public String main() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(AdminDto admin, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView nextView = null;
		Admin user = LoginService.login(admin);
		if(user == null) {
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('ID Ȥ�� ��й�ȣ�� Ȯ���ϼ���.'); history.go(-1);</script>");
            out.flush();
		}else{
			nextView = new ModelAndView("main");
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("adminid", user.getLoginid());
			httpSession.setAttribute("menu", "main");
			httpSession.setAttribute("APPROOT", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
		}
		return nextView;
	}
}
