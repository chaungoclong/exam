package database;

public class QueryBuilder {
	private StringBuilder query;

	// select
	public QueryBuilder select(String columns) {
		this.query = new StringBuilder();
		this.query.append("SELECT ").append(columns).append(" ");

		return this;
	}

	// select distinct
	public QueryBuilder selectDistinct(String columns) {
		this.query = new StringBuilder();
		this.query.append("SELECT DISTINCT ").append(columns).append(" ");

		return this;
	}

	// from
	public QueryBuilder from(String table) {
		this.query.append(" FROM ").append(table).append(" ");
		return this;
	}

	// where
	public QueryBuilder where(String condition) {
		if (this.query.indexOf("WHERE") != -1) {
			this.query.append(" AND ").append(condition).append(" ");
		} else {
			this.query.append(" WHERE ").append(condition).append(" ");
		}

		return this;
	}

	// delete
	public QueryBuilder delete(String table) {
		this.query = new StringBuilder();
		this.query.append("DELETE FROM ").append(table).append(" ");
		return this;
	}

	// update
	public QueryBuilder update(String table) {
		this.query = new StringBuilder();
		this.query.append("UPDATE ").append(table).append(" ").append("SET ");
		return this;
	}

	// set
	public QueryBuilder set(String strColumns) {
		if (strColumns != null) {
			strColumns = strColumns.trim();

			// check is empty String
			if (strColumns.equals("")) {
				throw new IllegalArgumentException("thieu cot");
			}

			// get array columns from String
			String[] arrColumns = strColumns.split(",");

			// check if empty array
			if (arrColumns.length == 0) {
				throw new IllegalArgumentException("thieu cot");
			}

			// make query
			for (String column : arrColumns) {
				if (column.equals("")) {
					throw new IllegalArgumentException("ten cot khong hop le");
				}
				
				column = column.trim();
				this.query.append(column).append(" = ").append("?, ");
			}

			this.query.deleteCharAt(this.query.lastIndexOf(","));
		}

		return this;
	}
	
	// insert
	public QueryBuilder insert(String table, String columns) {
		this.query = new StringBuilder();
		this.query.append("INSERT INTO ").append(table);
		if (columns != null) {
			this.query.append("(").append(columns).append(")");
		}
		this.query.append(" VALUES");
		return this;
	}
	
	// values
	public QueryBuilder values(Object[] params) {
		int count = params.length;
		if (count == 0) {
			throw new IllegalArgumentException("thieu du lieu");
		}
		
		this.query.append("(");
		for (int i = 0; i < params.length; ++i) {
			this.query.append("?,");
		}
		this.query.deleteCharAt(this.query.lastIndexOf(","));
		this.query.append(");");
		return this;
	}
	
	// raw
	public QueryBuilder raw(String sql) {
		if (this.query == null) {
			this.query = new StringBuilder();
		}
		
		this.query.append(sql).append(" ");
		
		return this;
	}

	// get query
	public String getQuery() {
		String sql = this.query.toString();
		this.query = null;
		return sql;
	}

	public static void main(String[] args) {
		QueryBuilder query = new QueryBuilder();
		Object[] params = {};
		// System.out.println(
		// query.selectDistinct("id, age, name").from("class, abc").where("x >
		// ?").where("y > ?").getQuery());
		try {
			System.out.println(query.update("class").set("class_id").where("a > ?").raw("limit 1").getQuery());
			System.out.println(query.insert("class", null).values(params).getQuery());
			System.out.println(query.raw("select * from abc").getQuery());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
