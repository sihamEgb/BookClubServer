package bookclub.server.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class Book {
	private String bookId;
	private String ownerId;
	private String title;
	private String author;
	private String location;
	private String language;
	private String imageUrl; // or default image
	private Boolean isAvailable;

	public Book(Entity result) {
		bookId = Long.toString(result.getKey().getId());
		ownerId = (String) result.getProperty("ownerId");
		title = (String) result.getProperty("title");
		author = (String) result.getProperty("author");
		location = (String) result.getProperty("location");
		language = (String) result.getProperty("language");
		imageUrl = (String) result.getProperty("imageUrl");
		isAvailable = (Boolean) result.getProperty("isAvailable");

	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public static Book constructFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Book.class);
	}

}
