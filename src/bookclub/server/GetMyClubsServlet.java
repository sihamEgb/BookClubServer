package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.Query.Filter;

import bookclub.server.entities.Club;

@SuppressWarnings("serial")
public class GetMyClubsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String userId = req.getParameter("userId");

		Filter idFilter = new FilterPredicate("userId", FilterOperator.EQUAL,
				userId);

		// Use class Query to assemble a query
		Query q = new Query("JoinClub").setFilter(idFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.print("{ \"results\": [ ");
		for (Entity result : pq.asIterable()) {

			Key myKey = KeyFactory.createKey("Club",
					Long.parseLong((String) result.getProperty("clubId")));

			Filter keyFilter = new FilterPredicate(
					Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, myKey);

			Query q2 = new Query("Club").setFilter(keyFilter);
			PreparedQuery pq2 = datastore.prepare(q2);
			Entity result2 = pq2.asSingleEntity();
			
			//out.print(result2);
			Club c = new Club(result2);
			out.print(c.toJson());

			out.print(", ");

		}
		out.print("]}");

	}

}
