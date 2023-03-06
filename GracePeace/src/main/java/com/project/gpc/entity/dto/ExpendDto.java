package com.project.gpc.entity.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpendDto {
	Integer id;
	String title;
	Long price;
	String memo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate expdate;
}
