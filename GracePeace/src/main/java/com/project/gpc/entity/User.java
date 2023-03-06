package com.project.gpc.entity;

import com.project.gpc.entity.dto.UserDto;

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
@Table(name="t_user")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "birthday")
	private String birthday;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gradename")
	private String gradename;
	
	@Column(name = "regnumber")
	private String regnumber;
	
	public User(UserDto user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.gradename = user.getGradename();
		this.regnumber = user.getRegnumber();
		if(user.getRegnumber() != null && !user.getRegnumber().equals("")) {
			if(user.getRegnumber().indexOf("-") == -1) {
				this.birthday = user.getRegnumber();
			}else {
				String[] reg = user.getRegnumber().split("-");
				this.birthday = reg[0];
			}
		}
		
	}
}
