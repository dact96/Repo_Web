package pe.egcc.AppDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection cn;
        try {
            //cargar el driver en memoria
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            // obtener objeto conexion
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            cn = DriverManager.getConnection(url, "empresa", "admin");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }

    }
	
}
