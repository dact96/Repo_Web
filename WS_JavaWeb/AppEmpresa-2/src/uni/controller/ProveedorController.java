
package uni.controller;

import uni.dao.ProveedorDao;
import uni.entity.ProveedorTo;
import java.util.List;

/**
 *
 * @author alumno
 */
public class ProveedorController {
    
    //variables
    ProveedorDao dao;
    
    //constructor

    public ProveedorController() {
        dao=new ProveedorDao();
    }
    
    //metodos
     public void ProveedorAdicionar(ProveedorTo o) throws Exception {
        dao.create(o);
    }

    public void ProveedorActualizar(ProveedorTo o) throws Exception {
        dao.update(o);
    }

    public void ProveedorEliminar(ProveedorTo o) throws Exception {
        dao.delete(o);
    }
    
    public ProveedorTo ProveedorBuscar(Object o) throws Exception {
        return dao.find(o);
    }  
    
    public List<ProveedorTo> ProductoListar(String id) throws Exception {
        return dao.readAll(id);
    }
    
    
}
