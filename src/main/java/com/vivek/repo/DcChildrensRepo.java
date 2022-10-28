package com.vivek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.DcChildrensModel;

public interface DcChildrensRepo extends JpaRepository<DcChildrensModel, Integer> {

	public List<DcChildrensModel> findByCaseNum(Long caseNum);
}
