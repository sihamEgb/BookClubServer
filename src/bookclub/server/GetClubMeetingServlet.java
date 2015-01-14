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

import bookclub.server.entities.Meeting;

@SuppressWarnings("serial")
public class GetClubMeetingServlet extends HttpServlet {

	// TODO nice to have
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String clubId = req.getParameter("clubId");

		Filter idFilter = new FilterPredicate("clubId", FilterOperator.EQUAL,
				clubId);

		// Use class Query to assemble a query
		Query q = new Query("Meeting").setFilter(idFilter);// .addSort("date",
															// SortDirection.ASCENDING);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		Entity result = pq.asSingleEntity();

		Meeting c = new Meeting(result);
		out.print(c.toJson());

		// pq.asIterable().

	}

}
