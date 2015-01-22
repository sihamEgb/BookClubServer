package bookclub.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		Filter userFilter = new FilterPredicate("userId", FilterOperator.EQUAL,
				userId);
		Filter meetingFilter = new FilterPredicate("meetingId",
				FilterOperator.EQUAL, meetingId);
		Filter andFilter = CompositeFilterOperator.and(userFilter,
				meetingFilter);
		Query q2 = new Query("JoinMeeting").setFilter(andFilter);

		PreparedQuery pq2 = datastore.prepare(q2);
		Entity result2 = pq2.asSingleEntity();

		if (op.equals("leave") && (result2 == null))
			resp.getWriter().println("member did not joined this meeting");
		else if (op.equals("join") && (result2 != null))
			resp.getWriter().println("member already joined this meeting");

		else if (op.equals("leave")) {
			datastore.delete(result2.getKey());
			resp.getWriter().println("member leaved meeting");
		} else {

			Entity JoinMeeting = new Entity("JoinMeeting");
			JoinMeeting.setProperty("meetingId", meetingId);
			JoinMeeting.setProperty("userId", userId);

			datastore.put(JoinMeeting);
			resp.getWriter().println("member joined meeting");
		}

	}

}
