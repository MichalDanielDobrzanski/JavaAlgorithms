import input.StringData;

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
public class Converter extends BaseAlgorithm<StringData> {
    Converter(StringData stringData) {
        super(stringData);
    }

    @Override
    void execute() {
        String input = "This is a paragraph with a soft\n" +
                "line break.\n" +
                "\n" +
                "This is another paragraph that has\n" +
                "> Some text that\n" +
                "> is in a\n" +
                "> block quote.\n" +
                "\n" +
                "This is another paragraph with a ~~strikethrough~~ word.";
        String output = convert(input);
        System.out.println(output);
    }

// This is a paragraph with a soft
// line break.
//
// This is another paragraph that has
// > Some text that
// > is in a
// > block quote.
// New one
//
// This is another paragraph with a ~~strikethrough~~ word.

// <p>This is a paragraph with a soft<br />line break.</p>
//
// <p>This is another paragraph that has <br />
//  <blockquote>Some text that<br />is in a<br />block quote.</blockquote>
// </p>
//
// <p>This is another paragraph with a <del>strikethrough</del> word.</p>

    private String convert(String input) {
        StringBuilder builder = new StringBuilder();
        String[] paragraphs = input.split("\n\n");
        for (String p : paragraphs) {
            builder.append("<p>");

            boolean blockMode = false;
            boolean strikethroughMode = false;
            for (int i = 0; i < p.length(); i++) {
                char curr = p.charAt(i);
                if (curr == '>') {
                    if (!blockMode) {
                        blockMode = true;
                        builder.append("\n  ").append("<blockquote>");
                    }
                } else if (curr == '~') {
                    if (!strikethroughMode) {
                        strikethroughMode = true;
                        i++;
                        builder.append("<del>");
                    } else {
                        strikethroughMode = false;
                        i++;
                        builder.append("</del>");
                    }
                } else if (curr == '\n') {
                    if (i + 1 < p.length() && p.charAt(i + 1) != '>' && blockMode) {
                        builder.append("</blockquote>").append("\n  ");
                        blockMode = false;
                    } else {
                        builder.append("<br />");
                    }
                } else {
                    builder.append(curr);
                }
            }
            if (blockMode) builder.append("</blockquote>").append('\n');

            builder.append("</p>").append("\n\n");


        }
        return builder.toString();
    }

    @Override
    protected String describe() {
        return "Converting markdown to HTML";
    }
}
