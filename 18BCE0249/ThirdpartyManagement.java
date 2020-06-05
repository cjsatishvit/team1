//Third party collection for Home Loans

//Project Manager Prof. Satish C.J.
//Team number: 01
//Team member:Rishik Reddy
//Member ID: 18BCE0249

//USES MYSQL DATABASES AND JAVA (JDBC-JAVA DATABASE CONNECTION API)

//importing packages
import java.sql.*;
import java.util.*;
import java.lang.*; 
class test 
{ static Scanner sc = new Scanner(System.in);//creating static object
  static int id;
  static String type=""; //bank manager (or) third party manager (or) agent
  static String dbURL = "jdbc:mysql://localhost:3306/sampledb";
  static String username = "root";
  static String password = "MyComputer";
    public static void main(String[] args) //main function
    { Scanner sc = new Scanner(System.in);
    	
    	int usertype,select,Lselect;
    	
		System.out.println("Have an account? press 1 to login" );
    	System.out.println("New user? press 2 to sign up" );
    	
    	Lselect=sc.nextInt();
    	
    	//loop for getting correct user choice
    	do{switch(Lselect){
    	case 1 : System.out.println("proceeding to login...."); 
    	break;
    	case 2: signup(); //New user sign up
    	break;
    	default: System.out.println("Please try again");
    	}
    		
    	}while(Lselect!=1 && Lselect!=2);
    	
    	//login process
    	
    	try {
       	 
    	    Connection conn = DriverManager.getConnection(dbURL, username, password);
    	    Statement stmt = conn.createStatement();
    	    if (conn != null) 
    	    {
				//Connected to database for login
    	        System.out.println("Login");
    	        System.out.print("Enter your user ID : ");  
    	        int ID_str = sc.nextInt(); //user ID
    	        System.out.print("Enter the password : ");  
    	        String Pass_str = sc.next(); //password
    	        do{
    	        	System.out.println("Bank manager: press 1");
    	        	System.out.println("Third party manager: press 2");
    	        	System.out.println("Third party agent: press 3");
    	        	usertype=sc.nextInt();//entering usertype for login
    	        	switch(usertype){
    	        	
    	            case 1: 
    	                type="bm"; 
    	                break; 
    	            case 2: 
    	                type="tpm"; 
    	                break; 
    	            case 3: 
    	                type="tpa"; 
    	                break;
    	            default: 
    	            	System.out.println("invalid choice"); 
    	                break; 
    	        	}
    	        	}while(usertype!=1 && usertype!=2 && usertype!=3  );
    	        //Crosschecking with the database
    	        ResultSet rs = stmt.executeQuery("select * from login where id='" + ID_str + "' and password='" + Pass_str + "' and type= '" + type +"'"); 
				System.out.println("Account created");
    	      
    	        if (rs.next())  
    	         {id= ID_str;  //id for the whole program is the id you entered while u login(if you login successfully)
    	         }
    	          
    	        else  
    	         {
					System.out.println("Invalid credentials");
    	         }
    	        
    	        }
    	}
    	catch (SQLException ex) 
    	{
   	     ex.printStackTrace();
   	 }
    	
    	
    	if(type=="bm")
    	{
    		 do{//if your are a bank manager then these are your set of functions
                 
                 System.out.println("--------BANK MANAGER OPERATIONS-----------");
				 System.out.println("press 1: view defaulters list");
                 System.out.println("press 2: add defaulter");
                 System.out.println("press 3: remove defaulters");
                 System.out.println("press 4: check collection status");
                 System.out.println("press 5: exit");
                 select=sc.nextInt();
                 
                 switch(select)//selection for choice of operation
                 {
                         case 1:         
						 				//view defaulter table
                        	             ViewTable();
                                         break;
						 				
                         case 2:
						 				//add defaulter
                        	             AddDefaulter();
                                         break;
                                         
                         case 3:
						 				//remove defaulters
                        	        	DeleteDefaulter();
                                    	break; 
                                      
                         case 4:
                                        //check collection status
            	                        CheckStatus();
                                        break;   
                         
                         case 5:
                                         System.exit(0);
                                         break;
                         default:
                        	           System.out.println("Invalid choice:");
                                         break;
                 }
         }while(select!=5);
    		
    	}
    	else if (type=="tpm")
    	{
    		do{//if your type is of a third party manager then these are your set of functions
                
                System.out.println("--------THIRD PARTY MANAGER OPERATIONS-----------");
                System.out.println("press 1: View defaulters list ");
                System.out.println("press 2: Assign agents to defaulters");
                System.out.println("press 3: Exit");
                select=sc.nextInt();
                
                switch(select)//selection for choice of operation
                {
                        case 1: 
                                        //viewing thelist of defaulters
                        	            tpmViewTable();
                                        break;
                        case 2:
                                        //assign third party agents to defaulters
                        	            AssignAgent();
                                        break;
                        
                        case 3:
                                        System.exit(0);
                                        break;
                        default:
                       	           System.out.println("Invalid choice:");
                                        break;
                }
        }while(select!=3);
    		
    	}
    	else if (type=="tpa")
    	{
           do{//if your type is of a third party agent then these are your set of functions
                
                System.out.println("--------THIRD PARTY AGENT OPERATIONS-----------");
                System.out.println("press 1: View your defaulters");
                System.out.println("press 2: Update payment status");
                System.out.println("press 3: Exit");
                select=sc.nextInt();
                
                switch(select)//seelection for choice of operation
                {
                        case 1: 
                                        //viewing defaulters assigned to the agent
                        	            tpaViewTable();
                                        break;
                        case 2:
                                        //updating information of recovered payments
                        	            UpdatePayment();
                                        break;
                       
                        case 3:
                                        System.exit(0);
                                        break;
                        default:
                       	           System.out.println("Invalid choice:");
                                        break;
                }
        }while(select!=4);
    		
    	
    	}      	
    	    
    
    }

