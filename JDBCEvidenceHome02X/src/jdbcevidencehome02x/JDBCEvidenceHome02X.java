package jdbcevidencehome02x;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbUtil;

public class JDBCEvidenceHome02X {

    public static DbUtil db = new DbUtil();
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql = "";

    public static void main(String[] args) {
        
//        updateStudent("Nusrat", "nusrat@gmail.com", "Mirpur-1, Dhakal", "01234567899", 1);

        saveStudent("Rezvi", "rezvi@gmail.com", "Azimpur Colony, Dhaka.", "01472583699");
        
        deleteStudent(2);

        showStudent();

    }

    public static void showStudent() {

        sql = "select * from student";

        try {
            ps = db.getCon().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");

                System.out.println("ID: " + id + " Name: " + name + " Email: " + email + " Address: " + address + " Cell: " + cell);

            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCEvidenceHome02X.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void saveStudent(String name, String email, String address, String cell) {

        sql = "insert into student(name, email, address, cell) values(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, cell);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCEvidenceHome02X.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateStudent(String name, String email, String address, String cell, int id) {

        sql = "update student set name=?, email=?, address=?, cell=? where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, cell);
            ps.setInt(5, id);
            
            ps.executeUpdate();
            
            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCEvidenceHome02X.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteStudent(int id) {

        sql = "delete from student where id=?";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCEvidenceHome02X.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
