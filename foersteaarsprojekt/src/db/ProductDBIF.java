package db;

import ctrl.DataAccessException;
import model.Product;

public interface ProductDBIF {
 Product findByName(String name) throws DataAccessException;
}
