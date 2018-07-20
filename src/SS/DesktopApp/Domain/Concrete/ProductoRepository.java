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
		return _dbContext
				.selectFrom(PRODUCTO)
				.where(PRODUCTO.CLAVE.eq(id.toString()))
				.fetchOne();
	}

	@Override
	public List<ProductoRecord> GetAll() {
		return _dbContext
				.selectFrom(PRODUCTO).fetch();
	}

	@Override
	public int Add(ProductoRecord entity) {
		return _dbContext
				.executeInsert(entity);
	}

	@Override
	public void Delete(Object id) {
		_dbContext
		.delete(PRODUCTO)
		.where(PRODUCTO.CLAVE.eq(id.toString()));
	}

	@Override
	public void Edit(ProductoRecord entity) {
		_dbContext
		.update(PRODUCTO)
		.set(entity)
		.execute();
	}

}
