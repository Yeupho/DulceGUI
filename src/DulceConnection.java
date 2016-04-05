import java.sql.*;

public class DulceConnection {

	public static void main(String[] args) {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://http://em.uh.edu/CoT-CIS3365-07;databaseName=DulceDatabase;integratedSecurity=true";
			String user ="COUGARNET\\lvnguye";
			String pass ="";
			
		
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();
			String SQL = "SELECT * FROM City";
			ResultSet rs = stmt.executeQuery(SQL);
			rs.next();
			String first = rs.getString("CityName");
			System.out.println(first);
			System.out.println("Connection Success, PARTY!");
			
			
		}
		catch(Exception exc){
			System.out.println("You failed connection");
		}
	}

}
