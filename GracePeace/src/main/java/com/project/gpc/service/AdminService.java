package com.project.gpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gpc.entity.Admin;
import com.project.gpc.entity.dto.AdminDto;
import com.project.gpc.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	public List<Admin> select() {
		return adminRepository.findAll();
	}

	public void delete(Integer id) {
		adminRepository.deleteById(id);
	}

	public void save(AdminDto admin) {
		Admin member = new Admin(admin);
		adminRepository.save(member);
	}


}
