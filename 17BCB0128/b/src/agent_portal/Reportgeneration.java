package agent_portal;
import java.sql.PreparedStatement;


import bankruptcygenerations.DB_Connect;
import java.sql.Connection;
public class Reportgeneration {
	public void insert_report(String FirstName,String LastName,String CustId,String Loan_amt, String Ph_num, String Due_date,String Status)
	{
		 DB_Connect obj_DB_Connect=new DB_Connect();
		 Connection connection=obj_DB_Connect.get_connection();
		 PreparedStatement ps=null;
		try {
	String query="insert into defaulters.DEFAULTERLIST(FirstName, LastName, CustId, Loan_amt, Ph_num, Due_date, Status) values(?,?,?,?,?,?,?)";
		 ps=connection.prepareStatement(query);
		 ps.setString(1, FirstName);
		 ps.setString(2, LastName);
		 ps.setString(3, CustId);
		 ps.setString(4, Loan_amt);
		 ps.setString(5, Ph_num);
         ps.setString(6, Due_date);
         ps.setString(7, Status);
		 ps.executeUpdate();
		} catch (Exception e) {
		 System.err.println(e);
		}
	}
}




