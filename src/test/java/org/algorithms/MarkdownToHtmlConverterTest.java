package org.algorithms;

import org.algorithms.input.StringData;
import org.junit.jupiter.api.Test;

class MarkdownToHtmlConverterTest {

    private MarkdownToHtmlConverter conv;

    // EXPECTED INPUT:
// This is a paragraph with a soft
// line break.
//
// This is another paragraph that has
// > Some text that
// > is in a
// > block quote.
//
// This is another paragraph with a ~~strikethrough~~ word.

    // EXPECTED OUTPUT:
// <p>This is a paragraph with a soft<br />line break.</p>
//
// <p>This is another paragraph that has <br />
//  <blockquote>Some text that<br />is in a<br />block quote.</blockquote>
// </p>
//
// <p>This is another paragraph with a <del>strikethrough</del> word.</p>

    @Test
    void testStandardInput() {
        // given
        String input = "This is a paragraph with a soft\n" +
                "line break.\n" +
                "\n" +
                "This is another paragraph that has\n" +
                "> Some text that\n" +
                "> is in a\n" +
                "> block quote.\n" +
                "\n" +
                "This is another paragraph with a ~~strikethrough~~ word.";
        conv = create(input);

        // when
        conv.execute();
    }

    @Test
    void testNonStandardInput() {
        // given
        String input = "This is a paragraph with a soft\n" +
                "line break.\n" +
                "\n" +
                "This is another paragraph that has\n" +
                "> Some text that\n" +
                "> is in a\n" +
                "> block quote.\n" +
                "\n" +
                "This is another paragraph with a ~~outer ~~inner~~ outer~~ word.";
        conv = create(input);

        // when
        conv.execute();
    }

    @Test
    void testBlockQuote() {
        // given
        String input = """
                Text
                > A
                > B
                Tail
                """;
        conv = create(input);

        // when
        conv.execute();
    }

    @Test
    void testEmpty() {
        // given
        String input = "";
        conv = create(input);

        // when
        conv.execute();
    }

    @Test
    void testParWithSoftBreak() {
        // given
        String input = """
                This is a paragraph with a soft
                line break.
                """;
        conv = create(input);

        // when
        conv.execute();
    }

    private MarkdownToHtmlConverter create(String input) {
        return new MarkdownToHtmlConverter(new StringData(input));
    }
}