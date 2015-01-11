package bookclub.server;

import java.io.IOException;

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
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class JoinClubServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String clubId = req.getParameter("clubId");
		String userId = req.getParameter("userId");
		String op = req.getParameter("op");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		resp.setContentType("text/plain");

		Key myKey = KeyFactory.createKey("Club", Long.parseLong(clubId));
		Filter keyFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.EQUAL, myKey);
		Query q = new Query("Club").setFilter(keyFilter);
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		Number memeberNum = (Number) result.getProperty("memeberNum");
		Integer num = memeberNum.intValue();

		Filter userFilter = new FilterPredicate("userId", FilterOperator.EQUAL,
				userId);
		Filter clubFilter = new FilterPredicate("clubId", FilterOperator.EQUAL,
				clubId);
		Filter andFilter = CompositeFilterOperator.and(userFilter, clubFilter);
		Query q2 = new Query("JoinClub").setFilter(andFilter);

		PreparedQuery pq2 = datastore.prepare(q2);
		Entity result2 = pq2.asSingleEntity();

		if (result2 != null)
			resp.getWriter().println("member already joined this club");
		else {
			if (op.equals("leave")) {
				datastore.delete(result2.getKey());
				num--;
				memeberNum = num;
				result.setProperty("memeberNum", memeberNum);
				datastore.put(result);
				resp.getWriter().println("member leaved club");
			} else {

				Entity joinClub = new Entity("JoinClub");
				joinClub.setProperty("clubId", clubId);
				joinClub.setProperty("userId", userId);

				num++;

				memeberNum = num;
				result.setProperty("memeberNum", memeberNum);
				datastore.put(result);

				datastore.put(joinClub);
				resp.getWriter().println("member joined club");
			}
		}

	}

}
