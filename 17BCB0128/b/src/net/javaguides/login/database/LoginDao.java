package net.javaguides.login.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {
	 public boolean validate(net.javaguides.login.bean.LoginBean loginBean) throws ClassNotFoundException {
	        boolean status = false;
//here with the following commands we make connection with the database
	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "");

	            // Here we Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            //here using the database that we already have we match the entered username and password 
	            .prepareStatement("select * from login where username = ? and password = ? ")) {
	            //for username
	        	preparedStatement.setString(1, loginBean.getUsername());
	            //for password
	        	preparedStatement.setString(2, loginBean.getPassword());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return status;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

}
