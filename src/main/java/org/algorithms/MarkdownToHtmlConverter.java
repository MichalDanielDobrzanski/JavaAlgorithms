package org.algorithms;

import org.algorithms.input.StringData;

import java.util.ArrayList;
import java.util.List;

// This is a paragraph with a soft
// line break.
//
// This is another paragraph that has
// > Some text that
// > is in a
// > block quote.
//
// This is another paragraph with a ~~strikethrough~~ word.

// <p>This is a paragraph with a soft<br />line break.</p>
//
// <p>This is another paragraph that has <br />
//  <blockquote>Some text that<br />is in a<br />block quote.</blockquote>
// </p>
//
// <p>This is another paragraph with a <del>strikethrough</del> word.</p>
public class MarkdownToHtmlConverter extends BaseAlgorithm<StringData> {
    public MarkdownToHtmlConverter(StringData stringData) {
        super(stringData);
    }

    @Override
    public void execute() {
        String input = "This is a paragraph with a soft\n" +
                "line break.\n" +
                "\n" +
                "This is another paragraph that has\n" +
                "> Some text that\n" +
                "> is in a\n" +
                "> block quote.\n" +
                "\n" +
                "This is another paragraph with a ~~outer ~~inner~~ outer~~ word.";
        String output = processMarkdown(input);
        System.out.println(output);
    }

    //     * Input:
//     *  This is a paragraph with a soft
//     *  line break.
//     *
//     *  This is another paragraph that has
//     *  > Some text that
//     *  > is in a
//     *  > block quote.
//     *
//     *  This is another paragraph with a ~~strikethrough~~ word.
//     *
//     * Output:
//     *  <p>This is a paragraph with a soft<br />line break.</p>
//     *
//     *  <p>This is another paragraph that has <br />
//     *   <blockquote>Some text that<br />is in a<br />block quote.</blockquote>
//     *  </p>
//     *
//     *  <p>This is another paragraph with a <del>strikethrough</del> word.</p>
    private String processMarkdown(String input) {
        if (input == null || input.isEmpty()) return "";

        String[] paragraphs = input.split("\n\n");
        List<String> processed = new ArrayList<>();

        for (String p : paragraphs) {
            processed.add(processParagraph(p));
        }

        return String.join("\n\n", processed);
    }

    private String processParagraph(String paragraph) {
        paragraph = processLineBreaks(paragraph);
        paragraph = processBlockQuotes(paragraph);
        paragraph = processStrikethrough(paragraph);
        return wrapInParagraph(paragraph);
    }

    private String processStrikethrough(String paragraph) {
        // this will not handle cases like: ~~outer ~~inner~~ outer~~
        return paragraph.replaceAll("~~(.*?)~~", "<del>$1</del>");
    }

    private String processBlockQuotes(String paragraph) {
        StringBuilder result = new StringBuilder();
        String[] lines = paragraph.split("<br />");

        boolean isBlockquote = false;
        for (String line : lines) {
            if (line.startsWith("> ")) {
                if (!isBlockquote) {
                    isBlockquote = true;
                    result.append("\n  <blockquote>");
                }
                result.append(line.substring(2)).append("<br />");
            } else {
                if (isBlockquote) {
                    result.append("</blockquote>");
                    isBlockquote = false;
                }
                result.append(line).append("<br />");
            }
        }
        result.setLength(result.length() - 6);

        if (isBlockquote) {
            result.append("</blockquote>");
        }

        return result.toString();
    }

    private String processLineBreaks(String paragraph) {
        return paragraph.replace("\n", "<br />");

    }

    private String wrapInParagraph(String paragraph) {
        if (paragraph.contains("blockquote")) {
            return "<p>" + paragraph.trim() + "\n</p>";
        }
        return "<p>" + paragraph.trim() + "</p>";
    }

    @Override
    protected String describe() {
        return "Converting markdown to HTML";
    }
}
