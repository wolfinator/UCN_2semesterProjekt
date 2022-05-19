package ctrl;

import java.util.List;

import db.ProductDB;
import model.Product;

public class ProductCtrl {
	private ProductDB productDB;

	public ProductCtrl() throws DataAccessException {
		productDB = new ProductDB();
	}

	public Product findProductByName(String name) throws DataAccessException {
		Product res = productDB.findByName(name);
		return res;
	}
	
	public List<Product> findAll() throws DataAccessException{
		return productDB.findAll();
	}
	
}
