package SS.DesktopApp.Domain.Concrete;

import java.util.List;

public interface IRepository<T> {
	
	public T GetById(Object id);
	public List<T> GetAll();
	public void Add(T entity);
	public void Delete(T entity);
	public void Edit(T entity);
	
}
