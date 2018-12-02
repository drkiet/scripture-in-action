package com.drkiettran.scriptureinaction.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Example:
 * 
 * <code>
 * 
 * 2 King chapter 1
 * 
 * a. [1:1] 2 Kgs 3:4–27.
 * b. [1:10] Lv 10:1–2; Sir 48:3; Lk 9:51–55.
 * c. [1:16] Sir 48:6.
 * 
 * </code>
 * 
 * @author ktran
 *
 */
public class Link {
	@JsonProperty("link_id")
	private String linkId;

	@JsonProperty("to_link_ids")
	private List<String> toLinkIds;

	@JsonProperty("verse_id")
	private String verseId;
}
