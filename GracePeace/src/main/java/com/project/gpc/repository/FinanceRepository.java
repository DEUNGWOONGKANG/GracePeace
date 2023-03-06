package com.project.gpc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.project.gpc.entity.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Integer>, JpaSpecificationExecutor<Finance>{
}
