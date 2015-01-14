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

import bookclub.server.entities.Book;

@SuppressWarnings("serial")
public class GetMyBooksServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String userId = req.getParameter("userId");

		Filter idFilter = new FilterPredicate("ownerId", FilterOperator.EQUAL,
				userId);

		// Use class Query to assemble a query
		Query q = new Query("Book").setFilter(idFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.print("{ \"results\": [ ");
		for (Entity result : pq.asIterable()) {
			Book c = new Book(result);
			out.print(c.toJson());

			out.print(", ");

		}
		out.print("]}");

	}

}
