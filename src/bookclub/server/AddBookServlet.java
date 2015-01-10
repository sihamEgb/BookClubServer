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
public class AddBookServlet extends HttpServlet {

	/**
	 * add a new club return the club added with his assigned id
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String title = req.getParameter("title");
		String location = req.getParameter("location");
		String author = req.getParameter("author");
		String language = req.getParameter("language");
		String ownerId = req.getParameter("ownerId");
		String imageUrl = req.getParameter("imageUrl");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity book = new Entity("Book");
		book.setProperty("title", title);
		book.setProperty("location", location);
		book.setProperty("author", author);
		book.setProperty("language", language);
		book.setProperty("ownerId", ownerId);
		book.setProperty("imageUrl", imageUrl);
		book.setProperty("isAvailable", true);

		book.setProperty("Date", new Date());

		try {
			datastore.put(book);
		} catch (DatastoreFailureException d) {
			d.printStackTrace();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("book added");

	}

}
