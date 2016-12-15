package com.zehin.vpaas.mapper;

import java.util.List;

public interface ViewAccessStateMapper<Model> {
	List<Model> getLastAccessTimes();
	
	void addAccessTime(Model model);
	
	void outDate(Model model);
	
	Model getLastByView(Model model);
}
