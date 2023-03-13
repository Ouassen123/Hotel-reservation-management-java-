package dao;

import java.util.List;

public interface IDAO <T>{
Boolean create(T o);
Boolean update(T o);
Boolean delete(T o);
T findById(int id);
List<T> findAll();
}
