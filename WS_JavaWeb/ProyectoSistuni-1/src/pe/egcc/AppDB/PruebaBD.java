package pe.egcc.AppDB;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Connection cn = ConexionDB.getConnection();
            System.out.println("Conexion conforme....");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("error:" + e.getMessage());
        }
	}

}
