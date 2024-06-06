package cruddbupdate;

import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbUtil;

public class CrudDbUpdate {

    static PreparedStatement ps;
    static ResultSet rs;
    static DbUtil db = new DbUtil();

    public static void main(String[] args) {

        Student student = new Student();

        List<Student> studentList = getById(3);

        student.setName(studentList.get(0).getName());
        student.setEmail("rezvi123@gmail.com");
        student.setAddress(studentList.get(0).getAddress());
        student.setCell(studentList.get(0).getCell());
        student.setId(studentList.get(0).getId());

//        saveDate();
        editData(student);
        showData();

    }

    public static void saveDate() {

        String insertSql = "insert into student(name, email, address, cell) values(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(insertSql);

            ps.setString(1, "Rezvi");
            ps.setString(2, "rezvi@gmail.com");
            ps.setString(3, "Azimpur, Dhaka.");
            ps.setString(4, "01234567899");

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(CrudDbUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showData() {

        String selectSql = "select * from student";

        try {
            ps = db.getCon().prepareStatement(selectSql);

            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");

                System.out.println("Id: " + id + "\nName: " + name + "\nEmail: " + email + "\nAddress: " + address + "\nCell: " + cell);

            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(CrudDbUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editData(Student s) {

        String sql = "update student set name=?, email=?, address=?, cell=? where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getCell());
            ps.setInt(5, s.getId());

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            System.out.println("Edit Successfully.");

        } catch (SQLException ex) {
            Logger.getLogger(CrudDbUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Student> getById(int id) {

        List<Student> studentList = new ArrayList<>();

        String sql = "select * from student where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("cell")
                );

                studentList.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudDbUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }

}
