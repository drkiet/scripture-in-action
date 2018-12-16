package com.drkiettran.scriptureinaction.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.util.CommonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Example:
 * 
 * <code>
 * 
 *  usccb: en
 *  
 * 2 King chapter 1
 * 
 * a. [1:1] 2 Kgs 3:4–27.
 * b. [1:10] Lv 10:1–2; Sir 48:3; Lk 9:51–55.
 * c. [1:16] Sir 48:6.
 * 
 * [1:10]: book name, chapter number and and verse number.
 * 
 * 2 Kgs 3:4–27: short name of book, chapter number, starting verse, ending verse.
 * 
 * </code>
 * 
 * @author ktran
 *
 */
public class Link {
	@JsonProperty("link_id")
	private String linkId;

	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("chapter_id")
	private String chapterId;

	@JsonProperty("chapter_number")
	private int chapterNumber;

	@JsonProperty("verse_id")
	private String verseId;

	@JsonProperty("verse_number")
	private int verseNumber;

	@JsonProperty("link_ref")
	private String linkRef;

	@JsonProperty("text")
	private String text;

	@JsonProperty("related_verse_ids")
	private List<String> relatedVerseIds;

	@JsonProperty("related_verses")
	private List<VersePointer> relatedVerses;

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

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

	public String getLinkRef() {
		return linkRef;
	}

	public void setLinkRef(String linkRef) {
		this.linkRef = linkRef;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		parse(text);
	}

	public List<String> getRelatedVerseIds() {
		return relatedVerseIds;
	}

	public void setRelatedVerseIds(List<String> relatedVerseIds) {
		this.relatedVerseIds = relatedVerseIds;
	}

	public List<VersePointer> getRelatedVerses() {
		return relatedVerses;
	}

	public void setRelatedVerses(List<VersePointer> relatedVerses) {
		this.relatedVerses = relatedVerses;
	}

	public void parse(String text) {
		this.text = text;
		StringTokenizer st = new StringTokenizer(text, " .;");
		List<String> tokens = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}

		int index = 0;
		linkRef = tokens.get(index++);

		st = new StringTokenizer(tokens.get(index++), "[:]");
		chapterNumber = Integer.valueOf(st.nextToken());
		verseNumber = Integer.valueOf(CommonUtils.cleansingNumberOnly(st.nextToken()));
		relatedVerses = new ArrayList<VersePointer>();

		while (index < tokens.size()) {
			StringBuilder sb = new StringBuilder();
			if (Character.isDigit(tokens.get(index).charAt(0)) && tokens.get(index).length() == 1) {
				sb.append(tokens.get(index++)).append(' ').append(tokens.get(index++));
			} else {
				sb = new StringBuilder(tokens.get(index++));
			}

			sb.append(' ').append(tokens.get(index++));

			while (sb.charAt(sb.length() - 1) == ',') {
				sb.append(tokens.get(index++));
			}

			VersePointer vp = new VersePointer();
			vp.parse(sb.toString());
			relatedVerses.add(vp);
		}
	}

}
