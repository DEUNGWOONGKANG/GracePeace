package com.project.gpc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.gpc.entity.Expend;

public interface ExpendRepository extends JpaRepository<Expend, Integer>, JpaSpecificationExecutor<Expend>{
}
