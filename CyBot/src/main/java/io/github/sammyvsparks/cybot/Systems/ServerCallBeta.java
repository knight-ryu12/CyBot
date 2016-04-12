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
import java.util.Objects;
import java.util.Scanner;

public class ServerCallBeta {

    private JSONParser parser = new JSONParser();
    ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void ServerCheck(String sender, String channel, String[] args) {
        String ip = "";
        String port = "";
        int c = 0;
        int cc = 0;

        for (String ignored : args) {
            c++;
        }

        if (c != 2) {
            ip = args[0];
            port = "25565";
        }
        if (c == 2) {
            ip = args[0];
            port = args[1];
        }
        if (args[0].contains(":")) {
            String[] args_split = args[0].split(":");
            ip = args_split[0];

            for (String ignored : args_split) {
                cc++;
            }
            if (cc != 2) {
                ip = args_split[0];
                port = "25565";
            }
            if (cc == 2) {
                ip = args_split[0];
                port = args_split[1];
            }
        }

        URL ping;
        URL latency;

        try {
            ping = new URL("https://mcapi.us/server/status?ip=" + ip + "&port=" + port);
            latency = new URL("https://mcapi.ca/query/" + ip + ":" + port + "/info");
            System.out.println("[Ping][" + ip + ":" + port + "] - " + ping.toString());
            Scanner s = new Scanner(ping.openStream(), "UTF-8");
            Scanner l = new Scanner(latency.openStream(), "UTF-8");
            String json = s.useDelimiter("\\A").next();
            String json2 = l.useDelimiter("\\A").next();
            s.close();
            l.close();
            JSONObject obj = (JSONObject) parser.parse(json);
            JSONObject obj2 = (JSONObject) parser.parse(json2);
            if (((Boolean) obj.get("online"))) {
                String motd = (String) obj.get("motd");
                if (Objects.equals(motd, "")) motd = "Not Detected!";
                JSONObject players = (JSONObject) obj.get("players");
                JSONObject version = (JSONObject) obj.get("server");
                String vname = (String) version.get("name");
                long vprot = (long) version.get("protocol");
                long currentPlayers = (long) players.get("now");
                long maxPlayers = (long) players.get("max");
                String latensy = (String) obj2.get("ping");

                Main.api.send(channel, ChatFormat.GREEN + ip + ":" + port + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " Online running " + ChatFormat.CYAN + vname + "(" + vprot + ") " + ChatFormat.DARK_BLUE + "with " + ChatFormat.YELLOW + currentPlayers + ChatFormat.DARK_BLUE + " players out of " + ChatFormat.YELLOW + maxPlayers + ChatFormat.DARK_BLUE + " with a latency of " + ChatFormat.LIGHT_GRAY + latensy + "ms" + ChatFormat.DARK_BLUE + " and a " + ChatFormat.MAGENTA + "Message " + ChatFormat.DARK_BLUE + "of: " + ChatFormat.CYAN + motd);

            } else {
                Main.api.send(channel, ChatFormat.GREEN + ip + ":" + port + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " is Currently Offline or Unreachable.");
            }
        } catch (ParseException | IOException except) {
            System.out.println("Invalid URL");
        }
    }

}
