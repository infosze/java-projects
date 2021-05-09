package util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseConnect {

	private static ComboPooledDataSource cpds;

	static {
		try {
			cpds = new ComboPooledDataSource();

			String resourceName = "database.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties properties = new Properties();
			try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				properties.load(resourceStream);
			}
			cpds.setDriverClass(properties.getProperty("DRIVER_CLASS"));
			cpds.setJdbcUrl(properties.getProperty("DB_CONNECTION_URL"));
			cpds.setUser(properties.getProperty("DB_USER"));
			cpds.setPassword(properties.getProperty("DB_PWD"));
			// the settings below are optional
			// c3p0 can work with defaults
			cpds.setInitialPoolSize(5);
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
		} catch (PropertyVetoException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}

}
