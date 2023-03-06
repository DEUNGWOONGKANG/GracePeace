package com.project.gpc.repository.spec;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.project.gpc.entity.Finance;
import com.project.gpc.entity.User;

public class FinanceSpec {
	
	public static Specification<Finance> inUser(List<User> user) {
		return (Specification<Finance>) ((root, query, builder) -> root.get("user").in(user));
	}
	
	public static Specification<Finance> equalsKind(String kind) {
		return (Specification<Finance>) ((root, query, builder) -> builder.equal(root.get("kind"), kind));
	}
	
	public static Specification<Finance> greaterMonth(String startmonth) {
		return (Specification<Finance>) ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("month"), startmonth));
	}

	public static Specification<Finance> lessMonth(String endmonth) {
		return (Specification<Finance>) ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("month"), endmonth));
	}

	public static Specification<Finance> greaterWeek(String startweek) {
		return (Specification<Finance>) ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("week"), startweek));
	}

	public static Specification<Finance> lessWeek(String endweek) {
		return (Specification<Finance>) ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("week"), endweek));
	}

}
