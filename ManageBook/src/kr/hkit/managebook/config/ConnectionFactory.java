package kr.hkit.managebook.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import kr.hkit.managebook.jdbc.DBProperties;

public class ConnectionFactory {
	private static final ConnectionFactory instance = new ConnectionFactory();
	private Properties prop;

	public static Connection getConnection() {
		return instance.createConnection();
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(prop.getProperty("Url"), prop);
		} catch (SQLException e) {
			System.err.printf("Error : Unable to Connection DataBase");
		}
		return connection;
	}

	private ConnectionFactory() {
		prop = DBProperties.loadProperties(Config.PROPERTIES_PATH);
		// DBProperties.prnProperties(prop);
		try {
			Class.forName(prop.getProperty("Driver"));

		} catch (ClassNotFoundException e) {
			System.err.printf("MySQL Driver not Found!!");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
