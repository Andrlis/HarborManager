package data_base;

import java.util.List;

public interface AllDAO<T> {

	public void insert(T data);

	public List<T> select();

}
