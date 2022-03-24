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

public class ProductDB implements ProductDbIF{
	//query: select id, supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, countryOfOrigin, stock, minStock, size, colour, calibre, type
	private static final String FIND_BY_PRODUCTNO_SQL = "select id, supplierId, name, productNo, description, "+
														"purchasePrice, salesPrice, rentPrice, "+
														"countryOfOrigin, stock, minStock, "+
														"size, colour, calibre, materiel, type "+
														"from product where ProductNo = ? ";
	private PreparedStatement findProductPS; 
	
	public ProductDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS = con.prepareStatement(FIND_BY_PRODUCTNO_SQL);

		}catch (SQLException e) {
			e.printStackTrace(); //TODO not finished 
		}
	}

	private List<Product> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Product> res = new ArrayList<>(); 
		try {
			while(rs.next()) {
				Product currProduct = buildObject(rs, fullAssociation); 
				res.add(currProduct); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1   2      	  3 	4		   5			6			   7		   8		  9 
	// id, supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, countryOfOrigin, 
	// 10	  11		12 	  13	  14	   15	    16
	// stock, minStock, size, colour, calibre, materiel type
	private Product buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Product product = new Product();
		
		try {
			product.setId(rs.getInt("id"));
			//TODO make association with supplierDB or sumthing
			//currProduct.setSupplierId(rs.getString("supplier")); 
			product.setName(rs.getString("name")); 
			product.setProductNo(rs.getString("productNo")); 
			product.setDescription(rs.getString("description")); 
			product.setPurchasePrice(rs.getDouble(0)); 
			product.setSalesPrice(rs.getDouble(0)); 
			product.setRentPrice(rs.getDouble(0)); 
			product.setCountryOfOrigin(rs.getString("countryOfOrigin"));
			product.setStock(rs.getInt(0)); 
			product.setMinStock(rs.getInt(0));
			if(fullAssociation) {
				
			}
			// Check to see what kind of product it is by checking for null values
			if (isClothing(rs)) {
				Clothing clothing = (Clothing) product;
				clothing.setSize(rs.getString(12));
				clothing.setColour(rs.getString(13));
				product = clothing;
			} else if (isEquipment(rs)){
				Equipment equipment = (Equipment) product;
				equipment.setType(rs.getString(16));
				product = equipment;
			} else if (isGunReplica(rs)) {
				GunReplica gunReplica = (GunReplica) product;
				gunReplica.setMateriel(rs.getString(15));
				gunReplica.setCalibre(rs.getString(14));
				product = gunReplica;
			} else {
				System.out.println("Something went very wrong");
			}
			
		}catch (SQLException e) {
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
