/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum ChatFormat {

    NORMAL("\u000f", Style.RESET),
    BOLD("\u0002", Style.FORMAT),
    UNDERLINE("\u001f", Style.FORMAT),
    REVERSE("\u0016", Style.FORMAT),
    WHITE("\u000300", Style.COLOR),
    BLACK("\u000301", Style.COLOR),
    DARK_BLUE("\u000302", Style.COLOR),
    DARK_GREEN("\u000303", Style.COLOR),
    RED("\u000304", Style.COLOR),
    BROWN("\u000305", Style.COLOR),
    PURPLE("\u000306", Style.COLOR),
    OLIVE("\u000307", Style.COLOR),
    YELLOW("\u000308", Style.COLOR),
    GREEN("\u000309", Style.COLOR),
    TEAL("\u000310", Style.COLOR),
    CYAN("\u000311", Style.COLOR),
    BLUE("\u000312", Style.COLOR),
    MAGENTA("\u000313", Style.COLOR),
    DARK_GRAY("\u000314", Style.COLOR),
    LIGHT_GRAY("\u000315", Style.COLOR);

    private final static Map<ChatFormat, Pattern> chatFormatPatterns = new EnumMap<>(ChatFormat.class);

    static {
        chatFormatPatterns.put(BOLD, Pattern.compile("/\\[\\/?b\\]/ig"));
        chatFormatPatterns.put(UNDERLINE, Pattern.compile("/\\[\\/?u\\]/ig"));
        for (ChatFormat format : values()) {
            chatFormatPatterns.put(format, Pattern.compile("/\\[\\/?" + format.name().toLowerCase().replace("_", "") + "\\]/ig"));
        }
    }

    private final String code;
    private final Style style;

    ChatFormat(String c, Style s) {
        code = c;
        style = s;
    }

    public static String removeColors(String line) {
        for (ChatFormat color : ChatFormat.values()) {
            if (color.style == Style.COLOR) {
                line = line.replace(color.toString(), "");
            }
        }
        return line;
    }

    public static String removeFormatting(String line) {
        for (ChatFormat color : ChatFormat.values()) {
            if (color.style == Style.FORMAT) {
                line = line.replace(color.toString(), "");
            }
        }
        return line;
    }

    public static String removeFormattingAndColors(String line) {
        return removeFormatting(removeColors(line));
    }

    public static String fromMarkdown(String line) {
        for (Map.Entry<ChatFormat, Pattern> patternEntry : chatFormatPatterns.entrySet()) {
            line = patternEntry.getValue().matcher(line).replaceAll(patternEntry.getKey().getCode());
        }
        return line;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public enum Style {

        FORMAT, COLOR, RESET
    }
}