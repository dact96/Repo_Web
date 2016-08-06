package pe.egcc.AppDao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import pe.egcc.AppDB.ConexionDB;
import pe.egcc.AppDto.ProductoDTO;
import pe.egcc.AppService.CrudDao;

public class ProductoDao implements CrudDao<ProductoDTO> {
	
	// variables

    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

	@Override
	public void create(ProductoDTO o) throws Exception {
		
		try {
            cn = ConexionDB.getConnection();
            String cod = generaCodigo();//metodo genera codigo de producto
            o.setIdproducto(cod);
            sql = "insert into productos(idproducto,descripcion,idlinea,preciocompra,precioventa,stock)" + " values(?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getIdproducto());
            ps.setString(2, o.getDescripcion());
            ps.setInt(3, o.getIdlinea());
            ps.setDouble(4, o.getPreciocompra());
            ps.setDouble(5, o.getPrecioventa());
            ps.setInt(6, o.getStock());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public void update(ProductoDTO o) throws Exception {
		try {
            cn = ConexionDB.getConnection();
            sql = "update into productos set descripcion=?,idlinea=?,preciocompra=?,precioventa=?,stock=?" + " where idproducto=?";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getDescripcion());
            ps.setInt(2, o.getIdlinea());
            ps.setDouble(3, o.getPreciocompra());
            ps.setDouble(4, o.getPrecioventa());
            ps.setInt(5, o.getStock());
            ps.setString(6, o.getIdproducto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public void delete(ProductoDTO o) throws Exception {
		try {
            cn = ConexionDB.getConnection();
            sql = "delete from productos where idproducto=?";
            ps = cn.prepareStatement(sql);
            //preparamos los valores de los parametros
            ps.setString(1, o.getIdproducto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
	}

	@Override
	public ProductoDTO find(Object o) throws Exception {
		ProductoDTO pr = null;
        try {
            cn = ConexionDB.getConnection();
            sql = "select * from productos where idproducto='" + (String) o + "'";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr=new ProductoDTO();
                pr.setIdproducto(rs.getString(1));
                pr.setDescripcion(rs.getString(2));
                pr.setIdlinea(rs.getInt(3));
                pr.setPreciocompra(rs.getDouble(4));
                pr.setPrecioventa(rs.getDouble(5));
                pr.setStock(rs.getInt(6));
                
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
        return pr;
	}

	@Override
	public List<ProductoDTO> readAll() throws Exception {
		List<ProductoDTO> lista = new ArrayList<>();
        try {
            cn = ConexionDB.getConnection();
            sql = "select * from productos";
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
	
	
	public List<ProductoDTO> readAll(String nombre) throws Exception {
		List<ProductoDTO> lista = new ArrayList<>();
		try {
			cn = ConexionDB.getConnection();
			sql = "select * from productos where descripcion like ?";
			ps = cn.prepareStatement(sql);
			ps.setString(1, nombre + "%");
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
	
	public List<ProductoDTO> readAll(int id) throws Exception {
		List<ProductoDTO> lista = new ArrayList<>();
		try {
			cn = ConexionDB.getConnection();
			sql = "select * from productos where where idlinea=?";
			ps = cn.prepareStatement(sql);
			ps.setString(1, id + "%");
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
	
	private List<ProductoDTO> cargaLista(ResultSet rs) throws SQLException {
        List<ProductoDTO> aux = new ArrayList<>();
        while (rs.next()) {
            ProductoDTO pr = new ProductoDTO();
            pr.setIdproducto(rs.getString(1));
            pr.setDescripcion(rs.getString(2));
            pr.setIdlinea(rs.getInt(3));
            pr.setPreciocompra(rs.getDouble(4));
            pr.setPrecioventa(rs.getDouble(5));
            pr.setStock(rs.getInt(6));
            aux.add(pr);
        }
        rs.close();
        return aux;
    }

    private String generaCodigo() throws SQLException {
        sql = "select valor from control where parametro='Productos'";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int cont = rs.getInt(1);
        rs.close();
        sql = "update control set valor=valor+1 where parametro='Productos'";
        ps = cn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        String cod = "";
        if (cont < 10) {
            cod = "A000" + cont;
        } else {
            cod = "A00" + cont;
        }
        return cod;
    }

}
