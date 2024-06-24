package evidencejdbc03;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbUtil01;

public class EvidenceJDBC03 {

    public static DbUtil01 db = new DbUtil01();
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql = "";
    
    public static void main(String[] args) {
        
        showStudent();
    }
    
    public static void showStudent(){
        
        sql = "select * from student";
        
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
            
            ps.execute();
            
            ps.close();
            rs.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvidenceJDBC03.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateStudent(){
        
    }
    
    public static void deleteStudent(){
        
    }
    
    public static void saveStudent(){
        
    }

}
