package bookclub.server.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class Club {

	private String clubId;
	private String adminId;
	private String name;
	private String location;
	private String description;
	private String imageUrl;
	private Number memeberNum;
	private String meetingId;

	public Club(Entity result) {

		clubId = Long.toString(result.getKey().getId());
		adminId = (String) result.getProperty("adminId");
		name = (String) result.getProperty("name");
		location = (String) result.getProperty("location");
		description = (String) result.getProperty("description");
		imageUrl = (String) result.getProperty("imageUrl");
		memeberNum = (Number) result.getProperty("memeberNum");
		meetingId = (String) result.getProperty("meetingId");
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static Club constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Club.class);
	}

}
