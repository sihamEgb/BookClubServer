
package bookclub.server.entities;

import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class Club {

	private String clubId;
	private String adminId;
	private String name;
	private String location;
	//private String category;
	private String description;
	private String imageUrl;
	// TODO remove
	private Set<String> members;

	public Club(Entity result) {
		clubId = (String) result.getProperty("clubId");
		adminId = (String) result.getProperty("adminId");
		name = (String) result.getProperty("name");
		location = (String) result.getProperty("location");
		//category = (String) result.getProperty("category");
		description = (String) result.getProperty("description");
		imageUrl = (String) result.getProperty("imageUrl");
		// TODO - what to do with memebrs??
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getMemberId() {
		return members;
	}

	public void addMemeber(String newMemeber) {
		this.members.add(newMemeber);
	}

	/*
	 * returns number of members in this club
	 */

	public int getNumber() {
		return members.size();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
