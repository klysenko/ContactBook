package dao.impl;

import dao.ContactDao;
import model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ContactDbDao implements ContactDao {

    public static final String USER = "test";
    public static final String PASSWORD = "test";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ContactBook";

    public ContactDbDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = ((Connection) connection).createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS CONTACT(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE INT);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CONTACT (NAME, LASTNAME, AGE) VALUES(?, ?, ?);")) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setInt(3, contact.getAge());
            statement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Contact> getAll() {
        List<Contact> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM CONTACT;");

            while (rs.next()) {
                list.add(new Contact(rs.getInt("ID"), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteByName(String name) {
        String sql = "DELETE FROM CONTACT WHERE NAME = ? OR LASTNAME = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, name);
            int count = statement.executeUpdate();
            System.out.println("Count of deleted contacts: " + count);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void modifyByName(String name, String newFirstName, String newLastName) {
        String sql = "UPDATE CONTACT SET NAME = ?, LASTNAME = ? WHERE NAME = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setString(3, name);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
