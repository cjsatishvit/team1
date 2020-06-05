package bankruptcygenerations;
import java.sql.Connection;



import java.sql.DriverManager;


public class DB_Connect {
	public static void main(String[] args) {
		DB_Connect obj_DB_Connection=new DB_Connect();
		
		System.out.println(obj_DB_Connection.get_connection());
	}
	
	
	
	public Connection get_connection(){
		Connection connection=null;
		try {
			
		
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/defaulters","root", "");
 		
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
		
		
		
		
	}
}
