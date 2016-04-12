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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class UserCallBeta {

    private JSONParser parser = new JSONParser();
    private ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void UserCall(String sender, String channel, String[] args) {
        int c = 0;
        String target = "Invalid";
        for (String ignored : args) {
            c++;
        }
        if (c != 1) {
            Main.api.notice(sender, "Invalid Username!");
        }
        if (c == 1) {
            target = args[0];
        }

        URL paid = null;
        URL migr = null;
        URL uuid = null;
        URL skin = null;
        String JRead;
        String JRead2;
        String JRead3;

        try {
            paid = new URL("https://mcapi.ca/other/haspaid/" + target);
            migr = new URL("https://us.mc-api.net/v3/migrated/" + target);
            uuid = new URL("https://us.mc-api.net/v3/uuid/" + target);
            skin = new URL("https://mcapi.ca/skin/2d/" + target);
        } catch (MalformedURLException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }

        System.out.println("[User][" + target + "] ");

        String PAID = "";
        boolean MIGRATED_C = false;
        String MIGRATED;
        String UUID = "";

        try {
            assert paid != null;
            Scanner s = new Scanner(paid.openStream(), "UTF-8");
            JRead = s.useDelimiter("\\A").next();
            s.close();

            assert migr != null;
            Scanner s2 = new Scanner(migr.openStream(), "UTF-8");
            JRead2 = s2.useDelimiter("\\A").next();
            s2.close();

            assert uuid != null;
            Scanner s3 = new Scanner(uuid.openStream(), "UTF-8");
            JRead3 = s3.useDelimiter("\\A").next();
            s3.close();

            JSONObject payer = (JSONObject) parser.parse(JRead);
            JSONObject mig = (JSONObject) parser.parse(JRead2);
            JSONObject uui = (JSONObject) parser.parse(JRead3);

            PAID = payer.get("premium").toString();
            MIGRATED_C = (boolean) mig.get("migrated");
            UUID = (String) uui.get("full_uuid");

        } catch (ParseException | IOException e) {
            Main.api.notice(sender, exception.PARSE_FAILED());
        }

        // Now Lets Output Checks
        if (Objects.equals(UUID, "")) { UUID = "UUID Not Found";  }
        if (MIGRATED_C) MIGRATED = ChatFormat.GREEN + "Migrated";
        else MIGRATED = ChatFormat.RED + "Migrated";
        if (PAID.equals("true")) PAID = ChatFormat.GREEN + "Paid";
        else PAID = ChatFormat.RED + "Paid";

        String uri;
        if (Objects.equals(UUID, "UUID Not Found")) { uri = "No Skin Found";  }
        else {
            assert skin != null;
            uri = skin.toString(); }

        assert skin != null;
        Main.api.send(channel, ChatFormat.CYAN + "[ " + ChatFormat.DARK_BLUE + target + ChatFormat.YELLOW + " | " + ChatFormat.DARK_BLUE + UUID + ChatFormat.CYAN + " | Skin: " + ChatFormat.DARK_GREEN + uri + ChatFormat.CYAN + " ] [" + PAID + ChatFormat.CYAN + "] [" + MIGRATED + ChatFormat.CYAN + "]");
    }

}
