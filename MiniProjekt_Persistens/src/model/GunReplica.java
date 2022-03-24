package model;

public class GunReplica extends Product {
	private String calibre; 
	private String materiel;
	
	public GunReplica(int id, Supplier supplier, String name, String productNo, String description,
					  double purchasePrice, double salesPrice, double rentPrice, 
					  String countryOfOrigin, int stock, int minStock,
					  String calibre, String materiel) {
		super(id, supplier, name, productNo, description, 
			  purchasePrice, salesPrice, rentPrice, 
			  countryOfOrigin, stock, minStock);
		this.calibre = calibre;
		this.materiel = materiel;
	}
	
	public GunReplica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCalibre() {
		return calibre;
	}
	
	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}
	
	public String getMateriel() {
		return materiel;
	}
	
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	} 
	
}