    public static void AddDefaulter() //function of bank manager for entry in defaulter table
    {
    	 
    	  
    	 try {
    	  
    	     Connection conn = DriverManager.getConnection(dbURL, username, password);//establishes connection with database
    	  
    	     if (conn != null) {
    	         //Connected to database
    	         String entry = "INSERT INTO defaulters (defaulterid,defaultername,loantype,totalamount,manager) VALUES (?, ?, ?, ?, ?)";
    	          //executing the query
    	         PreparedStatement statement = conn.prepareStatement(entry);
    	         System.out.println("enter the 5 digit defaulter id"); //entering details of defaulters
    	         int defaulterid;
    	         defaulterid=sc.nextInt();
    	         System.out.println("enter defaulter name");
    	         String defaultername;
    	         defaultername=sc.nextLine();
    	         System.out.println("enter the loan type");
    	         String loantype;
    	         loantype=sc.nextLine();
    	         System.out.println("enter the amount of money to be recovered");
    	         int totalamount;
    	         do{
    	        	 totalamount=sc.nextInt();
    	        	 if(totalamount==0){System.out.println("enter a non zero amount");}
    	         }while(totalamount==0);//amount to be recovered cannot be zero because if it is zero then the customer is not a defaulter
    	         
    	         System.out.println("enter the id of the third party manager asigned to recover this money");
    	         int manager ;
    	         manager=sc.nextInt();
    	         //storing the entered details of defaulters in the database
    	         statement.setInt(1, defaulterid);
    	         statement.setString(2, defaultername);
    	         statement.setString(3, loantype);
    	         statement.setInt(4, totalamount);
    	         statement.setInt(5, manager);
    	          
    	         int rowsInserted = statement.executeUpdate();
    	         if (rowsInserted > 0) {
    	             System.out.println("A new user was inserted successfully!");
    	         }
    	     }
    	 } catch (SQLException ex) {
    	     ex.printStackTrace();
    	 }
    	
    }
    public static void ViewTable()//function of bank manager to view defaulter table
    {
    	
    	 
    	try {
    	 
    	    Connection conn = DriverManager.getConnection(dbURL, username, password);
    	 
    	    if (conn != null) {
    	        System.out.println("Connected to database");
    	        String entry = "SELECT * FROM defaulters";
    	        //executing the query
    	        Statement statement = conn.createStatement();
    	        ResultSet result = statement.executeQuery(entry);
    	         
    	        
    	         
    	        while (result.next()){//retrieving details of defaulters from the database
    	            String defaulterid = result.getString(1);
    	            String defaultername = result.getString(2);
    	            String loantype = result.getString(3);
    	            int totalamount = result.getInt("totalamount");
    	            int manager = result.getInt("manager");
    	            int recoveredamount=result.getInt("recoveredamount");
    	            //printing the retrieved details in a tabular form
    	            System.out.print("customer id:  "+defaulterid+"  ");
    	            System.out.print("customer name:"+defaultername+"  ");
    	            System.out.print("type of loan:"+loantype+"  ");
    	            System.out.print("amount to be recovered:"+totalamount+"  ");
    	            System.out.print("amount recovered:"+recoveredamount+"  ");
    	            System.out.println("third party manager assigned:"+manager+"  ");
    	        }
    	    }
    	} catch (SQLException ex) {
    	    ex.printStackTrace();
    	}
    	
    }
    
   
    
