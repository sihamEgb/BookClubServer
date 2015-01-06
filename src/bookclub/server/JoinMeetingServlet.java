package bookclub.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class JoinMeetingServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String meetingId = req.getParameter("meetingId");
		String userId = req.getParameter("userId");
		String op = req.getParameter("op");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		resp.setContentType("text/plain");
		if (op.equals("join")) {

			Entity joinMeeting = new Entity("JoinMeeting");
			joinMeeting.setProperty("meetingId", meetingId);
			joinMeeting.setProperty("userId", userId);

			try {
				datastore.put(joinMeeting);
			} catch (DatastoreFailureException d) {
				d.printStackTrace();
			}

			resp.getWriter().println("member joined meeting");
		} else {

			Filter userFilter = new FilterPredicate("userId",
					FilterOperator.EQUAL, userId);

			Filter meetingFilter = new FilterPredicate("meetingId",
					FilterOperator.EQUAL, meetingId);

			Filter andFilter = CompositeFilterOperator.and(userFilter,
					meetingFilter);

			Query q = new Query("JoinMeeting").setFilter(andFilter);

			PreparedQuery pq = datastore.prepare(q);
			Entity result = pq.asSingleEntity();
			datastore.delete(result.getKey());

			resp.getWriter().println("member leaved meeting");
		}
	}

}
