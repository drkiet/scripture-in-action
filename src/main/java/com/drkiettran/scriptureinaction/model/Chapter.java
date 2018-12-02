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

	@JsonProperty("verses")
	private List<Verse> verses;
}
