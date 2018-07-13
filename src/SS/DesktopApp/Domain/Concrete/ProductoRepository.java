package SS.DesktopApp.Domain.Concrete;

import java.util.List;

import org.jooq.DSLContext;

import SS.DesktopApp.Domain.Entities.ProductoRecord;
import static SS.DesktopApp.Domain.Tables.PRODUCTO;

public class ProductoRepository implements IRepository<ProductoRecord>{
	
	private DSLContext _dbContext;
	
	public ProductoRepository(DSLContext dbContext) {
		_dbContext = dbContext;
	}

	@Override
	public ProductoRecord GetById(Object id) {
		return _dbContext.selectFrom(PRODUCTO).where(PRODUCTO.CLAVE.eq((String)id)).fetchOne();
	}

	@Override
	public List<ProductoRecord> GetAll() {
		return _dbContext.selectFrom(PRODUCTO).fetch();
	}

	@Override
	public void Add(ProductoRecord entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(ProductoRecord entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Edit(ProductoRecord entity) {
		// TODO Auto-generated method stub
		
	}

}
