package jdbcapplicationgui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCApplicationGUI {

    private Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/javastore";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "jee59";

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverName);

            con = DriverManager.getConnection(url, username, password);

            stmt = con.createStatement();

            String sql = "SELECT * FROM product";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");
                float salesPrice = rs.getFloat("salesPrice");

                System.out.println("ID: " + id + ": \nName: " + name + "\nUnit Price: " + unitPrice + "\nQuantity: " + quantity + "\nTotal Price: "
                        + totalPrice + "\nSales Price: " + salesPrice + "\n");
            }
        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println("Driver not found.");
            System.out.println("SQL Exception: " + ex.getMessage());

        }
    }
}
