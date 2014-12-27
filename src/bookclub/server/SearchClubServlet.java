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

import com.google.appengine.api.datastore.Query.Filter;

@SuppressWarnings("serial")
public class SearchClubServlet extends HttpServlet {

	/**
	 * search a club by: location category
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// TODO - for now it is search club by location!!
		
		String location = req.getParameter("location");

		Filter locationFilter =
				  new FilterPredicate("location", FilterOperator.EQUAL, location);

		// Use class Query to assemble a query
		Query q = new Query("Club").setFilter(locationFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		resp.getWriter().println("results:");

		for (Entity result : pq.asIterable()) {
			
			//private String clubId;
			
			
		  String name = (String) result.getProperty("name");
		  String description = (String) result.getProperty("description");
		
		  resp.getWriter().println(name + " " + description);

		}


	}
}
