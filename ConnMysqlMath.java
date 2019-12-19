import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnMysqlMath {

	private static final String CONTROLLER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/school";
	private static final String USER = "root";
	private static final String PASS = "";



	static {
		try {
			Class.forName(CONTROLLER);
		} catch (ClassNotFoundException e) {
			System.out.println("Error while connecting the controller");
			e.printStackTrace();
		}
	}

	public java.sql.Connection conect() {
		java.sql.Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection OK");

		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		}

		return con;
	}

	public void printMath( ) {

		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(URL,USER,PASS);  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT * FROM mathclass"); 
			System.out.printf("%-5s %-20s %-20s %-8s %-8s %-8s\n", "#STD","FIRST NAME","LAST NAME", "MARK 1", "MARK 2", "MID TERM");
			System.out.println("---------------------------------------------------------------------------");
			while(rs.next())  
				System.out.printf("%-5d %-20s %-20s %-8d %-8d %-8d\n", 
						rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5), rs.getInt(6));  
			con.close();  
		}
		catch(Exception e){ System.out.println(e);}  
	}

	public void updateMarks(String column, int newData, int std) {


		try 
		{
			String	query = String.format("UPDATE mathclass SET %s = ? WHERE idStudent = %d", column, std);

			Connection conn = DriverManager.getConnection(URL,USER,PASS);

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, newData);

			preparedStmt.executeUpdate();

			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}


	}

	public void updateLastName(String newData, int std) {

		try
		{
			String query = "UPDATE mathclass SET lastName = ? WHERE idStudent =" + std;

			Connection conn = DriverManager.getConnection(URL,USER,PASS);

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, newData);

			preparedStmt.executeUpdate();

			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void delete (int id) {
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn = DriverManager.getConnection(URL,USER,PASS);
			Statement stmt = conn.createStatement(); 


			String query = "DELETE FROM mathclass WHERE idStudent = " + id; 

			int x = stmt.executeUpdate(query); 

			if (x > 0)             
				System.out.println("Successfully Deleted");             
			else
				System.out.println("ERROR OCCURED");   

			conn.close(); 
		} 
		catch(Exception e) 
		{ 
			System.out.println(e); 
		} 
	} 

	public void insert(String name, String last, int mark1, int mark2, int mid)  {

		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn = DriverManager.getConnection(URL,USER,PASS);
			
			
			PreparedStatement preparedStmt = conn.prepareStatement("INSERT INTO mathclass (firstName, lastName, algebraTest1Mark, algebraTest2Mark, midTermMark) "
					+ "VALUES (?,?,?,?,?);");

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, last);
			preparedStmt.setInt(3, mark1);
			preparedStmt.setInt(4, mark2);
			preparedStmt.setInt(5, mid);


			conn.close();

		} catch (SQLException e) {
			System.out.println(e); 

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}
