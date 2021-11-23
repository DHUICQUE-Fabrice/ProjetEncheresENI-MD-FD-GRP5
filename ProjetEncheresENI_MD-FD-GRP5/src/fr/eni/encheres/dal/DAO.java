package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	public T insert(T obj);

	public List<T> selectAll();
}
