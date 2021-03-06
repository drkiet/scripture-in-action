package com.drkiettran.scriptureinaction.model;

import java.util.List;

import com.drkiettran.scriptureinaction.model.constants.NewAmerican;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BibleBook {
	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("other_names")
	private List<String> otherNames;

	public static final String PENTATEUCH = "pentateuch";
	public static final String HISTORICAL = "historical";
	public static final String NOVELLAS = "novellas";
	public static final String WISDOM = "wisdom";
	public static final String PROPHETIC = "prophetic";
	public static final String GOSPELS = "gospels";
	public static final String LETTERS = "letters";
	public static final String CATHOLIC_LETTERS = "catholic_letters";

	/**
	 * - pentateuch - historical - novellas - wisdom - prophetic - gospels - letters
	 * - catholic_letters
	 */
	@JsonProperty("collection_name")
	private String collectionName;

	public static final String NT = "new_testament";
	public static final String OT = "old_testament";
	/**
	 * old_testament new_testament
	 */
	@JsonProperty("type")
	private String type;

	@JsonProperty("number_of_chapters")
	private int numberOfChapters;

	@JsonProperty("introduction")
	private String introduction;

	@JsonProperty("additional_information")
	private String additionalInformation;

	@JsonProperty("chapter_ids")
	private List<String> chapterIds;

	public static final String NAB = "new_american";
	public static final String RSV = "revised_standard";
	public static final String DR = "douay_rheims";
	public static final String LV = "latin_vulgate";
	/**
	 * - new_american - revised_standard - douay_rheims - latin_vulgate
	 * 
	 */
	@JsonProperty("translation")
	private String translation;

	@JsonProperty("chapters")
	private List<Chapter> chapters;

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.type = NewAmerican.getType(name);
		this.collectionName = NewAmerican.getCollectionType(name);
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public List<String> getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(List<String> otherNames) {
		this.otherNames = otherNames;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumberOfChapters() {
		return numberOfChapters;
	}

	public void setNumberOfChapters(int numberOfChapters) {
		this.numberOfChapters = numberOfChapters;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public List<String> getChapterIds() {
		return chapterIds;
	}

	public void setChapterIds(List<String> chapterIds) {
		this.chapterIds = chapterIds;
	}

	public String getVerseText(int chapterNo, int verseNo) {
		return getChapters().get(chapterNo - 1).getVerseText(verseNo);
	}
	
	public String getChapterText(int chapterNo) {
		return getChapters().get(chapterNo - 1).getText();
	}
	
	public String logBookSummary() {
		StringBuilder sb = new StringBuilder("*** Summary for the book of ").append(this.name);
		sb.append(" (").append(translation).append(") ***\n");
		sb.append("bookId: ").append(this.bookId).append('\n');
		sb.append("additionalInformation: ").append(this.additionalInformation).append('\n');
		sb.append("collectionName: ").append(this.collectionName).append('\n');
		sb.append("type: ").append(this.type).append('\n');
		sb.append(" has ").append(chapters.size()).append(" chapters:\n");
		for (int chapterIdx = 0; chapterIdx < chapters.size(); chapterIdx++) {
			sb.append(chapters.get(chapterIdx).logChapterSummary());
		}
		return sb.toString();
	}

	public int[] getNumberOfVersesByChapter() {
		int[] numVerses = new int[chapters.size()];

		for (int chapterIdx = 0; chapterIdx < chapters.size(); chapterIdx++) {
			numVerses[chapterIdx] = chapters.get(chapterIdx).getVerses().size();
		}
		return numVerses;
	}
}
