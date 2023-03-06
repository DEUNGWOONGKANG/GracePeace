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

import com.project.gpc.entity.Expend;
import com.project.gpc.entity.dto.ExpendDto;
import com.project.gpc.service.ExpendService;

@Controller
@RequestMapping(value="/expend")
public class ExpendController {
	@Autowired
	private ExpendService expendService;
	
	@RequestMapping(value="/select", method=RequestMethod.POST)
	public ResponseEntity<List<Expend>> selectExpend(@RequestParam(required = false) String title
													,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
													,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		return ResponseEntity.ok(expendService.select(title, startDate, endDate));
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteExpend(@RequestParam Integer id){
		Map<String, Object> result = new HashMap<>();
		expendService.delete(id);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> saveExpend(@RequestBody ExpendDto expend){
		Map<String, Object> result = new HashMap<>();
		expendService.save(expend);
		
		result.put("result", "Success");
		return ResponseEntity.ok(result);
	}
	
}
