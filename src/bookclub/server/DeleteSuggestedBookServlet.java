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
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class DeleteSuggestedBookServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String clubId = req.getParameter("clubId");
		String title = req.getParameter("title");

		Filter clubIdFilter = new FilterPredicate("clubId",
				FilterOperator.EQUAL, clubId);
		Filter titleFilter = new FilterPredicate("title", FilterOperator.EQUAL,
				title);

		Filter andAllFilter = CompositeFilterOperator.and(clubIdFilter,
				titleFilter);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query q = new Query("SuggestedBook").setFilter(andAllFilter);

		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();

		if (result != null) {
			datastore.delete(result.getKey());
			out.print("success");

		} else {
			out.print("fail");
		}

	}

}
