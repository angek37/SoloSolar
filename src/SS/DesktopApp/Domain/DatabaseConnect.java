package SS.DesktopApp.Domain;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class DatabaseConnect {
	private static DSLContext context;
	private static DatabaseConnect instance;
	
	private DatabaseConnect() {
		
	}
	
	public static DatabaseConnect getInstance() {
		return instance;
	}

	public static DSLContext getContext() {
		String userName = "admin";
		String password = "solosolo";
		String url = "jdbc:derby:db/";
		
		try {
			Connection conn = DriverManager.getConnection(url,userName, password);
			context = DSL.using(conn, SQLDialect.DERBY);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return context;
	}

}
