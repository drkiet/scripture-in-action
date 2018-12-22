package com.drkiettran.scriptureinaction.model;

import java.util.List;
import java.util.StringTokenizer;

import org.h2.util.StringUtils;

import com.drkiettran.scriptureinaction.util.CommonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Verse {
	@JsonProperty("verse_id")
	private String verseId;

	@JsonProperty("verse_number")
	private int verseNumber;

	@JsonProperty("chapter_number")
	private int chapterNumber;

	@JsonProperty("chapter_id")
	private String chapterId;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("text")
	private String text;

	@JsonProperty("additional_leading_text")
	private String additionalLeadingText;
	
	@JsonProperty("link")
	private Link link;
	
	@JsonProperty("commentary")
	private Commentary commentary;

	public String getVerseId() {
		return verseId;
	}

	public void setVerseId(String verseId) {
		this.verseId = verseId;
	}

	public int getVerseNumber() {
		return verseNumber;
	}

	public void setVerseNumber(int verseNumber) {
		this.verseNumber = verseNumber;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		parse(text);
	}

	public String getAdditionalLeadingText() {
		return additionalLeadingText;
	}

	public void setAdditionalLeadingText(String additionalLeadingText) {
		this.additionalLeadingText = additionalLeadingText;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String logVerseSummary() {
		StringBuilder sb = new StringBuilder("   verse ").append(verseNumber);
		sb.append(" has ").append(text.split(" ").length - 1).append(" words.\n");
		return sb.toString();
	}

	public void parse(String text) {
		this.text = text;
		verseNumber = -1; // bad number

		int index = text.indexOf(' ');
		if (index < 0) {
			return;
		}

		String number = text.substring(0, index);

		if (StringUtils.isNumber(number)) {
			verseNumber = Integer.valueOf(number);
		}
	}

}
