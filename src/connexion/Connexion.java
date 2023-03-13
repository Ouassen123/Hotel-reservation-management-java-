package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	private static final String url = "jdbc:mysql://localhost:3306/gestion?useUnicode=true" +
			"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
private static final String user="root";
private static final String password="";
private static Connection connexion;
	static {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion=DriverManager.getConnection(url,user,password);
			
			Statement statement =connexion.createStatement();
			System.out.println("Connexion bien faite");

			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Erreur de driver");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Erreur de connexion"+e);
		}
}
public static Connection getConnexion() {
	return connexion;
}	
}
