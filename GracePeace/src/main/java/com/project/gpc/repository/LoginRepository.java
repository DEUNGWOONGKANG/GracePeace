package com.project.gpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.gpc.entity.Admin;

public interface LoginRepository extends JpaRepository<Admin, Integer>{

	Admin findByLoginidAndPassword(String loginid, String password);
}
