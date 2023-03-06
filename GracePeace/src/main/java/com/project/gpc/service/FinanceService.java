package com.project.gpc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.gpc.entity.Finance;
import com.project.gpc.entity.User;
import com.project.gpc.entity.dto.FinanceDto;
import com.project.gpc.repository.FinanceRepository;
import com.project.gpc.repository.UserRepository;
import com.project.gpc.repository.spec.FinanceSpec;
@Service
public class FinanceService {
	@Autowired
	private FinanceRepository financeRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	public List<Finance> select(String username, String kind, String birthday, String startmonth, String startweek, String endmonth, String endweek){
		Specification<Finance> spec = (root, query, criteriaBuilder) -> null;
		if((username != null && !username.equals("")) || (birthday != null && !birthday.equals(""))) {
			List<User> userList = userService.select(username, birthday, null, null, null);
			if(userList.size() > 0) {
				spec = spec.and(FinanceSpec.inUser(userList));
			}
		}
		if(kind != null && !kind.equals("")) spec = spec.and(FinanceSpec.equalsKind(kind));
		if(startmonth != null && !startmonth.equals("")) spec = spec.and(FinanceSpec.greaterMonth(startmonth));
		if(endmonth != null && !endmonth.equals("")) spec = spec.and(FinanceSpec.lessMonth(endmonth));
		if(startweek != null && !startweek.equals("")) spec = spec.and(FinanceSpec.greaterWeek(startweek));
		if(endweek != null && !endweek.equals("")) spec = spec.and(FinanceSpec.lessWeek(endweek));
		return financeRepository.findAll(spec);
	}
	
	public void delete(Integer id) {
		financeRepository.deleteById(id);
	}

	public void save(FinanceDto finance) {
		Finance newFinance = new Finance(finance);
		User user = userRepository.findById(finance.getUserid()).orElse(null);
		newFinance.setUser(user);
		
		financeRepository.save(newFinance);
	}

}
