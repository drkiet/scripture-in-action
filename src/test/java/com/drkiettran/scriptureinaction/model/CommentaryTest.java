package com.drkiettran.scriptureinaction.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

/**
 * <code>
 * 
 * * [1:1–2:3] This section, from the Priestly source, functions as an introduction, as ancient stories of the origin of the world (cosmogonies) often did. It introduces the primordial story (2:4–11:26), the stories of the ancestors (11:27–50:26), and indeed the whole Pentateuch. The chapter highlights the goodness of creation and the divine desire that human beings share in that goodness. God brings an orderly universe out of primordial chaos merely by uttering a word. In the literary structure of six days, the creation events in the first three days are related to those in the second three.
 * Until modern times the first line was always translated, “In the beginning God created the heavens and the earth.” Several comparable ancient cosmogonies, discovered in recent times, have a “when…then” construction, confirming the translation “when…then” here as well. “When” introduces the pre-creation state and “then” introduces the creative act affecting that state. The traditional translation, “In the beginning,” does not reflect the Hebrew syntax of the clause.
 * * [1:2] This verse is parenthetical, describing in three phases the pre-creation state symbolized by the chaos out of which God brings order: “earth,” hidden beneath the encompassing cosmic waters, could not be seen, and thus had no “form”; there was only darkness; turbulent wind swept over the waters. Commencing with the last-named elements (darkness and water), vv. 3–10 describe the rearrangement of this chaos: light is made (first day) and the water is divided into water above and water below the earth so that the earth appears and is no longer “without outline.” The abyss: the primordial ocean according to the ancient Semitic cosmogony. After God’s creative activity, part of this vast body forms the salt-water seas (vv. 9–10); part of it is the fresh water under the earth (Ps 33:7; Ez 31:4), which wells forth on the earth as springs and fountains (Gn 7:11; 8:2; Prv 3:20). Part of it, “the upper water” (Ps 148:4; Dn 3:60), is held up by the dome of the sky (vv. 6–7), from which rain descends on the earth (Gn 7:11; 2 Kgs 7:2, 19; Ps 104:13). A mighty wind: literally, “spirit or breath [ruah] of God”; cf. Gn 8:1.
 * * [6:5–8:22] The story of the great flood is commonly regarded as a composite narrative based on separate sources woven together. To the Yahwist source, with some later editorial additions, are usually assigned 6:5–8; 7:1–5, 7–10, 12, 16b, 17b, 22–23; 8:2b–3a, 6–12, 13b, 20–22. The other sections are usually attributed to the Priestly writer. There are differences between the two sources: the Priestly source has two pairs of every animal, whereas the Yahwist source has seven pairs of clean animals and two pairs of unclean; the floodwater in the Priestly source is the waters under and over the earth that burst forth, whereas in the Yahwist source the floodwater is the rain lasting forty days and nights. In spite of many obvious discrepancies in these two sources, one should read the story as a coherent narrative. The biblical story ultimately draws upon an ancient Mesopotamian tradition of a great flood, preserved in the Sumerian flood story, the eleventh tablet of the Gilgamesh Epic, and (embedded in a longer creation story) the Atrahasis Epic.
 * * [6:19–21] You shall bring two of every kind…, one male and one female: For the Priestly source (P), there is no distinction between clean and unclean animals until Sinai (Lv 11), no altars or sacrifice until Sinai, and all diet is vegetarian (Gn 1:29–30); even after the flood P has no distinction between clean and unclean, since “any living creature that moves about” may be eaten (9:3). Thus P has Noah take the minimum to preserve all species, one pair of each, without distinction between clean and unclean, but he must also take on provisions for food (6:21). The Yahwist source (J), which assumes the clean-unclean distinction always existed but knows no other restriction on eating meat (Abel was a shepherd and offered meat as a sacrifice), requires additional clean animals (“seven pairs”) for food and sacrifice (7:2–3; 8:20).

 * </code>
 * 
 * @author kiet
 *
 */
public class CommentaryTest {
	@Test
	public void shouldParseCommentary() {
		String text = "* [1:1–2:3] This section, from the Priestly source, functions as an introduction, as ancient stories of the origin of the world (cosmogonies) often did. It introduces the primordial story (2:4–11:26), the stories of the ancestors (11:27–50:26), and indeed the whole Pentateuch. The chapter highlights the goodness of creation and the divine desire that human beings share in that goodness. God brings an orderly universe out of primordial chaos merely by uttering a word. In the literary structure of six days, the creation events in the first three days are related to those in the second three.";
		Commentary commentary = new Commentary();
		commentary.parse(text);

		assertThat(commentary.getStartingChapterNumber(), equalTo(1));
		assertThat(commentary.getEndingChapterNumber(), equalTo(2));
		assertThat(commentary.getStartingVerseNumber(), equalTo(1));
		assertThat(commentary.getEndingVerseNumber(), equalTo(3));

		text = "* [1:2] This verse is parenthetical, describing in three phases the pre-creation state symbolized by the chaos out of which God brings order: “earth,” hidden beneath the encompassing cosmic waters, could not be seen, and thus had no “form”; there was only darkness; turbulent wind swept over the waters. Commencing with the last-named elements (darkness and water), vv. 3–10 describe the rearrangement of this chaos: light is made (first day) and the water is divided into water above and water below the earth so that the earth appears and is no longer “without outline.” The abyss: the primordial ocean according to the ancient Semitic cosmogony. After God’s creative activity, part of this vast body forms the salt-water seas (vv. 9–10); part of it is the fresh water under the earth (Ps 33:7; Ez 31:4), which wells forth on the earth as springs and fountains (Gn 7:11; 8:2; Prv 3:20). Part of it, “the upper water” (Ps 148:4; Dn 3:60), is held up by the dome of the sky (vv. 6–7), from which rain descends on the earth (Gn 7:11; 2 Kgs 7:2, 19; Ps 104:13). A mighty wind: literally, “spirit or breath [ruah] of God”; cf. Gn 8:1.";
		commentary = new Commentary();
		commentary.parse(text);

		assertThat(commentary.getStartingChapterNumber(), equalTo(1));
		assertThat(commentary.getEndingChapterNumber(), equalTo(1));
		assertThat(commentary.getStartingVerseNumber(), equalTo(2));
		assertThat(commentary.getEndingVerseNumber(), equalTo(2));

		text = "* [6:19–21] You shall bring two of every kind…, one male and one female: For the Priestly source (P), there is no distinction between clean and unclean animals until Sinai (Lv 11), no altars or sacrifice until Sinai, and all diet is vegetarian (Gn 1:29–30); even after the flood P has no distinction between clean and unclean, since “any living creature that moves about” may be eaten (9:3). Thus P has Noah take the minimum to preserve all species, one pair of each, without distinction between clean and unclean, but he must also take on provisions for food (6:21). The Yahwist source (J), which assumes the clean-unclean distinction always existed but knows no other restriction on eating meat (Abel was a shepherd and offered meat as a sacrifice), requires additional clean animals (“seven pairs”) for food and sacrifice (7:2–3; 8:20).";
		commentary = new Commentary();
		commentary.parse(text);

		assertThat(commentary.getStartingChapterNumber(), equalTo(6));
		assertThat(commentary.getEndingChapterNumber(), equalTo(6));
		assertThat(commentary.getStartingVerseNumber(), equalTo(19));
		assertThat(commentary.getEndingVerseNumber(), equalTo(21));
	}
}
