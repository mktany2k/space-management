package com.osm.model;


import java.util.List;


public class Plan {
    
	private String id;
	
	private List<Lot> lots;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}
}