package bookclub.server;

import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


@SuppressWarnings("serial")
public class AddMeetingServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		// TODO
		// private Set<String> participants;
		
		String suggestedBookId = req.getParameter("suggestedBookId");
		String date = req.getParameter("date");
		String location = req.getParameter("location");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity meeting = new Entity("Meeting");
		meeting.setProperty("suggestedBookId", suggestedBookId);
		meeting.setProperty("location", location);
		meeting.setProperty("date", date);

		try {
			datastore.put(meeting);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("meeting added");

	}
}
