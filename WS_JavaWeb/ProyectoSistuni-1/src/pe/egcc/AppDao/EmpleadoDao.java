package pe.egcc.AppDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.egcc.AppDB.ConexionDB;
import pe.egcc.AppDto.EmpleadoDTO;
import pe.egcc.AppService.CrudDao;

public class EmpleadoDao implements CrudDao<EmpleadoDTO> {
	
	 //variables
    Connection cn = null;
    CallableStatement cs = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

	@Override
	public void create(EmpleadoDTO o) throws Exception {
		try {
            cn = ConexionDB.getConnection();
            String cod = generaCodigo();//metodo genera codigo de producto
            o.setIdempleado(cod);
            sql = "insert into empleados(idempleado,nombre,apellidos,email,usuario,clave)" + " values(?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getIdempleado());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getApellidos());
            ps.setString(4, o.getEmail());
            ps.setString(5, o.getUsuario());
            ps.setString(6, o.getClave());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public void update(EmpleadoDTO o) throws Exception {
		try {
            cn = ConexionDB.getConnection();
            sql = "update into empleados set nombre=?,apellidos=?,email=?,usuario=?,clave=?" + " where idempleado=?";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getApellidos());
            ps.setString(3, o.getEmail());
            ps.setString(4, o.getUsuario());
            ps.setString(5, o.getClave());
            ps.setString(6, o.getIdempleado());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public void delete(EmpleadoDTO o) throws Exception {
		
		try {
            cn = ConexionDB.getConnection();
            sql = "delete from empleados where idempleado=?";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getIdempleado());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public EmpleadoDTO find(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpleadoDTO> readAll() throws Exception {
		List<EmpleadoDTO> lista = new ArrayList<>();
        try {
            cn = ConexionDB.getConnection();
            String sql = "select * from empleados";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            lista = cargaLista(rs);
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
	}

	private String generaCodigo() throws SQLException {
        String sql = "select valor from control where parametro='Empleados'";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int cont = rs.getInt(1);
        rs.close();
        sql = "update control set valor=valor+1 where parametro='Empleados'";
        ps = cn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        String cod = "";
        if (cont < 10) {
            cod = "E000" + cont;
        } else {
            cod = "E00" + cont;
        }
        return cod;
    }

    private List<EmpleadoDTO> cargaLista(ResultSet rs) throws SQLException {
        List<EmpleadoDTO> aux = new ArrayList<>();
        while (rs.next()) {
            EmpleadoDTO em = new EmpleadoDTO();
            em.setIdempleado(rs.getString(1));
            em.setNombre(rs.getString(2));
            em.setApellidos(rs.getString(3));
            em.setEmail(rs.getString(4));
            aux.add(em);
        }
        rs.close();
        return aux;
    }
	
}
