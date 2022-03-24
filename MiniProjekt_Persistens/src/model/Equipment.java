package model;

public class Equipment extends Product {
	private String type; 

	public Equipment(int id, Supplier supplier, String name, String productNo, String description, 
			double purchasePrice, double salesPrice, double rentPrice, 
			String countryOfOrigin, int stock, int minStock, String type) {
		super(id, supplier, name, productNo, description,
		      purchasePrice, salesPrice, rentPrice, 
		      countryOfOrigin, stock, minStock);
		this.type = type;
	}

	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
