package ctrl;



import db.ProductDB;
import model.Product;

public class ProductCtrl{
private ProductDB productDB;


public ProductCtrl() {
	productDB = new ProductDB();
}
public Product findProductById(int productId) {
	Product res = ProductDB.findProductById(productId);
	return res;
}


}
