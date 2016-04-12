/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot.Systems;

/**
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class IsUpBeta {

    private JSONParser parser = new JSONParser();
    private ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void IsUpCheck(String sender, String channel, String[] args) {
        String ip = args[0];

        URL isUp;

        try {
            isUp = new URL("https://mcapi.ca/other/isup/" + ip);
            System.out.println("[IsUp][" + ip + "] - " + isUp.toString());
            Scanner s = new Scanner(isUp.openStream(), "UTF-8");
            String json = s.useDelimiter("\\A").next();
            s.close();
            JSONObject obj = (JSONObject) parser.parse(json);
            if (obj.get("status").equals("online")) {

                Main.api.send(channel, ChatFormat.GREEN + ip + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " Online and Accepting Connections.");

            } else {
                Main.api.send(channel, ChatFormat.GREEN + ip + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " Not Reachable or Denying Connections.");
            }
        } catch (ParseException | IOException except) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }
    }

}
