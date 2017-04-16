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
import DataPackage.Ship;

public class ShipDAO implements AllDAO<Ship> {

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
	public void insert(Ship ship) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO harbormanager.ships (ship_id , ship_name, shipcountry, shipmaxweight) VALUES (NULL ,?, ?, ?)");
			preparedStatement.setString(1, ship.getName());
			preparedStatement.setString(2, ship.getCountry());
			preparedStatement.setDouble(3, ship.getMaxWeight());
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Ship> select() {
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM harbormanager.ships");

			Ship ship = null;
			while (resultSet.next()) {
				ship = new Ship();
				ship.setId(Integer.parseInt(resultSet.getString("ship_id")));
				ship.setName(resultSet.getString("ship_name"));
				ship.setCountry(resultSet.getString("shipcountry"));
				ship.setMaxWeight(resultSet.getDouble("shipmaxweight"));

				shipList.add(ship);
			}

			ProductItem _item = null;
			for (Ship _ship : shipList) {
				resultSet = statement.executeQuery("SELECT * FROM harbormanager.ship_goods WHERE ship_id ='"
						+ String.valueOf(_ship.getId()) + "'");
				while (resultSet.next()) {
					_item = new ProductItem();
					_item.setId(resultSet.getInt("item_id"));
					_item.setName(resultSet.getString("item_name"));
					_item.setCount(resultSet.getInt("item_count"));

					_ship.getGoods().add(_item);
				}
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shipList;
	}

	public void delete(String name) {
		try {
			Statement statement = connection.createStatement();
			String sqlComand = "DELETE FROM harbormanager.ships WHERE ship_name='" + name + "'";
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
