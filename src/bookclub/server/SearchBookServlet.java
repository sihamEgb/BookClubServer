package bookclub.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookclub.server.entities.Club;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.Query.Filter;

@SuppressWarnings("serial")
public class SearchBookServlet extends HttpServlet {

	/**
	 * search a book by: location category
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		
		//TODO - search with multiple parameters ??
		String location = req.getParameter("location");
		String query = req.getParameter("title");
		String language = req.getParameter("language");

		Filter locationFilter =
				  new FilterPredicate("location", FilterOperator.EQUAL, location);

		Query q = new Query("Book").setFilter(locationFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		resp.getWriter().println("results:");

		for (Entity result : pq.asIterable()) {
			
			//private String clubId;
			
			
		  String title = (String) result.getProperty("title");
		  
		 Key id = result.getKey();
		 
			//	 getProperty("location");
		
		  resp.getWriter().println(title + " " + location + "the id is: " + id);

		}


	}
}
