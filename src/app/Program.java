package app;

import java.sql.SQLException;

import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		/*
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_product");
			
		while (rs.next()) {
			System.out.println(rs.getLong("Id") + ", " + rs.getString("Name"));
		}
		*/
		
		Product p = new Product();
		p.setName("Pizza");
		
		System.out.println("Nome do produto = " + p.getName());
		
	}
}
