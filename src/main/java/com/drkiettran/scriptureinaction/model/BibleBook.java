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

	@JsonProperty("collection_name")
	private String collectionName;

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
}
