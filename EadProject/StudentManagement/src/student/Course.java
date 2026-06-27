
package student;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Course {
     Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    
       public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from course");
            while(rs.next())
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
            return id +1;
     }  
       public boolean getId(int id)
       {
         try {
             ps = con.prepareStatement("select * from student where id = ?");
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             if(rs.next())
             {
                 Home.jTextField13.setText(String.valueOf(rs.getInt(1)));
                 return true;
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Student id does not added");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
       }
       public int countSemester(int id)
       {
           int total = 0;
         try {
             ps = con.prepareCall("select count(*) as 'total' from course where student_id = ?");
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             while(rs.next())
             {
                 total = rs.getInt(1);
             }
             if(total==0)
             {
                 JOptionPane.showMessageDialog(null,"This student has Compleated all the courses");
                 return -1;
             }
         } catch (SQLException ex) {
             Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
         }
         return total;
       }
}
