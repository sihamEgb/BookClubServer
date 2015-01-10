package bookclub.server.entities;

import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class SuggestedBook {
	private String suggestedBookId;
	private String title;
	// private String goodReadsUrl;
	private Number numOfLikes;
	private String clubId;

	// private Set<String> usersId;

	public SuggestedBook(Entity result) {
		suggestedBookId = (String) result.getProperty("suggestedBookId");
		this.title = (String) result.getProperty("title");
		// goodReadsUrl = (String) result.getProperty("goodReadsUrl");
		numOfLikes = (Number) result.getProperty("numOfLikes");
		clubId = (String) result.getProperty("clubId");

		// TODO - usersId??
	}

	public String getSuggestedBookId() {
		return suggestedBookId;
	}

	public void setSuggestedBookId(String suggestedBookId) {
		this.suggestedBookId = suggestedBookId;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static SuggestedBook constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, SuggestedBook.class);
	}

	/*
	 * public boolean addLike(String userId) { if (usersId.contains(userId) ==
	 * true) return false; numOfLikes++; usersId.add(userId); return true; }
	 * 
	 * public boolean removeLike(String userId) { if (usersId.contains(userId)
	 * == false) return false; numOfLikes--; usersId.remove(userId); return
	 * true; }
	 */

}
