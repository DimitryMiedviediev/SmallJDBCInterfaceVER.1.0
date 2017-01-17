package main;


import logic.ActionsWithMethods;
import logic.StartStopConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by Dimitry on 11.01.17.
 */
public class Start {

    private static String URL = "jdbc:mysql://localhost:3306/?useSSL=false";
    private static String NAME = null;
    private static String PASSWORD = null;
    public static StartStopConnect startStopConnect = new StartStopConnect();
    public static ActionsWithMethods action = new ActionsWithMethods();

    public static void main(String[] args) {
        Connection connection = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--Hello, it\'s JDBC-Interface.");
        System.out.println("--To start work you need input your NAME and PASSWORD from database.");
        System.out.println("--If you want to complete work with interface, input \'exit\'.");

        while (true) {
            try {
                System.out.println();
                System.out.println("--Please input your NAME...");
                NAME = input(reader.readLine(), connection);
                System.out.println("--Please input your PASSWORD...");
                PASSWORD = input(reader.readLine(), connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = startStopConnect.connectToDataBase(URL, NAME, PASSWORD);
            if (!(connection == null)) {
                break;
            } else {
                System.out.println("--Cannot create connection. Please, try again...");
            }
        }

        while (true) {
            System.out.println();
            System.out.println("--Please, input you command...");
            try {
                String s = input(reader.readLine(), connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("***" + action.positionOfSchema());
        }


    }

    public static String input(String param, Connection connection) {
        if (param.equals("exit")) {
            startStopConnect.disconnectToDataBase(connection);
            System.out.println("--Bye.");
            System.exit(0);
        } else if (param.equals("show db")) {
            action.showDataBases(connection);
        } else if (param.equals("show tables")) {
            action.showTables(connection);
        } else if (param.equals("back")) {
            action.back();
        } else if (param.equals("create schema")) {
            action.createSchema(connection);
        } else if (param.equals("drop schema")) {
            action.dropSchema(connection);
        } else if (param.equals("create table")) {
            action.createTable(connection);
        } else if (param.equals("use schema")) {
            action.useSchema();
        } else if (param.equals("remove table")) {
            action.removeTable(connection);
        } else if (param.equals("read table")) {
            action.readTable(connection);
        } else if (param.equals("insert into table")) {
            action.insertIntoTable(connection);
        } else if (param.equals("remove table")) {
            action.removeFromTable(connection);
        } else if (param.equals("position")) {
            action.givePosition();
        } else if (param.equals("help")){
            System.out.println("Command: \'exit\' - exit from JDBC-Interface.");
            System.out.println("Command: \'stop\' - stopping process of input command.");
            System.out.println("Command: \'back\' - move your position to root of DB.");
            System.out.println("Command: \'position\' - give your position in DB.");
            System.out.println("Command: \'show db\' - give you list of databases.");
            System.out.println("Command: \'use schema\' - change you position to schema.");
            System.out.println("Command: \'create schema\' - created new schema.");
            System.out.println("Command: \'drop schema\' - delete changed schema.");
            System.out.println("Command: \'show tables\' - give you list of tables in schema.");
            System.out.println("Command: \'create table\' - create new table.");
            System.out.println("Command: \'remove table\' - delete changed table.");
            System.out.println("Command: \'read table\' - give you data list of this table.");
            System.out.println("Command: \'insert into table\' - put values in table.");
        }
        return param;
    }



}
