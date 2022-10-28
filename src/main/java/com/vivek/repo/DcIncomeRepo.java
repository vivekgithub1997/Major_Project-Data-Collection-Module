package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.DcIncomeModel;

public interface DcIncomeRepo extends JpaRepository<DcIncomeModel, Integer> {
	
	public DcIncomeModel findByCaseNum(Long caseNum);

}