    public static void tpmViewTable()
    {
    	 
    	try {
    	 
    	    Connection conn = DriverManager.getConnection(dbURL, username, password);
    	 
    	    if (conn != null) {
    	        System.out.println("Connected to database");
    	        String entry = "SELECT * FROM defaulters WHERE manager="+id;
    	      //executing the query
    	      //third party manager can only see entries which are assigned to him
    	        Statement statement = conn.createStatement();
    	        ResultSet result = statement.executeQuery(entry);
    	         
    	        int count = 0;
    	       
    	        while (result.next()){//retrieving details of defaulters from the database
    	            String defaulterid = result.getString(1);
    	            String defaultername = result.getString(2);
    	            String loantype = result.getString(3);
    	            int totalamount = result.getInt("totalamount");
    	            int manager = result.getInt("manager");
    	            int agent= result.getInt("agent");
    	            //printing the retrieved details in a tabular form
    	            System.out.print("customer id:  "+defaulterid+"  ");
    	            System.out.print("customer name:"+defaultername+"  ");
    	            System.out.print("type of loan:"+loantype+"  ");
    	            System.out.print("amount to be recovered:"+totalamount+"  ");
    	            System.out.print("third party manager assigned:"+manager+"  ");
    	            System.out.println("third party agent assigned:"+agent+"  ");
    	        }
    	    }
    	} catch (SQLException ex) {
    	    ex.printStackTrace();
    	}	
    }
    
    public static void AssignAgent()
    {
    	
     
          try {
     
               Connection conn = DriverManager.getConnection(dbURL, username, password);
     
               if (conn != null) 
               {
               System.out.println("Connected to database ");
               System.out.println("enter the customer id of the customer to whom you want to assign a third party agent");
               int tempdefaulterid=sc.nextInt();//entering the customer id of the customer to whom you want to assign a third party agent
               String entry = "UPDATE defaulters SET agent=? WHERE defaulterid="  +tempdefaulterid;//updating the entry where the customer id is entered as above
               System.out.println("enter the id of the third party agent you want to assign");
               int agent=sc.nextInt();
               PreparedStatement statement = conn.prepareStatement(entry);
               statement.setInt(1, agent);
               
                
               int rowsUpdated = statement.executeUpdate();
               if (rowsUpdated > 0) {
                   System.out.println("An third party agent was updated successfully!");
               }
               }
             }     
          catch (SQLException ex) 
          {
                  ex.printStackTrace();
           }
    	
    }
    
    public static void tpaViewTable()
    {
    	 
    	try {
    	 
    	    Connection conn = DriverManager.getConnection(dbURL, username, password);
    	 
    	    if (conn != null) {
    	        System.out.println("Connected to database");
    	        String entry = "SELECT * FROM defaulters WHERE agent="+id;
    	      //executing the query
    	        //third party agent can only see entries which are assigned to him
    	        Statement statement = conn.createStatement();
    	        ResultSet result = statement.executeQuery(entry);
    	         
    	        int count = 0;
    	       
    	        while (result.next()){//retrieving details of defaulters from the database
    	            String defaulterid = result.getString(1);
    	            String defaultername = result.getString(2);
    	            String loantype = result.getString(3);
    	            int totalamount = result.getInt("totalamount");
    	            int recoveredamount = result.getInt("recoveredamount");
    	            int manager = result.getInt("manager");
    	            int agent= result.getInt("agent");
    	            //printing the retrieved details in a tabular form
    	            System.out.print("customer id:  "+defaulterid+"  ");
    	            System.out.print("customer name:"+defaultername+"  ");
    	            System.out.print("type of loan:"+loantype+"  ");
    	            System.out.print("amount to be recovered:"+totalamount+"  ");
    	            System.out.print("amount recovered:"+recoveredamount+"  ");
    	            System.out.print("third party manager assigned:"+manager+"  ");
    	            System.out.println("third party agent assigned:"+agent+"  ");
    	        }
    	    }
    	} catch (SQLException ex) {
    	    ex.printStackTrace();
    	}	
    }
    
