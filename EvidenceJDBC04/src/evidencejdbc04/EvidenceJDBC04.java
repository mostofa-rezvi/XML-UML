package evidencejdbc04;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbUtilSQL;
//import util.DbUtilXam;

public class EvidenceJDBC04 {
    
    public static DbUtilSQL db = new DbUtilSQL();
//    public static DbUtilXam db = new DbUtilXam();
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql = "";
    
    public static void main(String[] args) {
        
        deleteStudent(8);

        updateStudent("Raju", "rajuahmed@gmail.com", "Azimpur, Lalbag", "0123456789987", 2);
        
        saveStudent("Raju", "raju.ahmed@gmail.com", "Azimpur, Lalbag, Dhaka", "00545622334465");
        
        showStudent();
        
    }
    
    public static void showStudent(){
        
        sql = "select * from student";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");
                
                System.out.println("ID: " + id + " Name: " + name + " Email: " + email + " Address: " + address + " Cell: " + cell);
                
            }
            
            ps.execute();
            
            ps.close();
            rs.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvidenceJDBC04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void saveStudent(String name, String email, String address, String cell){
        
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
            Logger.getLogger(EvidenceJDBC04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void updateStudent(String name, String email, String address, String cell, int id){
        
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
            Logger.getLogger(EvidenceJDBC04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void deleteStudent(int id){
        
        sql = "delete from student where id=?";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvidenceJDBC04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
