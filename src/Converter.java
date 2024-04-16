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
//
// This is another paragraph with a ~~strikethrough~~ word.

// <p>This is a paragraph with a soft<br />line break.</p>
//
// <p>This is another paragraph that has <br />
//  <blockquote>Some text that<br />is in a<br />block quote.</blockquote>
// </p>
//
// <p>This is another paragraph with a <del>strikethrough</del> word.</p>


    // String input = "This is a paragraph with a soft\n" + "line break.\n\n" + "This is another paragraph that has\n" +
    //        "> Some text that\n" + "> is in a\n" + "> block quote.\n\n" +
    //        "This is another paragraph with a ~~strikethrough~~ word.";

    // String input = "This is a paragraph with a soft\nline break.\n\nThis is another paragraph that has\n> Some text that\n> is in a\n> block quote.\n\nThis is another paragraph with a ~~strikethrough~~ word.";
    private String convert(String input) {
        // 2. paragraphs -> end (\n\n), if there is something after then add start
        // 1. soft breaks -> single \n
        // 4. strikethrough -> start with ~~, end with next ~~
        // 3. blockquotes -> start \n>_ (_ is space), end if not met on the next line

        if (input.isEmpty()) return "";

        StringBuilder builder = new StringBuilder();
        builder.append("<p>");

        boolean endOfParagraph = false;
        boolean isStrikeThrough = false;
        boolean isBlockquote = false;
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == '\n') {
                char next = i + 1 < input.length() ? input.charAt(i + 1) : ' ';
                // paragraphs
                if (next == '\n') {
                    if (isBlockquote) {
                        builder.append("</blockquote>").append("\n");
                        isBlockquote = false;
                    }
                    builder.append("</p>").append("\n\n");
                    endOfParagraph = true;
                    i++;
                } else {
                    builder.append("<br />");
                    if (next == '>') {
                        char nextNext = i + 2 < input.length() ? input.charAt(i + 2) : '_';
                        if (nextNext == ' ') {
                            // blockquotes
                            if (!isBlockquote) {
                                builder.append("\n").append("<blockquote>");
                                isBlockquote = true;
                            }
                            i += 2;
                        }
                    }
                }
            } else if (curr == '~') {
                char next = i + 1 < input.length() ? input.charAt(i + 1) : ' ';
                if (next == '~') {
                    if (!isStrikeThrough) {
                        builder.append("<del>");
                        isStrikeThrough = true;
                    } else {
                        builder.append("</del>");
                        isStrikeThrough = false;
                    }
                    i++;
                } else {
                    builder.append(curr);
                }
            } else {
                if (endOfParagraph) {
                    builder.append("<p>");
                    endOfParagraph = false;
                }
                builder.append(curr);
            }
        }

        builder.append("</p>");
        return builder.toString();
    }

    @Override
    protected String describe() {
        return "Converting markdown to HTML";
    }
}
