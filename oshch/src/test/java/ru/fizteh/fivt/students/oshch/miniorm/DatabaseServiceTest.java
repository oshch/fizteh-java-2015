package ru.fizteh.fivt.students.oshch.miniorm;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseServiceTest {
    @DatabaseService.Table(name = "users")
    public static class User {

        @DatabaseService.Column(name = "name")
        @DatabaseService.PrimaryKey
        public String name;

        @DatabaseService.Column(name = "age")
        public int age;

        public User(String newName, int newAge) {
            this.name = newName;
            this.age = newAge;
        }

        public User() {
        }

        public final void setName(String newName) {
            this.name = newName;
        }

        public final void setAge(int newAge) {
            this.age = newAge;
        }

        @Override
        public final String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + ", age=" + age
                    + '}';
        }
    }

    @Test
    public void testInsert() throws Exception {

        final int testedUsers = 150;
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(255), age int, PRIMARY KEY(name))");
            for (int i = 0; i <= testedUsers; ++i) {
                int age = i * i + 5;
                String name = "User" + i;
                dbs.insert(new User(name, age));
            }

            String modAge = "4";

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE age % 10 != ? ORDER BY age");
            preparedStatement.setString(1, modAge);
            ResultSet rs = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                users.add(user);
            }

            List<User> correctUsers = new ArrayList<>();
            for (int i = 0; i <= testedUsers; ++i) {
                int age = i * i + 5;
                if (age % 10 != 4) {
                    String name = "User" + i;
                    correctUsers.add(new User(name, age));
                }
            }

            assertEquals(correctUsers.size(), users.size());

            for (int i = 0; i < correctUsers.size(); ++i) {
                assertEquals(correctUsers.get(i).toString(), users.get(i).toString());
            }
            statement.close();
        }
    }

    @Test
    public void testQueryById() throws Exception {
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(255), age int, PRIMARY KEY(name))");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Gena', 200)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Lena', 300)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Pena', 400)");

            String key = "Lena";
            User answer = dbs.queryById(key);
            assertNotNull(answer);
            assertEquals("User{name='Lena', age=300}", answer.toString());
            statement.close();
        }
    }

    @Test
    public void testQueryForAll() throws Exception {
        final int testedUsers = 150;
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(255), age int, PRIMARY KEY(name))");
            List<User> correctUsers = new ArrayList<>();

            for (int i = 0; i <= testedUsers; ++i) {
                int age = i * i + 5;
                String name = "User" + i;
                dbs.insert(new User(name, age));
                correctUsers.add(new User(name, age));
            }

            List<User> users = dbs.queryForAll();
            assertEquals(correctUsers.size(), users.size());
            for (int i = 0; i < users.size(); ++i) {
                assertEquals(correctUsers.get(i).toString(), users.get(i).toString());
            }
            statement.close();
        }
    }

    @Test
    public void testUpdate() throws Exception {
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(255), age int, PRIMARY KEY(name))");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Gena', 200)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Lena', 300)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Pena', 400)");

            User toChange = new User("Lena", 305);

            dbs.update(toChange);

            String key = "Lena";
            User answer = dbs.queryById(key);

            assertNotNull(answer);
            assertEquals("User{name='Lena', age=305}", answer.toString());
            statement.close();
        }
    }

    @Test
    public void testDelete() throws Exception {
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(255), age int, PRIMARY KEY(name))");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Gena', 200)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Lena', 300)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Pena', 400)");

            User toChange = new User("Lena", 300);

            dbs.delete(toChange);

            String key = "Lena";
            User answer = dbs.queryById(key);

            // Должен вернуть null, то есть не найти ничего
            assertNull(answer);
            statement.close();
        }
    }

    @Test
    public void testCreateTable() throws Exception {
        DatabaseService dbs = new DatabaseService(User.class);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            dbs.createTable();

            final int testedUsers = 150;

            List<User> correctUsers = new ArrayList<>();

            for (int i = 0; i <= testedUsers; ++i) {
                int age = i * i;
                String name = "User" + i;
                dbs.insert(new User(name, age));
                correctUsers.add(new User(name, age));
            }

            List<User> users = dbs.queryForAll();

            assertEquals(correctUsers.size(), users.size());
            for (int i = 0; i < users.size(); ++i) {
                assertEquals(correctUsers.get(i).toString(), users.get(i).toString());
            }
            statement.close();
        }
    }

    @Test
    public void testDropTable() throws Exception{
        DatabaseService dbs = new DatabaseService(User.class);
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./miniORM2")) {
            dbs.createTable();
            dbs.insert(new User ("Lena", 300));
            dbs.dropTable();
            boolean tableExists = false;
            ResultSet result = connection.getMetaData().getTables(null, null, "users", null);
            if ( result.next() ) {
                tableExists = true;
            }
            assertFalse(tableExists);
            result.close();
        }
    }

}
