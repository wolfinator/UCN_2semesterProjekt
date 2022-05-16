package ctrl;

import java.util.List;

import db.TableDB;
import db.TableDBIF;
import model.Table;

public class TableCtrl {
	private TableDBIF tableDB;

	public TableCtrl() {
		tableDB = new TableDB();
	}
	
	public List<Table> getTables() {
		List<Table> res;
		res = tableDB.getTables();
		return res;
	}
}
