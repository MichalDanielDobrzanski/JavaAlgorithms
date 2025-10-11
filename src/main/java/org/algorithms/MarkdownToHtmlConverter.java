package org.algorithms;

import org.algorithms.input.StringData;

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
        String output = processMarkdown(input.data);
        System.out.println(output);
    }

    private String processMarkdown(String input) {
        if (input == null || input.isEmpty()) return "";

        /*
             “Next, I’ll split paragraphs on one-or-more blank lines. I’ll use \\n\\n+ so multiple blank lines still separate one paragraph.”
         */
        String[] paragraphs = input.trim().split("\\n\\n+");
        StringBuilder html = new StringBuilder();

        for (int i = 0; i < paragraphs.length; i++) {
            /*
                “I’ll join results with double newlines to keep output readable.”
             */
            if (i > 0) html.append("\n\n");
            html.append(processParagraph(paragraphs[i]));
        }

        return html.toString();
    }

    /**
     * “I’ll implement a small markdown→HTML subset: paragraphs split by blank lines,
     * soft line breaks (\n → <br />),
     * block quotes for lines starting with >, and strikethrough using ~~...~~.”
     * <p>
     * “I’ll keep it simple: split → process paragraph line-by-line → wrap with <p>.”
     * <p>
     * “No heavy regex; just clean string ops so it’s easy to reason about.”
     */
    private String processParagraph(String paragraph) {
        // 1️⃣ Handle block quotes first
        paragraph = handleBlockQuotes(paragraph);

        // 2️⃣ Handle line breaks
        /*
        “Now I convert single newlines to <br />. This is applied after quotes so I don’t have to micromanage breaks during the quote pass.”
         */
        paragraph = paragraph.replace("\n", "<br />");

        // 3️⃣ Handle strikethrough
        paragraph = handleStrikethrough(paragraph);

        // 4️⃣ Wrap paragraph
        return "<p>" + paragraph.trim() + "</p>";
    }

    /**
     * “I’ll split the paragraph into physical lines.”
     * “I’ll walk each line; if trim().startsWith('>'), I open a <blockquote> the first time and keep appending lines inside it.”
     * “When I hit a non-quote line after quotes, I close the <blockquote>.”
     * “At the end, if I’m still inside a quote, I close it.”
     * “I’ll strip the leading > and any single space afterward so content looks clean.”
     */
    private String handleBlockQuotes(String text) {
        String[] lines = text.split("\n");
        StringBuilder result = new StringBuilder();
        boolean insideQuote = false;

        for (String line : lines) {
            if (line.trim().startsWith(">")) {
                if (!insideQuote) {
                    insideQuote = true;
                    result.append("<blockquote>");
                }
                // remove "> " or ">" from start
                String cleaned = line.trim().substring(1).trim();
                result.append(cleaned).append("\n");
            } else {
                if (insideQuote) {
                    result.append("</blockquote>");
                    insideQuote = false;
                }
                result.append(line).append("\n");
            }
        }

        if (insideQuote) result.append("</blockquote>");

        return result.toString().trim();
    }

    /**
     * “I’ll implement a toggle parser: every ~~ flips <del> on or off.”
     * “If the paragraph ends with an open <del>, I auto-close to keep valid HTML.”
     */
    private String handleStrikethrough(String text) {
        // simple toggle parser: every pair of "~~" opens/closes <del>
        StringBuilder sb = new StringBuilder();
        boolean open = false;

        for (int i = 0; i < text.length(); i++) {
            if (i + 1 < text.length() && text.charAt(i) == '~' && text.charAt(i + 1) == '~') {
                sb.append(open ? "</del>" : "<del>");
                open = !open;
                i++; // skip second ~
            } else {
                sb.append(text.charAt(i));
            }
        }

        if (open) sb.append("</del>");
        return sb.toString();
    }

    @Override
    protected String describe() {
        return "Converting markdown to HTML";
    }
}
