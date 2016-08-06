package pe.egcc.AppDao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import pe.egcc.AppDB.ConexionDB;
import pe.egcc.AppDto.LineaDTO;
import pe.egcc.AppService.CrudDao;

public class LineaDao implements CrudDao<LineaDTO> {

	// variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
	
	@Override
	public void create(LineaDTO o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(LineaDTO o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LineaDTO o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LineaDTO find(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineaDTO> readAll() throws Exception {
		List<LineaDTO> lista = new ArrayList<>();
        try {
            cn = ConexionDB.getConnection();
            sql = "select * from lineas order by idlinea";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LineaDTO li = new LineaDTO();
                li.setIdlinea(rs.getInt(1));
                li.setNombre(rs.getString(2));
                lista.add(li);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }
	

}
