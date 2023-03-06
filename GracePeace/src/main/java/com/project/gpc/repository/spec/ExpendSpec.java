package com.project.gpc.repository.spec;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import com.project.gpc.entity.Expend;

public class ExpendSpec {
	
	public static Specification<Expend> likeTitle(String title) {
		return (Specification<Expend>) ((root, query, builder) -> builder.like(root.get("title"), "%" + title + "%"));
	}
	
	public static Specification<Expend> greaterStartDate(LocalDate startDate) {
		return (Specification<Expend>) ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("expdate"), startDate));
	}

	public static Specification<Expend> lessEndDate(LocalDate endDate) {
		return (Specification<Expend>) ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("expdate"), endDate));
	}
}
