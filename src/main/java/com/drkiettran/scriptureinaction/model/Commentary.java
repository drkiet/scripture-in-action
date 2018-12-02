package com.drkiettran.scriptureinaction.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Commentary {
	@JsonProperty("commentary_id")
	private String commentaryId;

	@JsonProperty("commentary")
	private List<String> commentary;

	@JsonProperty("authors")
	private List<String> authors;

	@JsonProperty("verse_id")
	private String verseId;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date pubDate;
}
