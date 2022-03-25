package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Clothing;
import model.Equipment;
import model.GunReplica;
import model.Product;

public class ProductDB implements ProductDbIF {
	// query: select id, supplierId, name, productNo, description, purchasePrice,
	// salesPrice, rentPrice, countryOfOrigin, stock, minStock, size, colour,
	// calibre, type
	private static final String FIND_BY_PRODUCTNO_SQL = "select id, supplierId, name, productNo, description, "
			+ "purchasePrice, salesPrice, rentPrice, " + "countryOfOrigin, stock, minStock, "
			+ "size, colour, calibre, materiel, type " + "from product where ProductNo = ? ";
	private PreparedStatement findProductPS;

	public ProductDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS = con.prepareStatement(FIND_BY_PRODUCTNO_SQL);

		} catch (SQLException e) {
			e.printStackTrace(); // TODO not finished
		}
	}

	private List<Product> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Product currProduct = buildObject(rs, fullAssociation);
				res.add(currProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1 2 3 4 5 6 7 8 9
	// id, supplierId, name, productNo, description, purchasePrice, salesPrice,
	// rentPrice, countryOfOrigin,
	// 10 11 12 13 14 15 16
	// stock, minStock, size, colour, calibre, materiel type
	private Product buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Product product = null;

		try {
			// Check to see what kind of product it is by checking for null values
			if (isClothing(rs)) {
				product = new Clothing();
				Clothing clothing = (Clothing) product;
				clothing.setSize(rs.getString(12));
				clothing.setColour(rs.getString(13));
				product = clothing;
			} else if (isEquipment(rs)) {
				product = new Equipment();
				Equipment equipment = (Equipment) product;
				equipment.setType(rs.getString(16));
				product = equipment;
			} else if (isGunReplica(rs)) {
				product = new GunReplica();
				GunReplica gunReplica = new GunReplica();
				gunReplica.setCalibre(rs.getString(14));
				gunReplica.setMateriel(rs.getString(15));
				product = gunReplica;
			} else {
				System.out.println("Something went very wrong");
			}

			product.setId(rs.getInt(1));
			// TODO make association with supplierDB or sumthing
			// currProduct.setSupplierId(rs.getString("supplier"));
			product.setName(rs.getString(3));
			product.setProductNo(rs.getString(4));
			product.setDescription(rs.getString(5));
			product.setPurchasePrice(rs.getDouble(6));
			product.setSalesPrice(rs.getDouble(7));
			product.setRentPrice(rs.getDouble(8));
			product.setCountryOfOrigin(rs.getString(9));
			product.setStock(rs.getInt(10));
			product.setMinStock(rs.getInt(11));
			if (fullAssociation) {

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	private boolean isGunReplica(ResultSet rs) {
		boolean res = true;
		try {
			rs.getString(14);
			rs.getString(15);
		} catch (SQLException e) {
			res = false;
		}
		return res;
	}

	private boolean isEquipment(ResultSet rs) {
		boolean res = true;
		try {
			rs.getString(16);
		} catch (SQLException e) {
			res = false;
		}
		return res;
	}

	private boolean isClothing(ResultSet rs) {
		boolean res = true;
		try {
			rs.getString(12);
			rs.getString(13);
		} catch (SQLException e) {
			res = false;
		}
		return res;
	}

	@Override
	public Product findProductByNo(String productNo) throws DataAccessException {
		Product res = null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS.setString(1, productNo);
			ResultSet rs = findProductPS.executeQuery();
			rs.next();
			res = buildObject(rs, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return res;
	}
}
