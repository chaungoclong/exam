package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private String host;
	private String port;
	private String dbname;
	private String username;
	private String password;
	private Connection connection;
	private QueryBuilder query;

	public DB(String host, String port, String dbname, String username, String password) {
		this.host = host;
		this.port = port;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
	}

	// open connection
	private void open() throws SQLException {
		if (this.connection == null || this.connection.isClosed()) {
			String dsn = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
			this.connection = DriverManager.getConnection(dsn, this.username, this.password);
		}
	}

	// close connection
	private void close() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			this.connection.close();
		}
	}

	// _query: base query for insert, delete, update
	public int _query(String sql, Object[] params) throws SQLException {
		this.open();
		PreparedStatement pstmt = this.connection.prepareStatement(sql);

		// bind param if exist
		if (params != null) {
			int index = 1;
			for (Object object : params) {
				pstmt.setObject(index, object);
				index++;
			}
		}

		// execute sql and save number of row change to rowChange
		int rowChange = pstmt.executeUpdate();

		this.close();
		return rowChange;
	}

	// _get: base query for select
	public ResultSet _get(String sql, Object[] params) throws SQLException {
		this.open();
		PreparedStatement pstmt = this.connection.prepareStatement(sql);

		// bind param if exist
		if (params != null) {
			int index = 1;
			for (Object object : params) {
				pstmt.setObject(index, object);
				index++;
			}
		}

		// execute sql and return ResultSet
		ResultSet result = pstmt.executeQuery();

		return result;
	}

	// select
	public ResultSet select(String table, String columns, String condition, Object[] params) throws SQLException {
		this.open();

		// make sql
		this.query = new QueryBuilder();
		this.query.select(columns).from(table).where(condition);

		return this._get(this.query.getQuery(), params);
	}

	// select distinct
	public ResultSet selectDistinct(String table, String columns, String condition, Object[] params)
			throws SQLException {
		this.open();

		// make sql
		this.query = new QueryBuilder();
		this.query.selectDistinct(columns).from(table).where(condition);

		PreparedStatement pstmt = this.connection.prepareStatement(this.query.getQuery());

		// bind param if exist
		if (params != null) {
			int index = 1;
			for (Object object : params) {
				pstmt.setObject(index, object);
				index++;
			}
		}

		ResultSet result = pstmt.executeQuery();
		return result;
	}

	// select all
	public ResultSet selectAll(String table, String condition, Object[] params) throws SQLException {
		return this.select(table, "*", condition, params);
	}

	// update
	public int update(String table, String columns, String condition, Object[] params) throws SQLException {
		this.query = new QueryBuilder();
		String sql = this.query.update(table).set(columns).where(condition).getQuery();
		return this._query(sql, params);
	}

	// insert
	public int insert(String table, String columns, Object[] params) throws SQLException {
		this.query = new QueryBuilder();
		String sql = this.query.insert(table, columns).values(params).getQuery();
		return this._query(sql, params);
	}

	// delete
	public int delete(String table, String condition, Object[] params) throws SQLException {
		this.query = new QueryBuilder();
		String sql = this.query.delete(table).where(condition).getQuery();
		return this._query(sql, params);
	}

	public static void main(String[] args) {
		DB db = new DB("localhost", "3306", "exercises_2", "long", "tnt");

		try {
			Object[] params = { "hellokk" };
			ResultSet rs = db.select("class", "class_id, class_name", "class_name = ?", params);
			while(rs.next()) {
				System.out.println(rs.getString("class_name") + "-" + rs.getString("class_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
