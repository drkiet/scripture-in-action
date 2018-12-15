package com.drkiettran.scriptureinaction.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BibleBook {
	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("other_names")
	private List<String> otherNames;

	/**
	 * - pentateuch - historical - novellas - wisdom - prophetic - gospels - letters
	 * - catholic_letters
	 */
	@JsonProperty("collection_name")
	private String collectionName;

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

	public String logBookSummary() {
		StringBuilder sb = new StringBuilder("*** Summary for the book of ").append(this.name);
		sb.append(" (").append(translation).append(") ***\n");
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
