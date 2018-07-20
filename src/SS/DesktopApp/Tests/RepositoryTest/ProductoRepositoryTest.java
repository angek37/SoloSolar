package SS.DesktopApp.Tests.RepositoryTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import SS.DesktopApp.Domain.DatabaseConnect;
import SS.DesktopApp.Domain.Concrete.ProductoRepository;
import SS.DesktopApp.Domain.Entities.ProductoRecord;

public class ProductoRepositoryTest {
	
	ProductoRepository repository;
	
	public ProductoRepositoryTest() {
		repository = new ProductoRepository(DatabaseConnect.getContext());
	}
	

	@Test
	public void ObtieneTodosLosRegistros() {
		// Arrange
		
		// Act
		List<ProductoRecord> products = repository.GetAll();
		// Assert
		assertNotNull(products);
	}
	
	@Test
	public void InsertaUnRegistro() {
		// Arrange
		ProductoRecord product = new ProductoRecord("D1", "Producto1", "1", 12, 20.0, 28.5, 25.0, true, 2, 20);
		// Act
		int result = repository.Add(product);
		System.out.print(result);
		// Assert
		assertEquals(1, result);
	}
}
