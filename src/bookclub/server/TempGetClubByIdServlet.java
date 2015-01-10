package bookclub.server;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookclub.server.entities.Club;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@SuppressWarnings("serial")
public class TempGetClubByIdServlet extends HttpServlet {

	/**
	 * get a club by his id number
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String id = req.getParameter("id");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		// TODO - not working
		//Club myClub = datastore.getObjectById(Club.class, id);

		//Query q = new Query("Club").setFilter(new FilterPredicate("id",
		//		FilterOperator.EQUAL, id));
		//PreparedQuery pq = datastore.prepare(q);
		//Entity result = pq.asSingleEntity();

		// Club foundClub = new Club();
		// foundClub.toJson(result);

		resp.setContentType("text/plain");
		resp.getWriter().println("hello world");
		//resp.getWriter().println("name: " + myClub.getName());

	}
}
