package bookclub.server.entities;

import java.util.Date;
import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class Meeting {
	private String meetingId;
	private String title;
	//private String suggestedBookId;
	private Date date;
	private String location;
	private String clubId;

	// private Set<String> participants;

	public Meeting(Entity result) {

		meetingId = Long.toString(result.getKey().getId());
		//suggestedBookId = (String) result.getProperty("suggestedBookId");
		date = (Date) result.getProperty("date");
		location = (String) result.getProperty("location");
		title = (String) result.getProperty("title");
		clubId = (String) result.getProperty("clubId");

		// TODO - what to do with participants??
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static Meeting constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Meeting.class);
	}

}