package ru.geekbrains.j3.ht2;

import java.sql.*;

public class myDb {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main (String[] args) {
        try {
            connect();
            createTable();
            addinfo();
            getInfo();
            deleteInfo(1);
            deleteTable();


            disconnect();
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
        statement = connection.createStatement();
    }
    private static void disconnect() throws SQLException {
        connection.close();
    }
    
    //задание 1 создаём таблицу
    private static void createTable() throws Exception {
    statement.execute("CREATE TABLE mynewtable (id INTEGER PRIMARY KEY AUTOINCREMENT, names TEXT, status INTEGER);");
    }


    //задание 2 добавляем запись
    private static void addinfo() throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO mynewtable (names, status) values (?, ?);");
        preparedStatement.setString(1, "dasha");
        preparedStatement.setInt(2, 0);
        preparedStatement.executeUpdate();

    }

    //задание 3 получаем запись
    private static void getInfo() throws Exception{

        ResultSet rs = statement.executeQuery("select * from mynewtable");
         while (rs.next()){
             System.out.println(rs.getInt(1) + " " + rs.getString("names") + "" + rs.getString("status"));
         }

    }

    //задание 4 удаляем значение (не понял по заданию надо все или один элемент) но всё удалить легче, поэтому удаляем одно значение
    private static void deleteInfo(int id) throws Exception {
        String sql = "DELETE FROM mynewtable WHERE id = ?";


        preparedStatement = connection.prepareStatement("DELETE FROM mynewtable WHERE id = ?;");


            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    //задание 5 просто удаляем таблицу
    private static void deleteTable() throws Exception {
        statement.execute("DROP TABLE mynewtable;");
    }


    }





