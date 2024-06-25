package book.rest.application.model;

import java.util.Date;

/*
 * 
 */
public class Book {
	private String bookName;
	private String author;
	private long price;
	private Date publishDate;
	private String bookType;
	private String screenName;

	public String getBookName() {
		return bookName;
	}

	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	
	public String getScreenName() {
		return screenName;
	}


	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", author=" + author + ", price=" + price + ", publishDate=" + publishDate
				+ ", bookType=" + bookType + ", screenName=" + screenName + "]";
	}


	


}
