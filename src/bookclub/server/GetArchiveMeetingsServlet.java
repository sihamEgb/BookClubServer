package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookclub.server.entities.Meeting;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class GetArchiveMeetingsServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String clubId = req.getParameter("clubId");

		Filter idFilter = new FilterPredicate("clubId", FilterOperator.EQUAL,
				clubId);

		Query q = new Query("ArchivedMeeting").setFilter(idFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		PreparedQuery pq = datastore.prepare(q);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.print("{ \"results\": [ ");
		for (Entity result : pq.asIterable()) {
			Meeting c = new Meeting(result);
			out.print(c.toJson());

			out.print(", ");

		}
		out.print("]}");

	}

}
