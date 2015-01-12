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
public class AddUserServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity user = new Entity("User");
		user.setProperty("name", name);
		user.setProperty("email", email);

		user.setProperty("Date", new Date());

		try {
			datastore.put(user);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("user added");

	}

}
