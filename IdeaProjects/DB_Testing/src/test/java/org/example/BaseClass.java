package org.example;


import org.testng.annotations.*;

import java.sql.*;

public class BaseClass
{
    public static Connection con;

    @BeforeClass
    public static Connection setUp() throws SQLException{
        try {
            String dburl="jdbc:mysql://localhost:3306/Crud";
            String username="root";
            String password="Ni.tish3386";
            con= DriverManager.getConnection(dburl,username,password);
        }
        catch (SQLException sqe){
            System.out.println(sqe.getErrorCode());
            System.out.println(sqe.getSQLState());
            System.out.println(sqe.getMessage());
            sqe.printStackTrace();
        }
        return con;
    }
    @Test
    public void insert_Data() throws SQLException {
        con = BaseClass.setUp();
        PreparedStatement ps = con.prepareStatement("insert into user (id,name) values(?,?)");
        ps.setString(1, "1");
        ps.setString(2, "Nitish");
        ps.addBatch();

        ps.setString(1, "2");
        ps.setString(2, "Harshal");
        ps.addBatch();

        ps.setString(1,"3");
        ps.setString(2,"Anwar");
        ps.addBatch();

        ps.executeBatch();


        read_Data();
    }
    @Test
    public void read_Data() throws SQLException {
        con = BaseClass.setUp();
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("Select * from user");
        while (rs.next()) {
            String userId = rs.getString(1);
            String userName = rs.getString(2);
            System.out.println(" " + userId + " " + userName);
        }
        rs.close();
        ps.close();
    }
    @Test
    public void update_Data() throws SQLException {
        con = BaseClass.setUp();
        PreparedStatement ps = con.prepareStatement("update user set Name='Anwar_Shaikh' where id=2");
        ps.executeUpdate();
        read_Data();
    }
    @Test
    public void delete_Data() throws SQLException {
        con = BaseClass.setUp();
        PreparedStatement ps = con.prepareStatement("delete from user where Name='Nitish'");
        ps.executeUpdate();
        read_Data();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        con.close();
    }

}
