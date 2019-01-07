package com.drkiettran.scriptureinaction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * usccb: fn
 * 
 * @author ktran
 *
 */
public class Commentary {
	@JsonProperty("commentary_id")
	private String commentaryId;

	@JsonProperty("commentary")
	private List<String> commentary;

	@JsonProperty("authors")
	private List<String> authors;

	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("chapter_id")
	private String chapterId;

	@JsonProperty("starting_chapter_number")
	private int startingChapterNumber;

	@JsonProperty("ending_chapter_number")
	private int endingChapterNumber;

	@JsonProperty("commentary_ref")
	private String commentaryRef;

	@JsonProperty("verse_id")
	private String verseId;

	@JsonProperty("text")
	private String text;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date pubDate;

	@JsonProperty("starting_verse_number")
	private int startingVerseNumber;

	@JsonProperty("ending_verse_number")
	private int endingVerseNumber;

	public String getCommentaryId() {
		return commentaryId;
	}

	public void setCommentaryId(String commentaryId) {
		this.commentaryId = commentaryId;
	}

	public List<String> getCommentary() {
		return commentary;
	}

	public void setCommentary(List<String> commentary) {
		this.commentary = commentary;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
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

	public String getVerseId() {
		return verseId;
	}

	public void setVerseId(String verseId) {
		this.verseId = verseId;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public int getStartingVerseNumber() {
		return startingVerseNumber;
	}

	public void setStartingVerseNumber(int startingVerseNumber) {
		this.startingVerseNumber = startingVerseNumber;
	}

	public int getEndingVerseNumber() {
		return endingVerseNumber;
	}

	public void setEndingVerseNumber(int endingVerseNumber) {
		this.endingVerseNumber = endingVerseNumber;
	}

	public String getCommentaryRef() {
		return commentaryRef;
	}

	public void setCommentaryRef(String commentaryRef) {
		this.commentaryRef = commentaryRef;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStartingChapterNumber() {
		return startingChapterNumber;
	}

	public void setStartingChapterNumber(int startingChapterNumber) {
		this.startingChapterNumber = startingChapterNumber;
	}

	public int getEndingChapterNumber() {
		return endingChapterNumber;
	}

	public void setEndingChapterNumber(int endingChapterNumber) {
		this.endingChapterNumber = endingChapterNumber;
	}

	private static final Logger logger = LoggerFactory.getLogger(Commentary.class);

	public void parse(String text) {
		this.text = text;
		int index = text.indexOf(']', 2);

		if (index < 0) {
			return;
		}

		commentaryRef = text.substring(2, index + 1);
		logger.info("commentaryRef: {}", commentaryRef);

		StringTokenizer st = new StringTokenizer(commentaryRef, "[:–-â,]");
		List<Integer> refNumbers = new ArrayList<Integer>();

		while (st.hasMoreElements()) {
			String expectedChapterNo = cleanNumber(st.nextToken());
			if (expectedChapterNo.isEmpty()) {
				expectedChapterNo = st.nextToken();
			}
			refNumbers.add(Integer.valueOf(expectedChapterNo));
		}

		switch (refNumbers.size()) {
		case 2:
			startingChapterNumber = endingChapterNumber = refNumbers.get(0);
			startingVerseNumber = endingVerseNumber = refNumbers.get(1);
			break;
		case 3:
			startingChapterNumber = endingChapterNumber = refNumbers.get(0);
			startingVerseNumber = refNumbers.get(1);
			endingVerseNumber = refNumbers.get(2);
			break;
		case 4:
			startingChapterNumber = refNumbers.get(0);
			startingVerseNumber = refNumbers.get(1);
			endingChapterNumber = refNumbers.get(2);
			endingVerseNumber = refNumbers.get(3);
			break;
		default:
			break;
		}

	}

	private String cleanNumber(String token) {
		StringBuilder sb = new StringBuilder();
		for (int idx = 0; idx < token.length(); idx++) {
			if (Character.isDigit(token.charAt(idx))) {
				sb.append(token.charAt(idx));
			}
		}
		return sb.toString();
	}

}
