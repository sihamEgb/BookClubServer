package bookclub.server.entities;

import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class Meeting {
	private String meetingId;
	private String suggestedBookId;
	private String date;
	private String location;
	private Set<String> participants;

	public Meeting(Entity result) {
		meetingId = (String) result.getProperty("meetingId");
		suggestedBookId = (String) result.getProperty("suggestedBookId");
		date = (String) result.getProperty("date");
		location = (String) result.getProperty("location");
		

		// TODO - what to do with participants??
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	
	public String getDate() {
		return date;
	}

	public Integer getParticipantsNumber() {
		return participants.size();
	}

	public void addParticipant(String participant) {
		participants.add(participant);
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