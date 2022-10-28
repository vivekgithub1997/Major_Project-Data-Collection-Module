package com.vivek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DcChildrensModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childrenId;
	private String childrenName;
	private Integer childrenAge;
	private Integer childrenSsn;
	private Long caseNum;

}
