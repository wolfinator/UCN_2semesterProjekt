package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Product;
import model.ProductType;

public class ProductDB implements ProductDBIF {

	private static final String FIND_BY_PRODUCTID_SQL = "select id, name, price, typeId, pt.id, pt.name from Product, ProductType pt "
			+ "where typeId = pt.id;";
	private PreparedStatement findProductPS;

	public ProductDB()throws DataAccessException{
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS = con.prepareStatement(FIND_BY_PRODUCTID_SQL);

		} catch (SQLException e) {
			e.printStackTrace(); // TODO not finished
		}
	}
	
	private List<Product> buildObjects(ResultSet rs) throws DataAccessException{
		List<Product> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Product currProduct = buildObject(rs);
				res.add(currProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// "select id, name, price, typeId, pt.id, pt.name from Product, ProductType pt "
	//+ "where typeId = pt.id;";
	private Product buildObject(ResultSet rs) {
		Product product = new Product();
		try {
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			ProductType pt = new ProductType();
			pt.setId(rs.getInt(5));
			pt.setName(rs.getString(6));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	
	public Product findProductById(int productId) throws DataAccessException {
		Product res = null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS.setInt(1, productId);
			ResultSet rs = findProductPS.executeQuery();
			rs.next();
			res = buildObject(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return res;
	}
}


