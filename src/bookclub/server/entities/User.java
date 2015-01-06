package bookclub.server.entities;

import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class User {

	private String userId;
	private String name;
	private String email;
	private Set<String> ownedClubs; // duplicate??
	private Set<String> myJoinedClubs;
	private Set<String> myBooks;
	// meetings
	public User(Entity result) {
		userId = (String) result.getProperty("userId");
		name = (String) result.getProperty("name");
		email = (String) result.getProperty("email");

		// TODO - owned clubs
		// TODO - joined clubs
		// TODO - own books

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getOwnedClubs() {
		return ownedClubs;
	}

	public void setOwnedClubs(Set<String> ownedClubs) {
		this.ownedClubs = ownedClubs;
	}

	public Set<String> getMyJoinedClubs() {
		return myJoinedClubs;
	}

	public void setMyJoinedClubs(Set<String> myJoinedClubs) {
		this.myJoinedClubs = myJoinedClubs;
	}

	public Set<String> getMyBooks() {
		return myBooks;
	}

	public void setMyBooks(Set<String> myBooks) {
		this.myBooks = myBooks;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static User constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, User.class);
	}

}
