package com.vivek.service;

import java.util.List;
import java.util.Map;

import com.vivek.binding.ChildRequest;
import com.vivek.binding.DcChildrens;
import com.vivek.binding.DcEducation;
import com.vivek.binding.DcIncome;
import com.vivek.binding.DcSummary;
import com.vivek.binding.PlanSelection;

public interface DcService {

	public Long loadCaseNum(Integer appId);

	public Map<Integer, String> getPlanName();

	public Long savePlanSelection(PlanSelection planSelection);

	public Long saveIncome(DcIncome dcIncome);

	public Long saveChildrens(ChildRequest childRequest);

	public Long saveEducation(DcEducation dcEducation);

	public DcSummary getSummary(Long caseNum);

}
