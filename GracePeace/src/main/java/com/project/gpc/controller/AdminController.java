package com.project.gpc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.gpc.entity.Admin;
import com.project.gpc.entity.dto.AdminDto;
import com.project.gpc.service.AdminService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/select", method=RequestMethod.POST)
	public ResponseEntity<List<Admin>> selectAdmin(){
		return ResponseEntity.ok(adminService.select());
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteAdmin(@RequestParam Integer id){
		Map<String, Object> result = new HashMap<>();
		adminService.delete(id);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> adminUser(@RequestBody AdminDto admin){
		Map<String, Object> result = new HashMap<>();
		adminService.save(admin);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
}
