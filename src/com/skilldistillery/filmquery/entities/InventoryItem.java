package com.skilldistillery.filmquery.entities;

import java.sql.Timestamp;

public class InventoryItem {
	private int id;
	private int filmId;
	private int storeId;
	private String condition;
	private Timestamp timestamp;

	public InventoryItem() {
	}

	public InventoryItem(int id, int filmId, int storeId, String condition, Timestamp timestamp) {
		this.id = id;
		this.filmId = filmId;
		this.storeId = storeId;
		this.condition = condition;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Inventory Item ID: " + id + ", Condition: " + condition;
	}

}
