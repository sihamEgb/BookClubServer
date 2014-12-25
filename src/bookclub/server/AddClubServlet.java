package bookclub.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@SuppressWarnings("serial")
public class AddClubServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// UserService userService = UserServiceFactory.getUserService();
		// User user = userService.getCurrentUser();

		String name = req.getParameter("name");
		String location = req.getParameter("location");
		String category = req.getParameter("category");
		String description = req.getParameter("description");
		String admin = req.getParameter("admin");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity club = new Entity("Club");
		club.setProperty("name", name);
		club.setProperty("location", location);
		club.setProperty("category", category);
		club.setProperty("description", description);
		club.setProperty("admin", admin);

		Date insertionDate = new Date();
		club.setProperty("date", insertionDate);

		try {
			datastore.put(club);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		//Query q = new Query("Club").addSort("date", SortDirection.DESCENDING);
		//PreparedQuery pq = datastore.prepare(q);
		//List<Entity> resultList = pq.asList(FetchOptions.Builder.withLimit(1));

		resp.setContentType("text/plain");
		resp.getWriter().println("club added");

		//if (resultList.isEmpty())
		//	resp.getWriter().println("club not available");
		//else
		//	resp.getWriter().println(
		//			"club added: " + resultList.get(0).getKey.getId());
		// entity.getKey().getId();
	}
	

}
