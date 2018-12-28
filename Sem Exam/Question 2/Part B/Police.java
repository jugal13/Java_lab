import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * Servlet implementation class Police
 */
@WebServlet("/Police")
public class Police extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	
	public Police() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "") ;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select area,phone from police ");
			int n  = Integer.parseInt(request.getParameter("n"));
			String val = request.getParameter("ap");
			boolean c = false ;
			switch (n) { 
				case 1 :
						while(rs.next()) {
							
							if(rs.getString("area").equals(val)) {
								out.println("Area " +val +" Phone " + rs.getString("phone"));
								c = true;
								break; 
							}    
						}   
						break;

				case 2:
						while(rs.next()) {
							
							if(rs.getString("phone").equals(val)) {
								out.println("Area " +val +" Phone " + rs.getString("phone"));
								c = true;
								break;
							}			
						}
						break;

				default:
						out.println("Information does not exist");
			}

			if(!c)
				out.println("Information does not exist");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}