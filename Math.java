import java.sql.SQLException;

public class Math {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnMysqlMath con = new ConnMysqlMath();
		
		con.printMath();
		
		con.updateMarks("algebraTest1Mark", 100, 2);
		
		con.updateMarks("midTermMark", 7, 8);
		
		con.updateLastName("Jones", 3);
		
		con.delete(4);
		
		con.insert("Ima", "Goner", 68, 75, 78);
		
		con.printMath();

		

	}

}
