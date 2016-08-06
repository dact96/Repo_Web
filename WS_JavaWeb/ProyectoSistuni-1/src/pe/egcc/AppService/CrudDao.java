package pe.egcc.AppService;

import java.util.List;

public interface CrudDao<T> {

	void create (T o) throws Exception;
	void update (T o) throws Exception;
	void delete (T o) throws Exception;
	T find(Object o)  throws Exception;
	List<T> readAll() throws Exception;
}
