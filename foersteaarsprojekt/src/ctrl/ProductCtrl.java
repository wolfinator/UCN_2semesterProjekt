package ctrl;



import db.ProductDB;
import model.Product;

public class ProductCtrl{
private ProductDB productDB;


public ProductCtrl() throws DataAccessException {
	productDB = new ProductDB();
}
public Product findProductById(int productId) throws DataAccessException {
	Product res = productDB.findProductById(productId);
	return res;
}



}
