package uni.dao;

import uni.database.AccesoDB;
import uni.entity.ProveedorTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.service.ICrudDao;

/**
 *
 * @author alumno
 */
public class ProveedorDao implements ICrudDao<ProveedorTo> {

    //variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public void create(ProveedorTo o) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            String cod = generaCodigo();//metodo genera codigo de producto
            o.setIdproveedor(cod);
            sql = "insert into proveedores(idproveedor,razonsocial,direccion,ruc,telefono)"
                    + " values(?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            //preparar los valores de los parametros
            ps.setString(1, o.getIdproveedor());
            ps.setString(2, o.getRazonsocial());
            ps.setString(3, o.getDireccion());
            ps.setString(4, o.getRuc());
            ps.setString(5, o.getTelefono());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(ProveedorTo o) throws Exception {
         try {
            cn = AccesoDB.getConnection();
            sql = "update proveedores set razonsocial=?,direccion=?,ruc=?,telefono=?"
                    + " where idproveedor=?";
            ps = cn.prepareStatement(sql);
            //preparar los valores de los parametros           
            ps.setString(1, o.getRazonsocial());
            ps.setString(2, o.getDireccion());
            ps.setString(3, o.getRuc());
            ps.setString(4, o.getTelefono());
            ps.setString(5, o.getIdproveedor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(ProveedorTo o) throws Exception {
       
    try {
            cn = AccesoDB.getConnection();
            sql = "delete from proveedores where idproveedor=?";
            ps = cn.prepareStatement(sql);
            //preparar el valor del parametro         
            ps.setString(1, o.getIdproveedor());
            ps.executeUpdate();
            sql = "update control set valor=valor-1 where parametro='Proveedores'";
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
      
            ps.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public ProveedorTo find(Object o) throws Exception {
    ProveedorTo pr = null;
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from proveedores where idproveedor='" + (String) o + "'";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr = new ProveedorTo();
                pr.setIdproveedor(rs.getString(1));
                pr.setRazonsocial(rs.getString(2));
                pr.setDireccion(rs.getString(3));
                pr.setRuc(rs.getString(4));
                pr.setTelefono(rs.getString(5));
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

    public List<ProveedorTo> readAll(String id) throws Exception {
     List<ProveedorTo> lista = new ArrayList<>();
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from proveedores where idproveedor=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
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
     sql = "select valor from control where parametro='Proveedores'";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int cont = rs.getInt(1);
        rs.close();
        sql = "update control set valor=valor+1 where parametro='Proveedores'";
        ps = cn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        String cod = "";
        if (cont < 10) {
            cod = "P000" + cont;
        } else {
            cod = "P00" + cont;
        }
        return cod;   
    }

    private List<ProveedorTo> cargaLista(ResultSet rs) throws SQLException {
      List<ProveedorTo> aux = new ArrayList<>();
        while (rs.next()) {
            ProveedorTo pr = new ProveedorTo();
            pr.setIdproveedor(rs.getString(1));
            pr.setRazonsocial(rs.getString(2));
            pr.setDireccion(rs.getString(3));
            pr.setRuc(rs.getString(4));
            pr.setTelefono(rs.getString(5));
            aux.add(pr);
        }
        rs.close();
        return aux;  
    }

    @Override
    public List<ProveedorTo> readAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
