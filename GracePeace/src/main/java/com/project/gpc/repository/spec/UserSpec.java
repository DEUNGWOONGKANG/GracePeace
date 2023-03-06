package com.project.gpc.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.project.gpc.entity.User;

public class UserSpec {
	
	public static Specification<User> likeUsername(String username) {
		return (Specification<User>) ((root, query, builder) -> builder.like(root.get("username"), "%" + username + "%"));
	}
	
	public static Specification<User> likeBirthday(String birthday) {
		return (Specification<User>) ((root, query, builder) -> builder.like(root.get("birthday"), "%" + birthday + "%"));
	}

	public static Specification<User> likeRegnumber(String regnumber) {
		return (Specification<User>) ((root, query, builder) -> builder.like(root.get("regnumber"), "%" + regnumber + "%"));
	}

	public static Specification<User> likePhone(String phone) {
		return (Specification<User>) ((root, query, builder) -> builder.like(root.get("phone"), "%" + phone + "%"));
	}

	public static Specification<User> likeAddress(String address) {
		return (Specification<User>) ((root, query, builder) -> builder.like(root.get("address"), "%" + address + "%"));
	}

}
