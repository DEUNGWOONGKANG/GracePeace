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
import com.project.gpc.entity.User;
import com.project.gpc.entity.dto.UserDto;
import com.project.gpc.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/select", method=RequestMethod.POST)
	public ResponseEntity<List<User>> selectUser(@RequestParam(required = false) String username
												,@RequestParam(required = false) String birthday
												,@RequestParam(required = false) String regnumber
												,@RequestParam(required = false) String phone
												,@RequestParam(required = false) String address){
		return ResponseEntity.ok(userService.select(username, birthday, regnumber, phone, address));
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestParam Integer id){
		Map<String, Object> result = new HashMap<>();
		userService.delete(id);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody UserDto user){
		Map<String, Object> result = new HashMap<>();
		userService.save(user);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
}
