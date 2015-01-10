package bookclub.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AddClubServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// private String clubId;
		String name = req.getParameter("name");
		String location = req.getParameter("location");
		String description = req.getParameter("description");
		String admin = req.getParameter("adminId");
		String imageUrl = req.getParameter("imageUrl");
		// private Set<String> members;

		//if (imageUrl.equals(null))
		//	imageUrl = "http://www.viduman.com/dosya/default.jpg";
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity club = new Entity("Club");
		club.setProperty("name", name);
		club.setProperty("location", location);
		club.setProperty("description", description);
		club.setProperty("adminId", admin);
		club.setProperty("imageUrl", imageUrl);
		club.setProperty("memeberNum", 0);

		club.setProperty("date", new Date());

		try {
			datastore.put(club);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("club added");

	}

}
