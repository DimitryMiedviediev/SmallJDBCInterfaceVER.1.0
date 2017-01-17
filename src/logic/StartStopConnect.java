package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dimitry on 12.01.17.
 */
public class StartStopConnect {

    public Connection connectToDataBase(String url, String name, String password) {
        Connection connection = null;

        try {
            System.out.println("--Trying to get DB access...");
            connection = DriverManager.getConnection(url, name, password);
            if (!connection.isClosed()) {
                System.out.println("--Connection to DB is active...");
            } else {
                System.err.println("--Cannot get DB access...");
                System.err.println("--Connection to DB is not active...");
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        if(connection==null){
            return null;
        }else {
            return connection;
        }
    }

    public void disconnectToDataBase(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                if (connection.isClosed()) {
//                    System.out.println("**********************");
                    System.out.println("--Connection to DataBase is disabled...");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
