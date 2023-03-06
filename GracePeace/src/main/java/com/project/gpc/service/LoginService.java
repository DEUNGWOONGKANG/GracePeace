package com.project.gpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gpc.entity.Admin;
import com.project.gpc.entity.dto.AdminDto;
import com.project.gpc.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	
	

	public Admin login(AdminDto admin) {
		return loginRepository.findByLoginidAndPassword(admin.getLoginid(), admin.getPassword());
	}

}
