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
import bookclub.server.entities.SuggestedBook;

@SuppressWarnings("serial")
public class GetSuggestedBooksServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String clubId = req.getParameter("clubId");

		Filter idFilter = new FilterPredicate("clubId", FilterOperator.EQUAL,
				clubId);

		// Use class Query to assemble a query
		Query q = new Query("SuggestedBook").setFilter(idFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		// out.print("bla bla");

		out.print("{ \"results\": [ ");
		for (Entity result : pq.asIterable()) {
			SuggestedBook c = new SuggestedBook(result);
			out.print(c.toJson());

			out.print(", ");

		}
		out.print("]}");

	}

}
