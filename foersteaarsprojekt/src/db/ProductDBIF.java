package db;

import ctrl.DataAccessException;
import model.Product;

public interface ProductDBIF {
Product findProductById(int productId) throws DataAccessException;
}
