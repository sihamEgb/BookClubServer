package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.Query.Filter;

import bookclub.server.entities.Club;

@SuppressWarnings("serial")
public class SearchClubServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String location = req.getParameter("location");

		Filter locationFilter = new FilterPredicate("location",
				FilterOperator.EQUAL, location);

		// Use class Query to assemble a query
		Query q = new Query("Club").setFilter(locationFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.print("{ \"results\": [ ");
		for (Entity result : pq.asIterable()) {
			Club c = new Club(result);
			out.print(c.toJson());

			out.print(", ");

		}
		out.print("]}");

	}

}
