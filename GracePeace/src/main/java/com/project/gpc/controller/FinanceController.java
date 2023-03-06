package com.project.gpc.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.gpc.entity.Finance;
import com.project.gpc.entity.dto.FinanceDto;
import com.project.gpc.service.FinanceService;

@Controller
@RequestMapping(value="/finance")
public class FinanceController {
	
	@Autowired
	private FinanceService financeService;
	
	@RequestMapping(value="/select", method=RequestMethod.POST)
	public ResponseEntity<List<Finance>> selectFinance(@RequestParam(required = false) String username
														,@RequestParam(required = false) String kind
														,@RequestParam(required = false) String birthday
														,@RequestParam(required = false) String startmonth
														,@RequestParam(required = false) String startweek
														,@RequestParam(required = false) String endmonth
														,@RequestParam(required = false) String endweek){
		return ResponseEntity.ok(financeService.select(username, kind, birthday, startmonth, startweek, endmonth, endweek));
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteFinance(@RequestParam Integer id){
		Map<String, Object> result = new HashMap<>();
		financeService.delete(id);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> saveFinance(@RequestBody FinanceDto finance){
		Map<String, Object> result = new HashMap<>();
		financeService.save(finance);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
}
