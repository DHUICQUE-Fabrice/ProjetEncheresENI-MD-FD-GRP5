package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	public void insert(T obj);

	public List<T> selectAll();
}
