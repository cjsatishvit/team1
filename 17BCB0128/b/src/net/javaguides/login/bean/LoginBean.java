package net.javaguides.login.bean;
import java.io.Serializable;

public class LoginBean implements Serializable {
    //private variables declared that means they can be used in this private class
    private static final long serialVersionUID = 1 ;
    private String username;
    private String password;
//here we use get function to get username from the user and thus return the same when we get the value 
    public String getUsername() {
        return username;
    }
//we use set function to assign the value recieved to the variable username
    public void setUsername(String username) {
        this.username = username;
    }
//similarly here also we use get to get the password from the user
    public String getPassword() {
        return password;
    }
//here we use set to set the password entered to the variable 
    public void setPassword(String password) {
        this.password = password;
    }

	public String getUsername1() {
		return null;
	}
}