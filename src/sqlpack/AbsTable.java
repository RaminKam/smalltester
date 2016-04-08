package sqlpack;
import java.sql.*;
import java.util.Vector;
abstract class AbsTable {
	Connection conn = null;
	Statement stmt = null;
	AbsTable(Connection conn,Statement stmt) {
		this.conn=conn; this.stmt=stmt;

	}
	
}
