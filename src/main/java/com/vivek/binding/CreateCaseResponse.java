package com.vivek.binding;

import java.util.Map;

import lombok.Data;

@Data
public class CreateCaseResponse {

	private Long caseNum;

	Map<Integer, String> planName;

}
