package com.app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Tourist;
import com.app.error.TouristNotFoundException;
import com.app.repository.ITouristRepository;

@Service
public class TouristMgmtServiceIMPL implements ITouristMgmtService {

	@Autowired
	private ITouristRepository repo;

	@Override
	public String registerTourist(Tourist t) {
		return repo.save(t).getTid() + " ::ID of the saved Tourist ";
	}

	@Override
	public List<Tourist> getAllTourist() {
		return repo.findAll();
	}

	@Override
	public String updateTourist(Tourist t) {
		Tourist dbTourist = repo.findById(t.getTid())
				.orElseThrow(() -> new RuntimeException("Data not Found for Udpate Tourist"));
		BeanUtils.copyProperties(t, dbTourist);
		repo.save(dbTourist);
		return "tourst updated success";
	}

	@Override
	public Tourist showTouristById(Integer id){
		return repo.findById(id).orElseThrow(() -> new TouristNotFoundException("INVALID ID"));

//		Optional<Tourist> opt = repo.findById(id);
//		if (opt.isPresent()) {
//			Tourist tourist = opt.get();
//			return tourist;
//		}
//		return null;
	}

	@Override
	public String deleteTouristById(Integer id) {
		Tourist tourist = showTouristById(id);
		if (Objects.nonNull(tourist)) {
			repo.delete(tourist);
			return "record with id::" + id + "::is deleted";
		} else {
			return "No record found";
		}

	}

	@Override
	public String updateTouristBudgetById(Integer id, double hikePercentage) {
		Tourist tourist = repo.findById(id).orElseThrow(() -> new IllegalStateException("Data not found"));

		if (Objects.nonNull(tourist)) {
			Double newbudget = tourist.getBudget() + (tourist.getBudget() * (hikePercentage / 100.0f));
			tourist.setBudget(newbudget);
			repo.save(tourist);
			return "Budget record is Updated with id::" + tourist.getTid();
		} else {

			return "No record found";
		}

	}

}
