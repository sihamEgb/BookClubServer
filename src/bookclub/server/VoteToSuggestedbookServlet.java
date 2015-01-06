package bookclub.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class VoteToSuggestedbookServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String id = req.getParameter("suggestedBookId");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Key myKey = KeyFactory.createKey("SuggestedBook", Long.parseLong(id));

		Filter keyFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.EQUAL, myKey);
		Query q = new Query("SuggestedBook").setFilter(keyFilter);

		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		out.print("bla bla");

		String title = (String) result.getProperty("title");
		Number numOfLikes = (Number) result.getProperty("numOfLikes");
		Integer num = numOfLikes.intValue();
		num++;
		numOfLikes = num;
		// = numOfLikes+1;

		result.setProperty("numOfLikes", numOfLikes);
		datastore.put(result);
		// result.
		// Integer numOfLikes = (Integer) result.getProperty("numOfLikes");

		// datastore.delete(myKey);
		out.print("num of likes for " + title + "is: " + numOfLikes);

	}

}
