package com.project.gpc.entity;

import com.project.gpc.entity.dto.AdminDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="t_admin")
public class Admin {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "loginid")
	String loginid;
	
	@Column(name = "password")
	String password;

	public Admin(AdminDto admin) {
		this.id = admin.getId();
		this.loginid = admin.getLoginid();
		this.password = admin.getPassword();
	}
}
