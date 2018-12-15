package com.drkiettran.scriptureinaction.model;

import java.util.List;

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

	@JsonProperty("related_verse_ids")
	private List<String> relatedVerseIds;

	@JsonProperty("commentary_ids")
	private List<String> commentaryIds;

	@JsonProperty("additional_leading_text")
	private String additionalLeadingText;

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
		this.text = text;
	}

	public List<String> getRelatedVerseIds() {
		return relatedVerseIds;
	}

	public void setRelatedVerseIds(List<String> relatedVerseIds) {
		this.relatedVerseIds = relatedVerseIds;
	}

	public List<String> getCommentaryIds() {
		return commentaryIds;
	}

	public void setCommentaryIds(List<String> commentaryIds) {
		this.commentaryIds = commentaryIds;
	}

	public String getAdditionalLeadingText() {
		return additionalLeadingText;
	}

	public void setAdditionalLeadingText(String additionalLeadingText) {
		this.additionalLeadingText = additionalLeadingText;
	}

	public String logVerseSummary() {
		StringBuilder sb = new StringBuilder("   verse ").append(verseNumber);
		sb.append(" has ").append(text.split(" ").length - 1).append(" words.\n");
		return sb.toString();
	}

}
