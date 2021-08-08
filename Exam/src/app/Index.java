package app;

import database.DB;

public class Index {
	public static void main(String[] args) {
		DB db = new DB("localhost", "3306", "exam", "long", "tnt");
		Route route = new Route(db);
		route.run();
	}
}
