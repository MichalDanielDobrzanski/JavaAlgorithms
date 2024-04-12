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


    // String input = "This is a paragraph with a soft\n" + "line break.\n\n" + "This is another paragraph that has\n" +
    //        "> Some text that\n" + "> is in a\n" + "> block quote.\n\n" +
    //        "This is another paragraph with a ~~strikethrough~~ word.";

    // String input = "This is a paragraph with a soft\nline break.\n\nThis is another paragraph that has\n> Some text that\n> is in a\n> block quote.\n\nThis is another paragraph with a ~~strikethrough~~ word.";
    private String convert(String input) {
        if (input.isBlank()) return "";

        StringBuilder builder = new StringBuilder("<p>");

        boolean paragraphMode = true;
        boolean blockquoteMode = false;
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);

            if (!paragraphMode) {
                builder.append("<p>");
                paragraphMode = true;
            }

            if (curr == '\n') {
                char next = i + 1 < input.length() ? input.charAt(i + 1) : ' ';

                if (next != '\n') {
                    builder.append("<br />");
                }

                if (next == '\n') {
                    if (blockquoteMode) {
                        builder.append("</blockquote>").append('\n');
                        blockquoteMode = false;
                    }
                    builder.append("</p>").append("\n\n");
                    paragraphMode = false;
                    i++;
                } else if (next == '>') {
                    int nextAfter = i + 2 < input.length() ? input.charAt(i + 2) : '0';
                    if (nextAfter == ' ') {
                        if (!blockquoteMode) {
                            builder.append('\n').append("<blockquote>");
                            blockquoteMode = true;
                        }
                        i += 2;
                    }
                }
            } else {
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
