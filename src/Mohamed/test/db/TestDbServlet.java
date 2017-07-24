package Mohamed.test.db;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
	//setup connection variable
		String user="springstudent" ;
		String pass="springstudent" ;
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false" ;
		String driver="com.mysql.jdbc.Driver" ;
		
	//get connection to database	
		try{
			PrintWriter out=response.getWriter();
			out.println("successfully connected to database: " + jdbcUrl);
			
			Class.forName(driver);
			Connection myConn=DriverManager.getConnection(jdbcUrl,user,pass) ;
			
			out.print("\n\n successfully connected");
			
			myConn.close();
			
		}catch (Exception exc){
			exc.printStackTrace();
			throw new ServletException(exc);
			
			
		}
		
	
	}

}
