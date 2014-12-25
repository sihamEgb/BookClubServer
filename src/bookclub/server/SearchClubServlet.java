package bookclub.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookclub.server.entities.Club;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class SearchClubServlet extends HttpServlet {

	/**
	 * search a club by: location category
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// TODO - for now it is search club by location!!

		String location = req.getParameter("location");

		// TODO
		// String category = req.getParameter("cat");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("Club").setFilter(new FilterPredicate("location",
				FilterOperator.EQUAL, location));
		PreparedQuery pq = datastore.prepare(q);
		// TODO - not as single !!
		Entity result = pq.asSingleEntity();

		Club foundClub = new Club();
		foundClub.toJson(result);

		resp.setContentType("text/plain");
		// resp.getWriter().println("Hello, world");
		resp.getWriter().println("name: " + foundClub.getName());

	}
}
