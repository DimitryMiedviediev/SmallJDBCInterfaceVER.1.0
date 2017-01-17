package logic;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dimitry on 11.01.17.
 */
public class ActionsWithDB {

    void showDataBases(Connection connection) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getCatalogs();

            System.out.println("--Getting DataBase catalog...");
//            System.out.println("**********************");
            System.out.println("--List of schemas:");
            int n = 1;
            while (rs.next()) {
                System.out.println(n + ". " + rs.getString(1));
                n++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void useSchema(Connection connection, String schema) {
        try {

            DatabaseMetaData meta = connection.getMetaData();
            String[] type = {"TABLE"};
            ResultSet rs = meta.getTables(schema, null, "%", type);

            ArrayList<String> tables = new ArrayList<>();

//            System.out.println("**********************");
            System.out.println("--Tables of \'" + schema + "\':");
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
                tables.add(table);
            }
            if (!tables.isEmpty()) {
                for (int i = 0; i < tables.size(); i++) {
                    System.out.println((i+1) + ". " + tables.get(i));
                }
            } else {
                System.err.println("--Schema is empty, or does not exist...");
            }
            setPositionOfSchema(schema);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createNewSchema(Connection connection, String nameOfNewSchema) {
        try {
//            System.out.println("**********************");
            System.out.println("--Trying to create new schema...");
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE " + nameOfNewSchema);
            System.out.println("--New schema " + nameOfNewSchema + " was created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void dropSchema(Connection connection, String nameOfSchema) {
        try {
            System.out.println("--Trying to delete schema...");
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE " + nameOfSchema);
            System.out.println("--Schema " + nameOfSchema + " was deleted...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTable(Connection connection, String nameSchema, String nameNewTable, String configureOfTable) {
        try {
            connection.setCatalog(nameSchema);
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE " + nameNewTable + "(" + configureOfTable + ")");
//            System.out.println("**********************");
            System.out.println("--New table with name \"" + nameNewTable + "\" and configure: \"" + configureOfTable + "\" was created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setPositionOfSchema(nameSchema);
    }

    void removeTable(Connection connection, String nameSchema, String nameTable) {
        try {
            connection.setCatalog(nameSchema);
            Statement st = connection.createStatement();
            st.executeUpdate("DROP TABLE " + nameTable);
//            System.out.println("**********************");
            System.out.println("--Table with name \"" + nameTable + "\" was removed...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setPositionOfSchema(nameSchema);
    }

    void readTable(Connection connection, String nameSchema, String nameTable) {
        try {
            connection.setCatalog(nameSchema);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* FROM " + nameTable);
            ResultSetMetaData meta = resultSet.getMetaData();

//            System.out.println("**********************");
            System.out.println("--List from table \"" + nameTable+ "\":");
            int l = 1;
            while (resultSet.next()) {
                System.out.print(l + ". { ");
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String str = meta.getColumnName(i);
                    System.out.print(str + ": " + resultSet.getString(str) + "; ");
                }
                System.out.print("}");
                System.out.println();
                l = l + 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        setPositionOfSchema(nameSchema);
    }

    void insertIntoTable(Connection connection, String nameSchema, String nameTable, String values) {
        try {
            connection.setCatalog(nameSchema);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO " + nameTable + " VALUES " + "(" + values + ")");
//            System.out.println("**********************");
            System.out.println("--New values in table \"" + nameTable + "\" was add successful...");
        } catch (SQLException e) {
            System.out.println(e);
        }
        setPositionOfSchema(nameSchema);
    }

    void removeFromTable(Connection connection, String nameSchema, String nameTable, String configParam, String value) {
        try {
            connection.setCatalog(nameSchema);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + nameTable + " WHERE " + configParam + "=" + "\'" + value + "\'");
//            System.out.println("**********************");
            System.out.println("--Line with parameter \"" + configParam + "=" + value + "\" was remove from table \"" + nameTable + "\" successful...");
        } catch (SQLException e) {
            System.out.println(e);
        }
        setPositionOfSchema(nameSchema);
    }


    String positionOfSchema = null;

    String getPositionOfSchema() {
        return positionOfSchema;
    }

    void setPositionOfSchema(String positionOfSchema) {
        this.positionOfSchema = positionOfSchema;
    }

    String positionOfTable = null;

    public String getPositionOfTable() {
        return positionOfTable;
    }

    void setPositionOfTable(String positionOfTable) {
        this.positionOfTable = positionOfTable;
    }
}
