import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
public class EventMethods {
	   static PreparedStatement pstmt=null;
	   static Connection con=null;
	   static Statement stmt=null;
	   static ResultSet res=null;
	
	  /* Add new event */
	   static void addevent(int event_id,String name,String date,String location,int capacity) {
			String q="insert into event(event_id,name,date,location,capacity) values (?,?,?,?,?)";
			try {
				con=Connector.connect();
				pstmt=con.prepareStatement(q);
				pstmt.setInt(1, event_id);
				pstmt.setString(2,name);
				pstmt.setString(3,date);
				pstmt.setString(4, location);
				pstmt.setInt(5, capacity);
				System.out.println(pstmt.executeUpdate());		
			}
			catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	   } 
	  
	   /* View event details */
	  static void viewEvent() {
		  int event_id,capacity;
		  String name,location,date; 
		  String view="select * from `event`"; 
		  try {
			  con=Connector.connect();
			  stmt=con.createStatement();
			  res=stmt.executeQuery(view);
			  while(res.next()==true) {
					event_id=res.getInt(1);
					name=res.getString(2);
					date=res.getString(3);
					location=res.getString(4);
					capacity=res.getInt(5);
							
					System.out.printf("%-7d %-20s %-15s %-15s %d\n",event_id,name,date,location,capacity);
			  }
			}
		  	catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}  
	  }
	  
	  /* Update event details */														
	  static void updateEvent(int event_id) {
		  Scanner sc=new Scanner(System.in);
		  int capacity;
		  String date,location,name;
		  try {
			  con=Connector.connect();
			  System.out.println("Enter new event name to be update:");
	          name = sc.nextLine();
	          System.out.println("Enter new event date to be update: (YYYY-MM-DD):");
	          date = sc.nextLine();
	          System.out.println("Enter new event location to be update::");
	          location = sc.nextLine();
	          System.out.println("Enter new event capacity to be update::");
	          capacity = sc.nextInt();
	          sc.nextLine();
	          String update="update `event` set name=?, date=?, location=?, capacity=? where `event_id`=?";
			  pstmt=con.prepareStatement(update);
			  pstmt.setString(1, name);
	          pstmt.setString(2, date);
	          pstmt.setString(3, location);
	          pstmt.setInt(4, capacity);
	          pstmt.setInt(5,event_id);
	          System.out.println(pstmt.executeUpdate());
		  }
		  catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	  }
	  
	  /* Delete event */   													
	  static void deleteEvent(int event_id) {  
		  String delete="delete from `event` where `event_id`=?";
		  try { 
			  con=Connector.connect();
			  pstmt=con.prepareStatement(delete);
			  pstmt.setInt(1,event_id);
			  System.out.println(pstmt.executeUpdate());
			  
		  }
		  catch(ClassNotFoundException |SQLException e){
				e.printStackTrace();
			}
			finally {
				Connector.close(res, pstmt, con);
			}
	  }
}

	
	
	

