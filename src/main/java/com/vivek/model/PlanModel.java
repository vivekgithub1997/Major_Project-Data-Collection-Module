package com.vivek.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class PlanModel {
	@Id
	@GeneratedValue
	private int planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private int planCategoryId;
	private String activeSW;
	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	@Column(name = "UPDATED_DATE", insertable = false)
	@CreationTimestamp
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;
}
