package com.selenium.sevillaqa;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SevillaQA {

	/**
	 * Example of Null Pointer Exception
	 */
	public static void main (String[] args)
    {
        // Initializing String variable with null value
        String ptr = null;
        
         //Checking if ptr.equals null or works fine. This line of code throws 
         //NullPointerException because ptr is null
            if (ptr.equals("gfg"))
                System.out.print("Same");
            else
                System.out.print("Not Same");
    }
	
	/*****************************************************************************************************************/
	
	/**
	 * Example of Complexity
	 */
	public void exampleComplexity(){
		String a="1", b="1", c="1";
		int d=0;
			if(a!="0"){
				return;
			}
			while(b=="1" && c!="2"){
				d++;
			}
			return;
	}
	
	/*****************************************************************************************************************/

	/**
	 * Example of duplication
	 */
	public void exampleDuplication(){
		String a="1", b="1", c="1";
		int d=0;
			if(a!="0"){
				a="1";
				return;
			}
			while(b=="1" && c!="2"){
				a="2"; b="3"; c="3"; d++;
			}
			return;
	}
	
	/*****************************************************************************************************************/
	
	/**
	 * Example of return statement
	 * @return
	 */
	public boolean exampleInvariantMethodResult(){
		int a=0;
		if(a==1){
			return true;
		}
		else{
			return true;
		}
	}
	
	/*****************************************************************************************************************/
	
	/**
	 * Example of unclosed resource
	 */
	public void exampleUnclosedResource(){
		try {
			String uri = null;
			File newFile = new File(uri);
			
		} catch (Exception e) {
				System.out.println("No exception");
		}
	}
	
	/*****************************************************************************************************************/
	
	/**
	 * Example of Vulnerability
	 */
	public void exampleVulnerability() throws SQLException{
		Connection conn = null;
		try {
		  conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
		        "user=steve&password=blue"); // Noncompliant
		  String uname = "steve";
		  String password = "blue";
		  conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
		        "user=" + uname + "&password=" + password); // Noncompliant
	
		  java.net.PasswordAuthentication pa = new java.net.PasswordAuthentication("userName", "1234".toCharArray());  // Noncompliant
	} finally {
		//TODO:
	}
	}

}
