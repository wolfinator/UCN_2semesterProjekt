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

	private final String FIND_BY_PRODUCTID_SQL = "select id, name, price, typeId, pt.id, pt.name from Product, ProductType pt "
			+ "where typeId = pt.id and id = ?;";
	private final String FIND_ALL_SQL = "select id, name, price, typeId, pt.id, pt.name from Product, ProductType pt;";

	private PreparedStatement ps_findById;
	private PreparedStatement ps_findAll;

	public ProductDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps_findById = con.prepareStatement(FIND_BY_PRODUCTID_SQL);
			ps_findAll = con.prepareStatement(FIND_ALL_SQL);

		} catch (SQLException e) {
			throw new DataAccessException("Fejl ved at initilisere ProductDB", e);
		}
	}

	public Product findProductById(int productId) throws DataAccessException {
		Product res = null;
		try {
			ps_findById.setInt(1, productId);
			ResultSet rs = ps_findById.executeQuery();
			if (rs.next())
				res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Fejl ved at finde product med productId = " + productId, e);

		}
		return res;
	}

	public List<Product> findAll() throws DataAccessException {
		List<Product> res = null;
		try {
			ResultSet rs = ps_findAll.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Fejl ved at finde alle produkter", e);
		}
		return res;
	}

	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Product currProduct = buildObject(rs);
				res.add(currProduct);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Fejl ved at bygge Product objekter", e);
		}
		return res;
	}

	// "select id, name, price, typeId, pt.id, pt.name from Product, ProductType pt
	// "
	// + "where typeId = pt.id;";
	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product product = null;
		try {
			product = new Product();
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			ProductType pt = new ProductType();
			pt.setId(rs.getInt(5));
			pt.setName(rs.getString(6));
			product.setType(pt);

		} catch (SQLException e) {
			throw new DataAccessException("Fejl ved at bygge Product objekt", e);
		}
		return product;
	}
}
