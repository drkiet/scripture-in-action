package com.drkiettran.scriptureinaction.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Verse {
	@JsonProperty("verse_id")
	private String verseId;

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

	@JsonProperty("commentary")
	private List<Commentary> commentaries;

}
