package com.app.service;

import java.util.List;

import com.app.entity.Tourist;
import com.app.error.TouristNotFoundException;

public interface ITouristMgmtService {

	public String registerTourist(Tourist t);

	public List<Tourist> getAllTourist();

	public Tourist showTouristById(Integer id) throws TouristNotFoundException;

	public String updateTourist(Tourist t);

	public String deleteTouristById(Integer id);
	
	public String updateTouristBudgetById(Integer id,double hikePercentage);
}
