package com.example.qrcode;



public class Model {

	private String name;
	private String price;
	private String quants;
	private boolean selected;

	public Model(String name, String price, String quants, boolean selected) {
		super();
		this.name = name;
		this.price = price;
		this.quants = quants;
		this.selected = selected;

	}

	public Model() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuants() {
		return quants;
	}

	public void setQuants(String quants) {
		this.quants = quants;
	}

}