package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

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

		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL,
				email);

		Query q = new Query("User").setFilter(emailFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		PreparedQuery pq = datastore.prepare(q);
		resp.setContentType("text/plain");
		Entity result = pq.asSingleEntity();

		if (result == null) {
			Entity user = new Entity("User");
			user.setProperty("name", name);
			user.setProperty("email", email);
			user.setProperty("Date", new Date());
			Key myKey = datastore.put(user);
			resp.getWriter().println(Long.toString(myKey.getId()));

		} else
			resp.getWriter().println(Long.toString(result.getKey().getId()));

	}

}
