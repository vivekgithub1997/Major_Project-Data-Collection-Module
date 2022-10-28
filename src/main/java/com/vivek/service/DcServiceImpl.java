package com.vivek.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivek.binding.ChildRequest;
import com.vivek.binding.DcChildrens;
import com.vivek.binding.DcEducation;
import com.vivek.binding.DcIncome;
import com.vivek.binding.DcSummary;
import com.vivek.binding.PlanSelection;
import com.vivek.model.CitizenApplicationModel;
import com.vivek.model.DcCasesModel;
import com.vivek.model.DcChildrensModel;
import com.vivek.model.DcEducationModel;
import com.vivek.model.DcIncomeModel;
import com.vivek.model.PlanModel;
import com.vivek.repo.CitizenApplicationRepo;
import com.vivek.repo.DcCasesRepo;
import com.vivek.repo.DcChildrensRepo;
import com.vivek.repo.DcEducationRepo;
import com.vivek.repo.DcIncomeRepo;
import com.vivek.repo.PlanRepo;

@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private CitizenApplicationRepo citizenRepo;

	@Autowired
	private DcCasesRepo dcCasesRepo;

	@Autowired
	private DcChildrensRepo dcChildrRepo;

	@Autowired
	private DcEducationRepo dcEduRepo;

	@Autowired
	private DcIncomeRepo dcIncomeRepo;

	@Autowired
	private PlanRepo planRepo;

	@Override
	public Long loadCaseNum(Integer appId) {

		Optional<CitizenApplicationModel> app = citizenRepo.findById(appId);
		if (app.isPresent()) {
			DcCasesModel dcCasesModel = new DcCasesModel();
			dcCasesModel.setAppId(appId);
			dcCasesRepo.save(dcCasesModel);
			return dcCasesModel.getCaseNum();
		}

		return 0l;

	}

	@Override
	public Map<Integer, String> getPlanName() {
		List<PlanModel> findAll = planRepo.findAll();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (PlanModel planModel : findAll) {

			map.put(planModel.getPlanId(), planModel.getPlanName());
		}
		return map;
	}

	@Override
	public Long savePlanSelection(PlanSelection planSelection) {

		Optional<DcCasesModel> findById = dcCasesRepo.findById(planSelection.getCaseNum());

		if (findById.isPresent()) {
			DcCasesModel dcCasesModel = findById.get();
			dcCasesModel.setPlanId(planSelection.getPlanId());
			dcCasesRepo.save(dcCasesModel);
			return planSelection.getCaseNum();
		}

		return null;
	}

	@Override
	public Long saveIncome(DcIncome dcIncome) {

		DcIncomeModel dcIncomeModel = new DcIncomeModel();

		BeanUtils.copyProperties(dcIncome, dcIncomeModel);

		dcIncomeRepo.save(dcIncomeModel);

		return dcIncome.getCaseNum();
	}

	@Override
	public Long saveChildrens(ChildRequest childRequest) {

		List<DcChildrens> childs = childRequest.getChilds();

		for (DcChildrens c : childs) {

			DcChildrensModel dcChildrensModel = new DcChildrensModel();
			BeanUtils.copyProperties(c, dcChildrensModel);
			dcChildrensModel.setCaseNum(childRequest.getCaseNum());

			dcChildrRepo.save(dcChildrensModel);

		}

		return childRequest.getCaseNum();
	}

	@Override
	public Long saveEducation(DcEducation dcEducation) {

		DcEducationModel dcEducationModel = new DcEducationModel();

		BeanUtils.copyProperties(dcEducation, dcEducationModel);

		dcEduRepo.save(dcEducationModel);

		return dcEducation.getCaseNum();
	}

	@Override
	public DcSummary getSummary(Long caseNum) {
		String planName = "";

		DcEducationModel dcEducationModel = dcEduRepo.findByCaseNum(caseNum);
		DcIncomeModel dcIncomeModel = dcIncomeRepo.findByCaseNum(caseNum);
		List<DcChildrensModel> dcChildsModel = dcChildrRepo.findByCaseNum(caseNum);

		Optional<DcCasesModel> dcCases = dcCasesRepo.findById(caseNum);
		if (dcCases.isPresent()) {
			Integer planId = dcCases.get().getPlanId();
			Optional<PlanModel> plan = planRepo.findById(planId);
			if (plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		DcSummary dcSummary = new DcSummary();
		dcSummary.setPlanName(planName);

		DcIncome income = new DcIncome();
		BeanUtils.copyProperties(dcIncomeModel, income);
		dcSummary.setDcIncome(income);

		DcEducation education = new DcEducation();
		BeanUtils.copyProperties(dcEducationModel, education);
		dcSummary.setDcEducation(education);

		DcChildrens childrens = new DcChildrens();
		BeanUtils.copyProperties(dcChildsModel, childrens);
		dcSummary.setDcChildrens(childrens);
		return dcSummary;
	}

}
