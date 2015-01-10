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
public class AddSuggestedbookServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// TODO
		// private Set<String> usersId;

		String title = req.getParameter("title");
		//String goodReadsUrl = req.getParameter("goodReadsUrl");
		String clubId = req.getParameter("clubId");
		

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity suggestedBook = new Entity("SuggestedBook");
		suggestedBook.setProperty("title", title);
		//suggestedBook.setProperty("goodReadsUrl", goodReadsUrl);
		suggestedBook.setProperty("numOfLikes", 0);
		suggestedBook.setProperty("clubId", clubId);
		
		suggestedBook.setProperty("Date", new Date());

		try {
			datastore.put(suggestedBook);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("suggested book added");

	}

}
