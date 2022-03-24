package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Product;

public interface ProductDbIF {
	Product findProductByNo(String productNo)throws DataAccessException; 
	
	//Product findByProductID(int productID, boolean b) throws DataAccessException; 
	
	//List<Product> findProduct (boolean fullAssociation) throws DataAccessException; 
	
	//Product insert(Product product) throws DataAccessException; 
}

