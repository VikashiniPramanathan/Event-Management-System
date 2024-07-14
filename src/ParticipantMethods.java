import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
public class ParticipantMethods {
	
	static PreparedStatement pstmt=null;
	static Connection con=null;
	static Statement stmt=null;
    static ResultSet res=null;
    
    /* Register new participant */
    static void addParti(int participant_id,String participant_name,String email,long phone_number) {
    	String add="insert into participant(participant_id,participant_name,email,phone_number) values(?,?,?,?)";
    	try {
    		con=Connector.connect();
    		pstmt=con.prepareStatement(add);
    		pstmt.setInt(1, participant_id);
    		pstmt.setString(2, participant_name);
    		pstmt.setString(3, email);
    		pstmt.setLong(4, phone_number);
    		System.out.println(pstmt.executeUpdate());	
    	}
    	catch(ClassNotFoundException |SQLException e){
			e.printStackTrace();
		}
		finally {
			Connector.close(res, pstmt, con);
		}
    }
    
    
    /* View participant details */
    static void viewParti() {
    	int participant_id;
    	String participant_name,email;
    	long phone_number;
    	String viewdetails="select * from `participant`";
    	try {
    		con=Connector.connect();
    		stmt=con.createStatement();
    		res=stmt.executeQuery(viewdetails);
    		while(res.next()==true) {
    			participant_id=res.getInt(1);
    			participant_name=res.getNString(2);
    			email=res.getString(3);
    			phone_number=res.getLong(4);
    			System.out.printf("%-4d  %-15s  %-20s  %-13d\n ",participant_id,participant_name,email,phone_number);
    		}
    	}
    	catch(ClassNotFoundException |SQLException e){
			e.printStackTrace();
		}
		finally {
			Connector.close(res, pstmt, con);
		}
    	
    }
    
    /* Update participant deatils */    
    static void updateParti(int participant_id) {
    	Scanner sc=new Scanner(System.in);
    	String participant_name,email;
    	long phone_number;
    	String partiupdate="update `participant` set participant_name=?,email=?,phone_number=? where participant_id=?";
    	try {
    		con=Connector.connect();
    		System.out.println("Enter name:");
    		participant_name=sc.nextLine();
    		System.out.println("Enter email:");
    		email=sc.nextLine();
    		System.out.println("Enter phone number:");
    		phone_number=sc.nextLong();
    		pstmt=con.prepareStatement(partiupdate);
    		pstmt.setString(1,participant_name);
    		pstmt.setString(2, email);
    		pstmt.setLong(3, phone_number);
    		pstmt.setInt(4, participant_id);
    		System.out.println(pstmt.executeUpdate());
    	}
    	catch(ClassNotFoundException |SQLException e){
			e.printStackTrace();
		}
		finally {
			Connector.close(res, pstmt, con);
		}
    	
    }
       
    /* Delete participant deatils */
    static void deleteParti(int participant_id) {
    	String delParti="delete from participant where participant_id=?";
    	try {
    		con=Connector.connect();
    		pstmt=con.prepareStatement(delParti);
    		pstmt.setInt(1,participant_id);
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
