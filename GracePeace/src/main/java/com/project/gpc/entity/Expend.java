package com.project.gpc.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.gpc.entity.dto.ExpendDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="t_expend")
public class Expend {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "price")
	Long price;
	
	@Column(name = "expdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate expdate;
	
	@Column(name = "memo")
	String memo;
	
	public Expend(ExpendDto expend) {
		this.id = expend.getId();
		this.title = expend.getTitle();
		this.price = expend.getPrice();
		this.expdate = expend.getExpdate();
		this.memo = expend.getMemo();
	}
}
