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
 * g. [24:50–51] Tb 7:11–12.
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
	private static final Logger logger = LoggerFactory.getLogger(Link.class);

	@JsonProperty("link_id")
	private String linkId;

	@JsonProperty("book_id")
	private String bookId;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("link_ref")
	private String linkRef;

	@JsonProperty("text")
	private String text;

	@JsonProperty("related_verse_ids")
	private List<String> relatedVerseIds;

	@JsonProperty("related_verses")
	private List<VersePointer> relatedVerses;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("link_pointers")
	private List<VersePointer> linkPointers;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<VersePointer> getLinkPointers() {
		return linkPointers;
	}

	public void setLinkPointers(List<VersePointer> linkPointers) {
		this.linkPointers = linkPointers;
	}

	/**
	 * a. [1:1] Gn 2:1, 4; 2 Mc 7:28; Ps 8:4; 33:6; 89:12; 90:2; Wis 11:17; Sir
	 * 16:24; Jer 10:12; Acts 14:15; Col 1:16-17; Heb 1:2â3; 3:4; 11:3; Rev 4:11.
	 * 
	 * @param text
	 */
	public void parse(String text) {
		shortName = NewAmerican.getShortNameByBookName(bookName);
		text = text.replace("â", "-");
		text = text.replace("/", ";");
		text = text.replace("cf.", "").trim();
		text = text.replace("Cf.", "").trim();
		text = text.replace("par", "").trim();
		text = text.replace("LXX", "").trim();
		if (text.contains("Ex 7-Dt 34")) {
			text = text.replace("Ex 7-Dt 34", "Ex 7;Dt 34"); // not quite but need to get going.
		}
		this.text = text;
		// skip label.
		int beginIndex = text.indexOf(' ') + 1;
		int endIndex = text.indexOf(']', beginIndex);

		// don't copy [ & ].
		linkRef = shortName + " " + text.substring(beginIndex + 1, endIndex);

		logger.info("linkRef: {}", linkRef);
		StringTokenizer st = new StringTokenizer(linkRef, ",");
		List<String> tokens = new ArrayList<String>();
		String lastShortName = "";
		String lastShortNameChapterNumber = "";

		while (st.hasMoreTokens()) {
			String verseRef = st.nextToken().trim();
			logger.info("verseRef: {}", verseRef);

			if (verseRef.indexOf(':') < 0 && verseRef.indexOf(' ') < 0) {
				verseRef = lastShortNameChapterNumber + verseRef;
			}

			String shortName = CommonUtils.extractShortNameFromReference(verseRef);
			logger.info("lastShortName {}, shortName {}, verseRef {}", lastShortName, shortName, verseRef);

			if (shortName.isEmpty()) {
				verseRef = lastShortName + " " + verseRef;
			} else {
				lastShortName = shortName;
			}
			lastShortNameChapterNumber = verseRef.substring(0, verseRef.indexOf(':') + 1);
			tokens.add(verseRef);
		}

		linkPointers = new ArrayList<VersePointer>();
		logger.info("tokens: {}", tokens);

		for (String token : tokens) {
			VersePointer vp = new VersePointer();
			vp.parse(token);
			linkPointers.add(vp);
		}

		st = new StringTokenizer(text.substring(endIndex + 1), ",.;");
		tokens = new ArrayList<String>();

		/**
		 * Verse/link reference usually comes with book short name and then chapter &
		 * verses like this: <code>Ps 1:3</code>. Sometimes it comes after a full
		 * reference with a short reference that means use the book short name of the
		 * previous reference like this: <code>Ps 1:3; 2:4-5; </code>. In this case it
		 * is equivalent to the following: <code>Ps 1:3; Ps 2:4-5;</code> <code>
		 * Ps 1:3; 2:4-5
		 * Ps 1:23; Ps 2:4-5
		 * 
		 * Gn 1:26, 28
		 * Gn 1:26; Gn 1:28 
		 * </code>
		 */
//		lastShortName = "";
		lastShortNameChapterNumber = "";
		while (st.hasMoreTokens()) {
			String verseRef = st.nextToken().trim();
			logger.info("verseRef: {}", verseRef);

			if (verseRef.indexOf(':') < 0 && verseRef.indexOf(' ') < 0) {
				verseRef = lastShortNameChapterNumber + verseRef;
			}

			String shortName = CommonUtils.extractShortNameFromReference(verseRef);
			logger.info("lastShortName {}, shortName {}, verseRef {}", lastShortName, shortName, verseRef);

			if (shortName.isEmpty()) {
				verseRef = lastShortName + " " + verseRef;
			} else {
				lastShortName = shortName;
			}
			lastShortNameChapterNumber = verseRef.substring(0, verseRef.indexOf(':') + 1);
			tokens.add(verseRef);
		}

		relatedVerses = new ArrayList<VersePointer>();
		logger.info("tokens: {}", tokens);

		for (String token : tokens) {
			VersePointer vp = new VersePointer();
			vp.parse(token);
			relatedVerses.add(vp);
		}
	}

	public String logLinkSummary() {
		StringBuilder sb = new StringBuilder("   Reference: ");
		for (VersePointer linkPointer : linkPointers) {
			sb.append(linkPointer.logVersePointerSummary());
		}

		sb.append(" has ").append(relatedVerses.size()).append(" references.\n");
		for (int vpIndex = 0; vpIndex < relatedVerses.size(); vpIndex++) {
			sb.append("    ").append(relatedVerses.get(vpIndex).logVersePointerSummary()).append('\n');
		}
		return sb.toString();
	}

}