    public static void UpdatePayment()
    {
    	 
    	try {
    	 
    	    Connection conn = DriverManager.getConnection(dbURL, username, password);
    	 
    	    if (conn != null) {
    	        //Connected to database"
    	        System.out.println("enter the customer id of the customer whose loan payment info you want to update");
                int tempdefaulterid=sc.nextInt();
                System.out.println("enter the amount from recieved from the defaulter");
                int paymentrecieved =sc.nextInt();
                String sqlent = "SELECT * FROM defaulters WHERE defaulterid="+tempdefaulterid;
                
                Statement statement1 = conn.createStatement();
                ResultSet result = statement1.executeQuery(sqlent);
                 
                int count = 0;
                 
                 result.next();
                 
                    int totalamount = result.getInt("totalamount");
                    int recoveredamount = result.getInt("recoveredamount");
                    int newtotalamount=totalamount-paymentrecieved;//as the customer has paid some amount amount left to be recovered is reduced by the amount paid
                    int newrecoveredamount =recoveredamount+paymentrecieved;//and total amount recovered increases by the amount paid  
                
    	        String entry = "UPDATE defaulters SET totalamount=?, recoveredamount=? WHERE defaulterid="+tempdefaulterid;
    	        
    	        PreparedStatement statement = conn.prepareStatement(entry);
    	        statement.setInt(1,totalamount);//inserted the new amoount to be recovered
    	        statement.setInt(2,newrecoveredamount );// inserted the new amount recovered
    	       
                
    	        int rowsUpdated = statement.executeUpdate();
                
    	        if (rowsUpdated > 0) {
    	            System.out.println("An existing user was updated successfully!");
    	        }
                }
                
              
              
    	} catch (SQLException ex) {
    	    ex.printStackTrace();
    	}
    	
    }
    
    public static void signup()
    {
    	  
    	 try {
    	  
    	     Connection conn = DriverManager.getConnection(dbURL, username, password);//establishes connection with database
    	  
    	     if (conn != null) {
    	         System.out.println("Connected to database"); 
    	         String entry = "INSERT INTO login (id,password,type) VALUES (?, ?, ?)";
    	         
    	         PreparedStatement statement = conn.prepareStatement(entry);
    	         System.out.println("enter the id (5 digit id)");//entering details for signing up as a new user
    	         int id;
    	         id=sc.nextInt();
    	         System.out.println("enter the password");
    	         String signuppassword;
    	         signuppassword=sc.next();
    	         int usertypedatabase;
    	         do{
     	        	System.out.println("press 1 if you are a bank manager");
     	        	System.out.println("press 2 if yor are a third party manager");
     	        	System.out.println("press 3 if you are a party agent");
     	        	 usertypedatabase=sc.nextInt();//entering choice to decide your role in this program
     	        	switch(usertypedatabase){
     	        	
     	            case 1: 
     	                type="bm"; 
     	                break; 
     	            case 2: 
     	                type="tpm"; 
     	                break; 
     	            case 3: 
     	                type="tpa"; 
     	                break;
     	            default: 
     	            	System.out.println("invalid choice"); 
     	                break; 
     	        	}
     	        	}while(usertypedatabase!=1 && usertypedatabase!=2 && usertypedatabase!=3  );
    	         
    	         //storing the entered details of defaulters in the database
    	         statement.setInt(1, id);
    	         statement.setString(2, signuppassword);
    	         statement.setString(3, type);
    	         
    	          
    	         int rowsInserted = statement.executeUpdate();
    	         if (rowsInserted > 0) {
    	             System.out.println("A new user was inserted successfully!");
    	         }
    	     }
    	 } catch (SQLException ex) {
    	     ex.printStackTrace();
    	 }
    	
    }

    public static void CheckStatus()
    {
    	 
    	  
    	 try {
    	  
    	     Connection conn = DriverManager.getConnection(dbURL, username, password);//establishes connection with database
    	  
    	     if (conn != null) {
    	         System.out.println("Connected to database"); 
    	         String entry = " SELECT SUM(recoveredamount) from defaulters";
    	         Statement pst= conn.prepareStatement(entry);
                 ResultSet rs = pst.executeQuery(entry);
                  
                 int count = 0;
                  
                 if(rs.next()){
                     int sum = rs.getInt("Sum(recoveredamount)");
                     System.out.println(sum);
                     int commision = (sum/100)*40;
                     System.out.println("commision to be given to third party managers out of the total recovery of rs "+ sum +"is 40% which is rs." +commision);
                 }
                 String entry1 = "UPDATE defaulters set recoveredamount=?";
     	        
     	        PreparedStatement statement = conn.prepareStatement(entry1);
     	       statement.setInt(1, 0);
     	      int rowsUpdated = statement.executeUpdate();
    	     }
    	 }
    	 catch (SQLException ex) 
    	    {
    	     ex.printStackTrace();
                     
            }  
    }
     public static void DeleteDefaulter()
     {
        
        String sql = "delete from defaulters where totalamount=0";//deleting the entry where amount to be recovered is 0 as he has paid his loan and is no more a defaulter

        try {
        		Connection conn = DriverManager.getConnection(dbURL, username, password); 
            Statement stmt = conn.createStatement(); 
          
          stmt.executeUpdate(sql);
          System.out.println("Record deleted successfully");
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
} 

