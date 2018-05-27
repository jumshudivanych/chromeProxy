package com.sandbox50572;

import java.sql.*;

public class WorkToSqlite {

    int id;
    String ip;
    int port;

    public void setId(int id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;//TODO дописать добавление в БД
    }

    public String getIp(int id) {

        String result = null;

        try {
        //инициализация драивера базы данных
        Class.forName("org.sqlite.JDBC");
        //создание подключения
        Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:C:\\idea\\sqlite\\proxy.db");//строка подключения
        //создание sql запроса
        Statement stmt = connection.createStatement();
        //считывание из таблицы

            ResultSet resSet = stmt.executeQuery("SELECT * FROM socks5");
            resSet.getInt(id);//TODO попытка вынуть id
            String ip  = resSet.getString("ip");
            int port = resSet.getInt("port");
            result = ip + ":" + port;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //возвращаем ip + port
        return result;
    }

    public void work() {
        try {

            //TODO Добавить создание БД !!!
            //инициализация драивера базы данных
            Class.forName("org.sqlite.JDBC");
            //создание подключения
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\idea\\sqlite\\proxy.db");//строка подключения
            //в строке подключения либо полный путь к фаилу либо
            //имя фаила если в тои же папке
            System.out.println("Connected");

            //создание sql запроса
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO 'socks5' ('ip', 'port') VALUES ('101.255.45.22', '33555');");//TODO тут sql запрос
            System.out.println("Запись добавлена");

            //добавление записи
            stmt.execute("INSERT INTO 'socks5' ('ip', 'port') VALUES ('103.10.52.83', '10723');");//TODO тут sql запрос
            System.out.println("Запись добавлена");



            //добавление записи
            stmt.execute("INSERT INTO 'users' ('name', 'url', 'login', 'password') VALUES ('ivan', 'google.ru', 'ivanlykov028@gmail.com', '1234cani');");
            System.out.println("Запись добавлена");

            //Удаление данных из таблицы
            //stmt.execute("DELETE * FROM users");

            //считывание из таблицы
            ResultSet resSet = stmt.executeQuery("SELECT * FROM users");
            while(resSet.next())
            {
                int id = resSet.getInt("id");
                String  name = resSet.getString("name");
                String  url = resSet.getString("url");
                String  login = resSet.getString("login");
                String  password = resSet.getString("password");
                System.out.println( "ID = " + id );
                System.out.println( "name = " + name );
                System.out.println( "url = " + url );
                System.out.println( "login = " + login );
                System.out.println( "password = " + password );
                System.out.println("Таблица выведена");

                //TODO !!! Добавить закрытие базы данных в отдельных методах
                //connection.close();
                //stmt.close();
                //resSet.close();

                System.out.println("Соединения закрыты");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
