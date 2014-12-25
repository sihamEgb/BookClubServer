package bookclub.server.entities;

import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Club {

	private String clubId;
	private String adminId;
	private String name;
	private String location;
	private String category;
	private String description;
	private Set<String> members;
	private String imageUrl;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Club fromJson(JSONObject json) {

		// TODO
		// Set<String> members;
		// String imageUrl;

		try {
			this.setClubId(json.getString("clubId"));
			this.setAdminId(json.getString("adminId"));
			this.setName(json.getString("name"));
			this.setLocation(json.getString("location"));
			this.setCategory(json.getString("category"));
			this.setDescription(json.getString("description"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return this;
	}

	public Club toJson(Entity result) {
		this.location = (String) result.getProperty("location");
		this.name = (String) result.getProperty("name");
		this.clubId = (String) result.getProperty("clubId");

		this.category = (String) result.getProperty("category");
		this.description = (String) result.getProperty("description");

		return this;

	}

}
