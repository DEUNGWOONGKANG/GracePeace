package com.project.gpc.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.gpc.entity.dto.FinanceDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="t_finance")
public class Finance {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "kind")
	String kind;
	
	@Column(name = "price")
	Long price;
	
	@Column(name = "month")
	String month;
	
	@Column(name = "week")
	String week;
	
	@Column(name = "memo")
	String memo;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	User user;
	
	public Finance(FinanceDto finance) {
		this.id = finance.getId();
		this.kind = finance.getKind();
		this.price = finance.getPrice();
		this.month = finance.getMonth();
		this.week = finance.getWeek();
		this.memo = finance.getMemo();
	}
}
