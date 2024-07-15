# Event Management System
Developed a menu-based event management system console application, allowing users to perform operations related to managing events, participants, and registrations.

## How to get this poject
1. Clone the repo: `git clone git@github.com:VikashiniPramanathan/Event-Management-System.git`
2. Go to local repository after cloning: `cd Event-Management-System`
3. Switch to main branch:`git checkout main`

## Tools Requirement 
1. Eclipse : 2024-03 (4.31.0)
2. Mysql Workbench : 8.0.38

## How to import this project
1. Start Eclipse.
2. Import the project (File -> Import).
3. Click General, select the Exiting Projects into workspace and click next.
4. Select the root directory and click Finish.

## How to build this project
1. Select Project and click Build All.

> [!NOTE]
> Port 3306 is the default port for the MySQL Workbench.

## How to Run this project
1. Select Run and click Run
2. The console will be popped up.

## Sample output
```
======MENU ITEMS======
1. Event Management
2. Participant Management
3. Registration Management
4. Exit
Enter your choice : 
1
======Event Management======
1. Add new event
2. View event details
3. Update event information
4. Delete an event
Enter the choice : 
2
2       Kabadi               2024-08-15      Chennai         4000
4       BasketBall           2024-10-27      Coimbatore      800
5       Hockey II            2025-10-10      Singapore       1000
7       Dance                2020-07-11      Bangalore       450
22      Cricket II           2024-12-01      Madurai         2000

======MENU ITEMS======
1. Event Management
2. Participant Management
3. Registration Management
4. Exit
Enter your choice : 
2
======Participant Management======
1. Register a new participant
2. View participant details
3. Update participant information
4. Delete a participant
Enter your choice:
2
 1     Banu             banu14@gmail.com      6543216789   
 2     Nikitha          niki2@gmail.com       2345678901   
 3     Ram              ram6@gmail.com        6655443322   
 4     Vikashini        vikash77@gmail.com    9597231526   
 13    Shruthi          shruthi23@gmail.com   2233445566   

  ======MENU ITEMS======
1. Event Management
2. Participant Management
3. Registration Management
4. Exit
Enter your choice : 
3
======Registration Management======
1. Register a participant for event
2. View registration details
3. Cancel registration
4. List participants for a specific event
Enter your choice:
2
1      2      2      2020-08-04
10     4      3      2025-12-12
```