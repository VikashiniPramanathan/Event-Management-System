import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
class Connector {

		private static String url="jdbc:mysql://localhost:3306/cognizant";
		private static String username="root";
		private static String password="root";
		static Connection con=null;
		static Statement stmt=null;
		static ResultSet res=null;
		
		static Connection connect() throws ClassNotFoundException, SQLException{
			/* Load the driver */
			Class.forName("com.mysql.cj.jdbc.Driver");
		    /* Connection establishment	*/
			con=DriverManager.getConnection(url,username,password);
			
			return con;
		}
		
		static void close(ResultSet res,Statement stmt,Connection con) {
			try {
				if(res!=null) {
					res.close();
					System.out.println("Result closed");
				}
				
				if(stmt!=null) {
					stmt.close();
					System.out.println("Statement closed");
				}
				
				if(con!=null) {
					con.close();
					System.out.println("Connection closed");
				} 
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
}
public class Event_Management{
	/* Event Management Method */
	static void eventManagement(Scanner sc1) {
		int event_id,capacity;
		String name,location,date;
		System.out.println("======Event Management======");
    	System.out.println("1. Add new event");
    	System.out.println("2. View event details");
    	System.out.println("3. Update event information");
    	System.out.println("4. Delete an event");
    	System.out.println("Enter the choice : ");
    	int eventchoice=sc1.nextInt();
    	
    	if(eventchoice == 1) {
    		/* Add new event */
    		Scanner scevent= new Scanner(System.in);
    		System.out.println("Enter event id : ");
    		event_id=scevent.nextInt();
    		scevent.nextLine();
    		System.out.println("Enter event name : ");
    		name=scevent.nextLine();
    		System.out.println("Enter event date (YYYY-MM-DD): ");
    		date =scevent.nextLine();
    		System.out.println("Enter event location : ");
    		location=scevent.nextLine();
    		System.out.println("Enter event capacity : ");
    		capacity=scevent.nextInt();
    		EventMethods.addevent(event_id, name, date, location, capacity);
    	}   	
    	else if(eventchoice == 2) {
    		/* View event details */
    		EventMethods.viewEvent();
    	}	
    	else if(eventchoice == 3) {
    		/*Update event information */
    		System.out.println("Enter event id : ");
    		Scanner eventUpdate=new Scanner(System.in);
    		event_id=eventUpdate.nextInt();
    		EventMethods.updateEvent(event_id);
    	}
    	else if(eventchoice == 4) {
    		/*Delete event*/
    		System.out.println("Enter event id : ");
    		Scanner eventdel=new Scanner(System.in);
    		event_id=eventdel.nextInt();
    		EventMethods.deleteEvent(event_id);
    	}
    	else {
    		System.err.println("Invalid choice");
    	}
	}
	
	/* Participant Management Method */
	static void participantManagement(Scanner sc2) {
		int participant_id;
		String participant_name,email;
		long phone_number;
		System.out.println("======Participant Management======");
    	System.out.println("1. Register a new participant");
    	System.out.println("2. View participant details");
    	System.out.println("3. Update participant information");
    	System.out.println("4. Delete a participant");
    	System.out.println("Enter your choice:");
    	int partichoice=sc2.nextInt();
    	
    	if(partichoice==1) {
     		/* Add new participant */
    		Scanner partisc=new Scanner(System.in);
    		System.out.println("Enter participant id:");
    		participant_id=partisc.nextInt();
   			partisc.nextLine();
   			System.out.println("Participant name:");
   			participant_name=partisc.nextLine();
   			System.out.println("Participant email:");
   			email=partisc.nextLine();
   			System.out.println("Participant phone number:");
   			phone_number=partisc.nextLong();
   			ParticipantMethods.addParti(participant_id,participant_name,email,phone_number);
    	}
     	 else if(partichoice==2) {
     		/* Participant details */
     		ParticipantMethods.viewParti();
     	 }
     	 else if(partichoice==3) {
     		/* Update participant details */
     		Scanner partiupdate=new Scanner(System.in);
     		System.out.println("Enter participant id: ");
     		participant_id=partiupdate.nextInt();
     		ParticipantMethods.updateParti(participant_id);
     	}
     	else if(partichoice==4) {
     		/* Delete participant */
     		Scanner delparti=new Scanner(System.in);
     		System.out.println("Enter participant id: ");
     		participant_id=delparti.nextInt();
     		ParticipantMethods.deleteParti(participant_id);
     	}
     	else {
     		System.err.println("Invalid choice");
     	}
	}
	
	/* Registration Management Method */
	static void registrationManagement(Scanner sc3) throws ClassNotFoundException {
		int registration_id,event_id,participant_id;
		String registration_date;
		System.out.println("======Registration Management======");
    	System.out.println("1. Register a participant for event");
    	System.out.println("2. View registration details");
    	System.out.println("3. Cancel registration");
    	System.out.println("4. List participants for a specific event");
    	System.out.println("Enter your choice:");
    	int regchoice=sc3.nextInt();
    	
    	if(regchoice==1) {
    		/* Register participant for event */
    		Scanner regsc=new Scanner(System.in);
    		System.out.println("Enter registeration id:");
    		registration_id=regsc.nextInt();
    		System.out.println("Enter event id:");
    		event_id=regsc.nextInt();
    		System.out.println("Enter participant id:");
    		participant_id=regsc.nextInt();
    		regsc.nextLine();
    		System.out.println("Enter registration date (YYYY-MM-DD):");
    		registration_date = regsc.nextLine().trim();
    		RegistrationMethods.regisAdd(registration_id,event_id,participant_id,registration_date);
    	}
    	else if(regchoice == 2) {
    		/* View registration details */
    		RegistrationMethods.regisView();
    	}
    	else if(regchoice==3) {
    		/* Cancel registration */
    		Scanner regdel=new Scanner(System.in);
    		System.out.println("Enter registration id:");
    		registration_id=regdel.nextInt();
    		RegistrationMethods.regisCancel(registration_id);
    	}
    	else if(regchoice==4) {
    		/* List participant for specific event */
    		Scanner regli=new Scanner(System.in);
    		System.out.println("Enter registration id:");
    		registration_id=regli.nextInt();
    		RegistrationMethods.regisList(registration_id);
    	}
    	else {
    		
    		System.err.println("Invalid choice");
    	}
	}
	
	/* Exit Method */
	static void exit(Scanner sc4) {
		System.out.println("Thank You for Viewing this page!!");
    	
	}
	
	  
	public static void main(String[] args) {
		   Connection con=null;
		   try {
			   Scanner sc=new Scanner(System.in);
			   while(true) {
					con=Connector.connect();
					System.out.println("======MENU ITEMS======");
					System.out.println("1. Event Management");
	                System.out.println("2. Participant Management");
	                System.out.println("3. Registration Management");
	                System.out.println("4. Exit");
	                System.out.println("Enter your choice : ");
	                int choice=sc.nextInt();
	                
	                switch(choice)
	                {
		                case 1:
		                		/* Event Management */
			                	eventManagement(sc);
			                	break;    
		                case 2:
		                		/* Participant Management */
		                		participantManagement(sc);
		                 		break;			
		                case 3:
		                		/* Registration Management */
		                		registrationManagement(sc);
		                		break;								
		                case 4:
		                		/* Exit */
		                		exit(sc);
		                		return;
		                default:
		                		System.out.println("Invalid choice");  
	                }
			   }   
		   }
		catch(ClassNotFoundException |SQLException e) {
				e.printStackTrace();
		}
		finally {
			   Connector.close(null, null, null);
		}		
	}
}
