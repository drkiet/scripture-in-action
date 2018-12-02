package com.drkiettran.scriptureinaction.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Every component/object requires a unique id
 * 
 * <code>
 * {
 * 	"id" : "e28744c6-bdb3-4e0b-9e81-651f2d68b9b4",
 *  "book_name" : "Leviticus",
 * 	"chapter_no" : 2,
 *  "verse_no" : 3,
 *  "pub_date" : "02-12-2018 12:46:11"
 * }
 * </code>
 * 
 * 
 * @author ktran
 *
 */
public class UniqueId {
	@JsonProperty("id")
	private String id;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("chapter_no")
	private int chapterNo;

	@JsonProperty("verse_no")
	private int verseNo;

	@JsonProperty("pub_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date pubDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(int chapterNo) {
		this.chapterNo = chapterNo;
	}

	public int getVerseNo() {
		return verseNo;
	}

	public void setVerseNo(int verseNo) {
		this.verseNo = verseNo;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

}
