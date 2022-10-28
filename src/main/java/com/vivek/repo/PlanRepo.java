package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.PlanModel;

public interface PlanRepo extends JpaRepository<PlanModel, Integer> {

}
