package logic;

import logic.ActionsWithDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Created by Dimitry on 12.01.17.
 */
public class ActionsWithMethods {
    public ActionsWithDB actionsWithDB = new ActionsWithDB();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    Connection connection = null;

    public void showDataBases(Connection connection) {
        actionsWithDB.showDataBases(connection);
    }

    public void useSchema() {
        while (true) {
            System.out.println("--Please, input schema what you want to use...");
            try {
                String schema = reader.readLine();
                if (schema.equals("stop")) {
                    break;
                }
                actionsWithDB.setPositionOfSchema(schema);
                System.out.println("--Schema \'" + schema + "\' is ready to use...");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void showTables(Connection connection) {
        while (true) {
            System.out.println("--What schema you want to show?");
            try {
                String schema = positionOfSchema();
                if (schema == null) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.useSchema(connection, schema);
                } else {
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.useSchema(connection, schema);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void createSchema(Connection connection) {
        while (true) {
            System.out.println("--Please, input name of new DB...");
            try {
                String schema = reader.readLine();
                if (schema.equals("stop")) {
                    break;
                }
                actionsWithDB.createNewSchema(connection, schema);
                actionsWithDB.setPositionOfSchema(schema);

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void dropSchema(Connection connection) {
        while (true) {
            System.out.println("--Please, input name of schema...");
            try {
                String schema = reader.readLine();
                if (schema.equals("stop")) {
                    break;
                }
                actionsWithDB.dropSchema(connection, schema);
                actionsWithDB.setPositionOfSchema(null);

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void createTable(Connection connection) {
        while (true) {
            try {
                String schema = positionOfSchema();
                if (schema == null) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input name of new table...");
                    String table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input configure of new table...");
                    String configure = reader.readLine();
                    if (configure.equals("stop")) {
                        break;
                    }
                    actionsWithDB.createTable(connection, schema, table, configure);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else {
                    System.out.println("--Please, input name of new table...");
                    String table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input configure of new table...");
                    String configure = reader.readLine();
                    if (configure.equals("stop")) {
                        break;
                    }
                    actionsWithDB.createTable(connection, schema, table, configure);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void removeTable(Connection connection) {
        while (true) {
            try {
                String schema = positionOfSchema();
                if (schema == null) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input name of new table...");
                    String table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.removeTable(connection, schema, table);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(null);
                } else {
                    System.out.println("--Please, input name of new table...");
                    String table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.removeTable(connection, schema, table);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void readTable(Connection connection) {
        while (true) {
            try {
                String schema = positionOfSchema();
                String table = positionOfTable();
                if ((schema == null) && (table == null)) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.readTable(connection, schema, table);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else if (!(schema == null) && (table == null)) {
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.readTable(connection, schema, table);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else if (!(schema == null) && !(table == null)) {
                    actionsWithDB.readTable(connection, schema, table);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void insertIntoTable(Connection connection) {
        while (true) {
            try {
                String schema = positionOfSchema();
                String table = positionOfTable();
                if (schema == null && table == null) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input values of your configuration...");
                    String values = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.insertIntoTable(connection, schema, table, values);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else if (!(schema == null) && (table == null)) {
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input values of your configuration...");
                    String values = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.insertIntoTable(connection, schema, table, values);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else {
                    System.out.println("--Please, input values of your configuration...");
                    String values = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    actionsWithDB.insertIntoTable(connection, schema, table, values);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void removeFromTable(Connection connection) {
        while (true) {
            try {
                String schema = positionOfSchema();
                String table = positionOfTable();
                if ((schema == null) && (table == null)) {
                    System.out.println("--Please, input name of schema...");
                    schema = reader.readLine();
                    if (schema.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input config of value, that you want to delete some values...");
                    String configParam = reader.readLine();
                    if (configParam.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input value...");
                    String value = reader.readLine();
                    if (value.equals("stop")) {
                        break;
                    }
                    actionsWithDB.removeFromTable(connection, schema, table, configParam, value);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else if (!(schema == null) && (table == null)) {
                    System.out.println("--Please, input name of table...");
                    table = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input config of value, that you want to delete some values...");
                    String configParam = reader.readLine();
                    if (configParam.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input value...");
                    String value = reader.readLine();
                    if (value.equals("stop")) {
                        break;
                    }
                    actionsWithDB.removeFromTable(connection, schema, table, configParam, value);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                } else {
                    System.out.println("--Please, input config of value, that you want to delete some values...");
                    String configParam = reader.readLine();
                    if (configParam.equals("stop")) {
                        break;
                    }
                    System.out.println("--Please, input value...");
                    String value = reader.readLine();
                    if (table.equals("stop")) {
                        break;
                    }

                    actionsWithDB.removeFromTable(connection, schema, table, configParam, value);
                    actionsWithDB.setPositionOfSchema(schema);
                    actionsWithDB.setPositionOfTable(table);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void back() {
        actionsWithDB.setPositionOfSchema(null);
        actionsWithDB.setPositionOfTable(null);
        System.out.println("--You in DB...");
    }

    public void givePosition() {
        System.out.print("--Your position is: ");
        String schema = positionOfSchema();
        String table = positionOfTable();
        if ((schema == null) && (table == null)) {
            System.out.print("root of DataBase.");
        } else if (!(schema == null) && (table == null)) {
            System.out.print(" schema \'" + positionOfSchema() + "\'");
        } else if (!(schema == null) && !(table == null)) {
            System.out.print(" schema \'" + positionOfSchema() + "\' table \'" + positionOfTable() + "\'");
        }
    }

    String positionOfSchema() {
        return actionsWithDB.getPositionOfSchema();
    }

    String positionOfTable() {
        return actionsWithDB.getPositionOfTable();
    }
}
