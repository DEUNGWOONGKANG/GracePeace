package com.project.gpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.gpc.entity.User;
import com.project.gpc.entity.dto.UserDto;
import com.project.gpc.repository.UserRepository;
import com.project.gpc.repository.spec.UserSpec;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> select(String username, String birthday, String regnumber, String phone, String address) {
		Specification<User> spec = (root, query, criteriaBuilder) -> null;
		if(username != null) spec = UserSpec.likeUsername(username);
		if(birthday != null) spec = spec.and(UserSpec.likeBirthday(birthday));
		if(regnumber != null) spec = spec.and(UserSpec.likeRegnumber(regnumber));
		if(phone != null) spec = spec.and(UserSpec.likePhone(phone));
		if(address != null) spec = spec.and(UserSpec.likeAddress(address));
		return userRepository.findAll(spec);
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	public void save(UserDto user) {
		User member = new User(user);
		userRepository.save(member);
	}


}
