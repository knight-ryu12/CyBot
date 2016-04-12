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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHistoryBeta {

    private JSONParser parser = new JSONParser();
    private ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void HistoryCall(String sender, String channel, String[] args) {
        String target = "Invalid";
        if (args.length != 1) {
            Main.api.notice(sender, "Invalid Username!");
        }
        if (args.length == 1) {
            target = args[0];
        }


        URL history;
        URL uuid = null;
        String JRead2;
        String JRead3;
        String UUID = "";

        try {
            uuid = new URL("http://us.mc-api.net/v3/uuid/" + target);
        } catch (MalformedURLException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }

        try {
            assert uuid != null;
            Scanner uuids = new Scanner(uuid.openStream(), "UTF-8");
            JRead3 = uuids.useDelimiter("\\A").next();
            uuids.close();
            JSONObject uui = (JSONObject) parser.parse(JRead3);
            UUID = (String) uui.get("uuid");
        } catch (ParseException | IOException e) {
            Main.api.notice(sender, exception.PARSE_FAILED());
        }

        System.out.println("[HistoryTraceUUID] User< " + target + " > | UUID< " + UUID + " >");

        ArrayList<String> strl = new ArrayList<>();
        String primaryusername = "";
        try {
            history = new URL("http://craftapi.com/api/user/namehistory/" + UUID);

            Scanner historyscan = new Scanner(history.openStream(), "UTF-8");
            JRead2 = historyscan.useDelimiter("\\A").next();
            historyscan.close();
            JSONArray historyjson = (JSONArray) parser.parse(JRead2); // I need to loop through each name in here; https://craftapi.com/api/user/namehistory/e32fb50d95fa452e86a6a6aece15cab6

            for (Object obj: historyjson) {
                JSONObject json = (JSONObject) obj;
                int count = 0;
                if(count == 0) {
                    primaryusername = json.get("name").toString();
                    count++;
                }

                if(count >= 1) {
                    String str = json.get("name").toString();
                    strl.add(str);
                    count++;
                }

            }
        } catch (MalformedURLException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        strl.remove(primaryusername);
        Main.api.send(channel, ChatFormat.CYAN + "User " + ChatFormat.DARK_GREEN + primaryusername + ChatFormat.CYAN + " used to have: " + ChatFormat.TEAL +  strl.toString() + ChatFormat.CYAN + " as their username(s).");

    }

}
