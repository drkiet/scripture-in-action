package com.drkiettran.scriptureinaction.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.drkiettran.scriptureinaction.util.CommonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An example of a verse pointer
 * 
 * <code>
 * 
 * Mt 19:5
 * Mk 10:7
 * 1 Cor 7:10–11
 * Eph 5:31.
 * 
 * 
 * </code>
 * 
 * @author kiet
 *
 */
public class VersePointer {
	private static final Logger logger = LoggerFactory.getLogger(VersePointer.class);

	@JsonProperty("text")
	private String text;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("chapter_number")
	private int chapterNumber;

	@JsonProperty("starting_verse_number")
	private int startingVerseNumber;

	@JsonProperty("ending_verse_number")
	private int endingVerseNumber;

	@JsonProperty("verses")
	private List<Integer> verses;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
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

	public List<Integer> getVerses() {
		return verses;
	}

	public void setVerses(List<Integer> verses) {
		this.verses = verses;
	}

	public void parse(String text) {
		this.text = text;
		StringTokenizer st = new StringTokenizer(text, " -–:.");

		List<String> tokens = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}

		logger.info("{} tokens {}", tokens.size(), tokens);

		int index = 0;
		shortName = "";

		if (Character.isDigit(tokens.get(index).charAt(0)) && tokens.get(index).length() == 1) {
			shortName = tokens.get(index++) + " ";
		}

		shortName += tokens.get(index++);
		bookName = NewAmerican.getBookNameByShortName(shortName);

		chapterNumber = Integer.valueOf(tokens.get(index++)).intValue();

		if (tokens.get(index).contains(",")) {
			st = new StringTokenizer(tokens.get(index), ",");
			verses = new ArrayList<Integer>();
			while (st.hasMoreElements()) {
				verses.add(Integer.valueOf(CommonUtils.cleansingNumberOnly(st.nextToken())));
			}
		} else {
			verses = null;
			startingVerseNumber = Integer.valueOf(CommonUtils.cleansingNumberOnly(tokens.get(index++)));

			if (index < tokens.size()) {
				endingVerseNumber = Integer.valueOf(CommonUtils.cleansingNumberOnly(tokens.get(index++)));
			} else {
				endingVerseNumber = startingVerseNumber;
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(shortName);
		sb.append(' ').append(chapterNumber).append(':').append(startingVerseNumber);
		if (endingVerseNumber != startingVerseNumber) {
			sb.append('-').append(endingVerseNumber);
		}
		return sb.toString();
	}

}
