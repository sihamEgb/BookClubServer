package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookclub.server.entities.Book;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.Query.Filter;

import com.google.appengine.api.datastore.Query.CompositeFilterOperator;

@SuppressWarnings("serial")
public class SearchBookServlet extends HttpServlet {

	/**
	 * search a book by: location category
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String location = req.getParameter("location");
		String title = req.getParameter("title");
		String language = req.getParameter("language");

		Filter locationFilter = new FilterPredicate("location",
				FilterOperator.EQUAL, location);

		Filter languageFilter = new FilterPredicate("language",
				FilterOperator.EQUAL, language);

		Filter titleFilter = new FilterPredicate("title", FilterOperator.EQUAL,
				title);

		Filter andFilter = CompositeFilterOperator.and(locationFilter,
				languageFilter, titleFilter);

		Query q = new Query("Book").setFilter(andFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

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
