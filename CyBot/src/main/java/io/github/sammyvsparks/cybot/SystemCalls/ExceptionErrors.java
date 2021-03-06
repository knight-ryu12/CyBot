/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot.SystemCalls;

/**
 * All Code in This Section handles errors that sections may throw. Please deprecate any that you add replacements for. DO NOT REMOVE
 * =========================================================================================================================================
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

import io.github.sammyvsparks.cybot.ChatFormat;

public class ExceptionErrors {

    /** @deprecated */
    public String API_DOWN() {
        return ChatFormat.RED + "The API Systems Appear to be Offline or Unreachable. (Failed to Connect or Throttled Connection). Please try again shortly!";
    }

    public String API_OFFLINE() {
        return ChatFormat.RED + "The API System(s) are currently non-responsive or offline! Please try again in a while or contact the Bot Developer!";
    }

    public String PARSE_FAILED() {
        return ChatFormat.RED + "Parsing of the Systems Failed. (Username Not Found/API Error) (If Issue Persists - Please try again shortly or contact the Bot Owner!)";
    }

    public String INVALID_INPUT_CHECK() {
        return ChatFormat.YELLOW + "Invalid Input! Please user \">query user [user]\" or \">query server [ip]:[port]\" or \">query status\".";
    }

    public String PASTE_READ_FAILED() {
        return "Paste Failed -- TODO";
    }

}
