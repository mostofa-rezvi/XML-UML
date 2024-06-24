package evidencejdbc01;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbUtil;

public class EvidenceJDBC01 {

    public static DbUtil db = new DbUtil();
    public static PreparedStatement ps;
    public static ResultSet rs;
    public String sql = "";
    
    public static void main(String[] args) {

        
//        saveStudent("AhOkay", "ahmedokay@gmail.com", "Khilgaon, Dhaka.", "01472583699");
        
//        updateStudent("Ahmed", "ahmed@gmail.com", "Khilgaon", "01472583699", 2);
        
//        deleteStudent(6);
        
        showStudent();
    }
    
    public static void saveStudent(String name, String email, String address, String cell){

        String sql = "insert into student (name, email, address, cell) values (?,?,?,?)";
        
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
            Logger.getLogger(EvidenceJDBC01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void showStudent(){
        
        String sql = "select * from student";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");
                
                System.out.println("Name: " + name + " Email: " + email + " Address: " + address + " Cell: " + cell);
            }
            
            ps.close();
            rs.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvidenceJDBC01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void deleteStudent(int id){
        
        String sql = "delete from student where id = ?";
        
        try {
            ps=db.getCon().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvidenceJDBC01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void updateStudent(String name, String email, String address, String cell, int id){
        
        String sql = "update student set name=?, email=?, address=?, cell=? where id=?";
        
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
            Logger.getLogger(EvidenceJDBC01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
