package org.algorithms;

// Java translation of the Prolog DSL from "Girls and Boys" by Blur
// Note: Java does not support logic programming natively, so we mimic the structure.
// The following models the DSL tree and produces sentences.

import java.util.ArrayList;
import java.util.List;

public class LyricsGenerator {

    enum Gender {
        MALE("boys"),
        FEMALE("girls");

        final String word;

        Gender(String word) {
            this.word = word;
        }

        @Override
        public String toString() {
            return word;
        }
    }

    static class Group {
        final Gender gender;
        final Gender isGender;          // optional
        final Gender performGender;     // optional
        final Group likeGroup;          // optional

        Group(Gender gender, Gender isGender, Gender performGender, Group likeGroup) {
            this.gender = gender;
            this.isGender = isGender;
            this.performGender = performGender;
            this.likeGroup = likeGroup;
        }

        public List<String> toSentenceTokens() {
            List<String> tokens = new ArrayList<>();
            tokens.add(gender.toString());
            if (isGender != null || performGender != null) {
                tokens.add("(");
                tokens.add("who");
                if (isGender != null) {
                    tokens.add("are");
                    tokens.add(isGender.toString());
                    if (performGender != null) tokens.add("and");
                }
                if (performGender != null) {
                    tokens.add("get");
                    tokens.add("done");
                    tokens.add("like");
                    tokens.add("they're");
                    tokens.add(performGender.toString());
                }
                tokens.add(")");
            }
            if (likeGroup != null) {
                tokens.add("who");
                tokens.add("like");
                tokens.addAll(likeGroup.toSentenceTokens());
            }
            return tokens;
        }

        public String toSentence() {
            return String.join(" ", toSentenceTokens());
        }

        public int depth() {
            return likeGroup == null ? 0 : 1 + likeGroup.depth();
        }
    }

    public static void main(String[] args) {
        Group tree = new Group(
                Gender.FEMALE, null, null,
                new Group(
                        Gender.MALE, Gender.FEMALE, null,
                        new Group(
                                Gender.MALE, null, Gender.FEMALE,
                                new Group(
                                        Gender.FEMALE, null, Gender.MALE, null)
                        )
                )
        );

        System.out.println("Sentence: " + tree.toSentence());
        System.out.println("Depth: " + tree.depth());
    }
}
