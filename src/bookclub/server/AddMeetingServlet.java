package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class AddMeetingServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String id = req.getParameter("clubId");
		Filter locationFilter = new FilterPredicate("clubId",
				FilterOperator.EQUAL, id);
		Query q = new Query("Meeting").setFilter(locationFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		String title = req.getParameter("title");
		String clubId = req.getParameter("clubId");
		String location = req.getParameter("location");

		DateFormat formatter;
		Date date = null;
		formatter = new SimpleDateFormat("dd-MMM-yy");
		try {
			date = formatter.parse(req.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (result == null) {
			Entity meeting = new Entity("Meeting");
			meeting.setProperty("title", title);
			meeting.setProperty("location", location);
			meeting.setProperty("clubId", clubId);
			meeting.setProperty("date", date);
			datastore.put(meeting);

		} else {
			result.setProperty("title", title);
			result.setProperty("location", location);
			result.setProperty("title", title);
			result.setProperty("date", date);

			datastore.put(result);
			out.print("new meeting added");

		}

	}

}
