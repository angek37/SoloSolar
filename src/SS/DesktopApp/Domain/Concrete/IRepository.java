package SS.DesktopApp.Domain.Concrete;

import java.util.List;

public interface IRepository<T> {
	
	public T GetById(Object id);
	public List<T> GetAll();
	public int Add(T entity);
	public void Delete(Object id);
	public void Edit(T entity);
	
}
