package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Product;

public class ProductDB implements ProductDbIF{
	private ProductDbIF productDB;
	//query: select id, supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, countryOfOrigin, stock, minStock, size, colour, calibre, type
	private static final String FIND_PRODUCT_Q = "select id, supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, countryOfOrigin, stock, minStock, size, colour, calibre, type from product where ProductNo = ? ";
	private PreparedStatement findProductPS; 
	
	private static final String FIND_BY_PRODUCT_OR_NAME_Q = FIND_PRODUCT_Q + "where product or name like ?";
	private PreparedStatement findByProductOrNamePS; 
	
	private static final String FIND_BY_PRODUCTID_Q = FIND_PRODUCT_Q + "where productID = ?"; 
	private PreparedStatement findByProductIDPS;
	
	private static final String INSERT_Q = "insert into product (id, supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, countryOfOrigin, stock, minStock, size, colour, calibre, type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private PreparedStatement insertPS;
	 
	
	public ProductDB(ProductDbIF productDB) throws DataAccessException {
		this.productDB = productDB; 
		init(); 
	}
	
	public ProductDB() throws DataAccessException {
		productDB = new ProductDB(this); 
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProductPS = con.prepareStatement(FIND_PRODUCT_Q);
			findByProductOrNamePS = con.prepareStatement(FIND_BY_PRODUCT_OR_NAME_Q);
			findByProductIDPS = con.prepareStatement(FIND_BY_PRODUCTID_Q);
			insertPS = con.prepareStatement(INSERT_Q);
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

	private Product buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Product currProduct = new Product();
		try {
			currProduct.setId(rs.getInt("id")); 
			//currProduct.setSupplierId(rs.getString("supplier")); 
			currProduct.setName(rs.getString("name")); 
			currProduct.setProductNo(rs.getString("productNo")); 
			currProduct.setDescription(rs.getString("description")); 
			currProduct.setPurchasePrice(rs.getDouble(0)); 
			currProduct.setSalesPrice(rs.getDouble(0)); 
			currProduct.setRentPrice(rs.getDouble(0)); 
			currProduct.setCountryOfOrigin(rs.getString("countryOfOrigin"));
			currProduct.setStock(rs.getInt(0)); 
			currProduct.setMinStock(rs.getInt(0)); 
			if(fullAssociation) {
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return currProduct;
	}

	public List<Product> findAll(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product insert(Product product) {
		// TODO Auto-generated method stub
		return null;
	}


	public Product findByProductID(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Product findByProductNo(String productNo) throws DataAccessException {
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


	@Override
	public Product findByProductID(int productID, boolean b) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Product> findProduct(boolean fullAssociation) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
