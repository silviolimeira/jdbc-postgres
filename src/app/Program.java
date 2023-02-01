package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_product");
			
		while (rs.next()) {
			
			Product p = instantiateProduct(rs);
			
			//System.out.println(rs.getLong("Id") + ", " + rs.getString("Name"));
			System.out.println(p);
		}
		
		
	}

	private static Product instantiateProduct(ResultSet rs) {
		Product p = new Product();
		try {
			p.setId(rs.getLong("id"));
			p.setDescription(rs.getString("description"));
			p.setName(rs.getString("name"));
			p.setImageUri(rs.getString("image_uri"));
			p.setPrice(rs.getDouble("price"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		return p;
	}
}
