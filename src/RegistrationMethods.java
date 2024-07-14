import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
public class RegistrationMethods {
	   static PreparedStatement pstmt=null;
	   static Connection con=null;
	   static Statement stmt=null;
	   static ResultSet res=null;
	   
	   /* Register participant for event */	
	   static void regisAdd(int registration_id,int event_id,int participant_id,String registration_date) {
		   String regsql="insert into registrationtable(registration_id,event_id,participant_id,registration_date)values(?,?,?,?)";
		   try {
			   con=Connector.connect();
			   System.out.println("driver loaded");
			   pstmt=con.prepareStatement(regsql);
			   pstmt.setInt(1, registration_id);
			   pstmt.setInt(2, event_id);
			   pstmt.setInt(3, participant_id);
			   pstmt.setString(4, registration_date);
			   System.out.println(pstmt.executeUpdate());
		   }
			catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	  }
	   
	   
       /* View registration details */				
	   static void regisView() {
		   int registration_id,event_id,participant_id;
		   String registration_date;
		   String regview="select * from registrationtable";
		   try {
			   con=Connector.connect();
			   stmt=con.createStatement();
			   res=stmt.executeQuery(regview);
			   while(res.next()) {
				   registration_id=res.getInt(1);
				   event_id=res.getInt(2);
				   participant_id=res.getInt(3);
				   registration_date=res.getString(4);
				   System.out.printf("%-5d  %-5d  %-5d  %-9s\n",registration_id,event_id,participant_id,registration_date);
			   }
		   }
		   catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	   }
	   
	   
       /* Cancel registration */							
	   static void regisCancel(int registration_id) {
		   String regCancel="delete from registrationtable where registration_id=?";
		    try {
			   con=Connector.connect();
			   pstmt=con.prepareStatement(regCancel);
			   pstmt.setInt(1, registration_id);
			   System.out.println(pstmt.executeUpdate());
		   }
		   catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	   }
	   
	   
       /* List participant for specific event */					
	   static void regisList(int registration_id) throws ClassNotFoundException{ 
		   int participant_id, event_id, capacity;
		   long phone_number;
		   String participant_name,email, event_name, event_date, location;
		   String sql_participant = "SELECT p.participant_id, p.participant_name, p.email, p.phone_number " +
		             "FROM participant p " +
		             "INNER JOIN registrationtable r " +
		             "ON p.participant_id = r.participant_id " +
		             "WHERE r.registration_id = ?";
		   
		   String sql_event = "SELECT e.event_id, e.name, e.date, e.location, e.capacity " +
		             "FROM event e " +
		             "INNER JOIN registrationtable r " +
		             "ON e.event_id = r.event_id " +
		             "WHERE r.registration_id = ?";
		   try {
			   con = Connector.connect(); 
			   pstmt = con.prepareStatement(sql_participant);
			   pstmt.setInt(1, registration_id);
	           res = pstmt.executeQuery();
	           
	           PreparedStatement pstmt1 = null;
	           ResultSet res1=null;
			   pstmt1 = con.prepareStatement(sql_event);
			   pstmt1.setInt(1, registration_id);
	           res1 = pstmt1.executeQuery();
	           
	            while (res.next()==true && res1.next()==true) {
	            	
	            	/* Participant table data */
	                participant_id = res.getInt("participant_id");
	                participant_name = res.getString("participant_name");
	                email=res.getString("email");
	                phone_number=res.getLong("phone_number");
	                
	                /* Event Table data */
	                event_id = res1.getInt("event_id");
	                event_name = res1.getString("name");
	                event_date = res1.getString("date");
	                location = res1.getString("location");
	                capacity = res1.getInt("capacity");
	                
	                
	                System.out.println("participant_id\t participant_name\t  email\t\t     phone_number\t  event_id\t  event_name\t  event_date\t  location\t  capacity");
	                System.out.println();
	                System.out.printf("%-15d  %-17s  %-25s  %-15d\t   %-5d\t  %-10s\t  %-9s\t  %-10s\t  %d\n", participant_id, participant_name, email, phone_number, event_id, event_name, event_date, location, capacity);
	            }
		   }
		   catch (SQLException e) { 
	             System.err.println("SQL Exception: " + e.getMessage());
	             e.printStackTrace();
	       }
		   finally {
			 Connector.close(res, pstmt, con);
		   }
     }
}


