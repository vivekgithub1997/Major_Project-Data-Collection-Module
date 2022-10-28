package com.vivek.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.binding.ChildRequest;
import com.vivek.binding.CreateCaseResponse;
import com.vivek.binding.DcEducation;
import com.vivek.binding.DcIncome;
import com.vivek.binding.DcSummary;
import com.vivek.binding.PlanSelection;
import com.vivek.service.DcService;

@RestController
public class DcController {

	@Autowired
	private DcService dcService;

	@GetMapping("/createCase/{appId}")
	public ResponseEntity<CreateCaseResponse> createCaseNum(@PathVariable Integer appId) {

		Long caseNum = dcService.loadCaseNum(appId);

		Map<Integer, String> planName = dcService.getPlanName();

		CreateCaseResponse createCaseResponse = new CreateCaseResponse();

		createCaseResponse.setCaseNum(caseNum);

		createCaseResponse.setPlanName(planName);

		return new ResponseEntity<CreateCaseResponse>(createCaseResponse, HttpStatus.OK);
	}

	@PostMapping("/planSelection")
	public ResponseEntity<Long> planSelection(@RequestBody PlanSelection planSelection) {

		Long caseNum = dcService.savePlanSelection(planSelection);

		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);

	}

	@PostMapping("/dcIncome")
	public ResponseEntity<String> saveIncome(@RequestBody DcIncome dcIncome) {

		Long caseNum = dcService.saveIncome(dcIncome);
		if (caseNum != null) {
			return new ResponseEntity<String>("Income Saved " + "CaseNumber -:" + caseNum, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("Income Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/dcEducation")
	public ResponseEntity<String> saveEducation(@RequestBody DcEducation dcEducation) {

		Long caseNum = dcService.saveEducation(dcEducation);
		if (caseNum != null) {
			return new ResponseEntity<String>("Education Saved " + "CaseNumber -:" + caseNum, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Education Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/dcChildrens")
	public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest childRequest) {

		Long caseNum = dcService.saveChildrens(childRequest);

		DcSummary summary = dcService.getSummary(caseNum);

		return new ResponseEntity<DcSummary>(summary, HttpStatus.CREATED);
	}
}
