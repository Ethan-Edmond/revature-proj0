package com.ethanedmond.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
// import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class ConnectionSingleton {
    private static ConnectionSingleton instance;
    private Connection conn;
    private ConnectionSingleton() {
        try {
//            SQLServerDriver driver = new SQLServerDriver(); // TODO fix the sql server driver in production
//             URL propURL = ConnectionSingleton.class.getResource("/connection.properties");
//             InputStream propIn = propURL.openStream();
            FileInputStream propIn = new FileInputStream("C:\\Users\\Ethan\\Documents\\Revature\\proj0\\target\\classes\\connection.properties");
            Properties props = new Properties();
            props.load(propIn);
            String url = (String) props.get("url");
            String user = (String) props.get("user");
            String password = (String) props.get("password");
            this.conn = DriverManager.getConnection(url, user, password);
            propIn.close();
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }
    public static Connection getConn() {
        return getInstance().conn;
    }
}
