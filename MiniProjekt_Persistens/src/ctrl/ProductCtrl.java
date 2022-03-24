package ctrl;

import java.util.List;

import db.ProductDB;
import model.Product;

public class ProductCtrl {
	private ProductDB productDB; 
	
	public ProductCtrl() throws DataAccessException {
		productDB = new ProductDB(); 
	}
	
	public List<Product> findByProductNo(String productNo) throws DataAccessException {
		List<Product> res = productDB.findByProductNo(productNo, true);
		return res;
	}
	
	Product findByProdutID(int productID, boolean b) throws DataAccessException{
		Product res = productDB.findByProductID(true);
		return res; 
	}
	public List<Product> findAll() throws DataAccessException {
		List<Product> res = productDB.findAll(false);
		return res;
	}
	
	public Product insert(Product product) throws DataAccessException {
		Product res = productDB.insert(product);
		return res;
	}
}
