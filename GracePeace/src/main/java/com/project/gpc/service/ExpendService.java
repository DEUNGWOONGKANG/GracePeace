package com.project.gpc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.gpc.entity.Expend;
import com.project.gpc.entity.dto.ExpendDto;
import com.project.gpc.repository.ExpendRepository;
import com.project.gpc.repository.spec.ExpendSpec;
@Service
public class ExpendService {
	@Autowired
	private ExpendRepository expendRepository;

	public List<Expend> select(String title, LocalDate startDate, LocalDate endDate){
		Specification<Expend> spec = (root, query, criteriaBuilder) -> null;
		if(title != null && !title.equals("")) spec = spec.and(ExpendSpec.likeTitle(title));
		if(startDate != null) spec = spec.and(ExpendSpec.greaterStartDate(startDate));
		if(endDate != null) spec = spec.and(ExpendSpec.lessEndDate(endDate));
		return expendRepository.findAll(spec);
	}
	
	public void delete(Integer id) {
		expendRepository.deleteById(id);
	}

	public void save(ExpendDto expend) {
		Expend exp = new Expend(expend);
		expendRepository.save(exp);
	}

}
