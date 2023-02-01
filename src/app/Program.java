package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DB;
import db.DbException;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("SELECT * FROM tb_order "
				+ "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id "
				+ "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");
			
		Map<Long, Order> mapOrder = new HashMap<>();
		Map<Long, Product> mapProduct = new HashMap<>(); 
		while (rs.next()) {
			
			Long orderId = rs.getLong("order_id");
			if (mapOrder.get(orderId) == null) {
				Order order = instantiateOrder(rs);
				mapOrder.put(orderId, order);
			}
			
			Long productId = rs.getLong("product_id");
			if (mapProduct.get(productId) == null) {
				Product product = instantiateProduct(rs);
				mapProduct.put(productId, product);
			}
			
			mapOrder.get(orderId).getProducts().add(mapProduct.get(productId));
			
			//System.out.println(rs.getLong("Id") + ", " + rs.getString("Name"));
		}
		
		for (Long orderId : mapOrder.keySet()) {
			System.out.printf("\n" + mapOrder.get(orderId));
			for (Product p : mapOrder.get(orderId).getProducts()) {
				System.out.printf("\n\t" + p);
			}
		}
		
		
	}

	private static Product instantiateProduct(ResultSet rs) {
		Product p = new Product();
		try {
			p.setId(rs.getLong("product_id"));
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
	
	private static Order instantiateOrder(ResultSet rs) {
		Order order = new Order();
		try {
			order.setId(rs.getLong("order_id"));
			order.setLatitude(rs.getDouble("latitude"));
			order.setLongitude(rs.getDouble("longitude"));
			order.setMoment(rs.getTimestamp("moment").toInstant());
			order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		return order;
	}
	
	
}
