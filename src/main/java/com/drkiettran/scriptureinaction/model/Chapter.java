package com.drkiettran.scriptureinaction.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chapter {
	@JsonProperty("chapter_id")
	private String chapterId;

	@JsonProperty("chapter_number")
	private int chapterNumber;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("verse_ids")
	private List<String> verseIds;

	@JsonProperty("verses")
	private List<Verse> verses;

	@JsonProperty("link_ids")
	private List<String> linkIds;

	@JsonProperty("links")
	private List<Link> links;

	@JsonProperty("comment_ids")
	private List<String> commentIds;

	@JsonProperty("comments")
	private List<Commentary> comments;

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
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

	public List<String> getVerseIds() {
		return verseIds;
	}

	public void setVerseIds(List<String> verseIds) {
		this.verseIds = verseIds;
	}

	public List<Verse> getVerses() {
		return verses;
	}

	public void setVerses(List<Verse> verses) {
		this.verses = verses;
	}

	public List<String> getLinkIds() {
		return linkIds;
	}

	public void setLinkIds(List<String> linkIds) {
		this.linkIds = linkIds;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<String> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(List<String> commentIds) {
		this.commentIds = commentIds;
	}

	public List<Commentary> getComments() {
		return comments;
	}

	public void setComments(List<Commentary> comments) {
		this.comments = comments;
	}

	public String logChapterSummary() {
		StringBuilder sb = new StringBuilder("  chapter ").append(this.chapterNumber);
		sb.append(" has ").append(this.verses.size()).append(" verses:\n");
		for (int verseIdx = 0; verseIdx < verses.size(); verseIdx++) {
			sb.append(verses.get(verseIdx).logVerseSummary());
		}
		return sb.toString();
	}

}
