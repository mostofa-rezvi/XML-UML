package dbcrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DbUtill;

public class DBCrud {

    static ResultSet rs;
    static PreparedStatement ps;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DbUtill db = new DbUtill();
        String insertSql = "insert into students (name, email, address, cellnumber)" + "values (?,?,?,?)";

        ps = db.getCon().prepareStatement(insertSql);

        ps.setString(1, "Person");
        ps.setString(2, "person@mail.com");
        ps.setString(3, "Dhaka");
        ps.setString(4, "0123456789");

        ps.executeUpdate();
        ps.close();
        db.getCon().close();

        String selectSql = "select * from students";

        ps = db.getCon().prepareStatement(selectSql);

        rs = ps.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String cellnumber = rs.getString("cellnumber");

            System.out.println("ID: " + id + "\nName: " + name + "\nMail: " + email + "\nAddress: " + address + "\nCell Number: " + cellnumber);

        }
        
        ps.close();
        rs.close();
        db.getCon().close();

    }

}
