package org.uv.programaclase04cc;

import java.util.List;

/**
 *
 * @author fermin
 */
public interface IDAOGeneral<T, ID>{
    public T create(T p);
    public boolean delete(ID id);
    public T update(ID id, T p);
    
    public List<T> findAll();
    public T findById(ID id);
}
