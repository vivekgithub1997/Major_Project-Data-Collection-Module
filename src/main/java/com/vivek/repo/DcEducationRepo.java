package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.DcEducationModel;

public interface DcEducationRepo extends JpaRepository<DcEducationModel, Integer> {

	public DcEducationModel findByCaseNum(Long caseNum);
}
