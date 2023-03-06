package com.project.gpc.entity.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinanceDto {
	Integer id;
	Integer userid;
	String kind;
	Long price;
	String memo;
	String month;
	String week;
}