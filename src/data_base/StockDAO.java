package data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data_base.AllDAO;

import DataPackage.ProductItem;

public class StockDAO implements AllDAO<ProductItem> {
	
	Connection connection = null;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connection == null)
				connection = DriverManager.getConnection("jdbc:mysql://localhost/harbormanager", "root", "root");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return connection;
	}

	@Override
	public void insert(ProductItem item) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO harbormanager.stock (stock_id , name, count, weight) VALUES (NULL ,?, ?, ?)");
			preparedStatement.setString(1, item.getName());
			preparedStatement.setInt(2, item.getCount());
			preparedStatement.setDouble(3, item.getWeight());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<ProductItem> select() {
		ArrayList<ProductItem> itemList = new ArrayList<ProductItem>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM harbormanager.stock");

			ProductItem item = null;
			while (resultSet.next()) {
				item = new ProductItem();
				item.setId(Integer.parseInt(resultSet.getString("stock_id")));
				item.setName(resultSet.getString("name"));
				item.setCount(resultSet.getInt("count"));
				item.setWeight(resultSet.getDouble("weight"));

				itemList.add(item);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	// public List<ProductItem> search(String search, String text) {
	// List<ProductItem> itemList = new LinkedList<ProductItem>();
	// try {
	// Statement statement = connection.createStatement();
	// ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog.audio
	// WHERE "+search+"='"+text+"'");
	//
	// ProductItem item = null;
	// while(resultSet.next()){
	// item = new ProductItem();
	// item.setId(Integer.parseInt(resultSet.getString("id")));
	// item.setName(resultSet.getString("name"));
	// item.setCount(resultSet.getInt("count"));
	// item.setWeight(resultSet.getDouble("weight"));
	//
	// itemList.add(item);
	// }
	// resultSet.close();
	// statement.close();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return itemList;
	// }

	public void delete(String name) {
		try {
			Statement statement = connection.createStatement();
			String sqlComand = "DELETE FROM harbormanager.stock WHERE name='" + name + "'";
			statement.executeUpdate(sqlComand);
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}
}
