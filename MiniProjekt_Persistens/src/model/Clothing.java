package model;

public class Clothing extends Product {
	private String size; 
	private String colour;
	
	public Clothing(int id, Supplier supplier, String name, String productNo, String description, 
			double purchasePrice, double salesPrice, double rentPrice, 
			String countryOfOrigin, int stock, int minStock, 
			String size, String colour) {
		
		super(id, supplier, name, productNo, description, 
			  purchasePrice, salesPrice, rentPrice, 
			  countryOfOrigin, stock, minStock);
		
		this.size = size;
		this.colour = colour;
	}

	public Clothing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	} 
	
}
