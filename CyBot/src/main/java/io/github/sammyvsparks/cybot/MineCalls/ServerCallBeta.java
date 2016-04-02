/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package io.github.sammyvsparks.cybot.MineCalls;

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerCallBeta {

    JSONParser parser = new JSONParser();
    ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void ServerCheck(String sender, String[] args){
        String ip = "";
        String port=""; int c = 0; int cc = 0;

        for(String arg : args){
            c++;
        }

        if(c != 3){
            ip = args[1];
            port = "25565";
        } if (c == 3){
            ip = args[1];
            port = args[2];
        }
        if(args[1].contains(":")) {
            String[] args_split = args[1].split(":");
            ip = args_split[0];

            for(String arg_split : args_split){
                cc++;
            }
            if(cc != 2){
                ip = args_split[0];
                port = "25565";
            } if(cc == 2) {
                ip = args_split[0];
                port = args_split[1];
            }
        }

        String JRead = null;
        URL ping = null;

        try{
            ping = new URL("https://mcapi.us/server/status?ip=" + ip + "&port=" + port);
            System.out.println("[Ping][" + ip + ":" + port + "] - " + ping.toString());
            Scanner s = new Scanner(ping.openStream(), "UTF-8");
            String json = s.useDelimiter("\\A").next();
            s.close();
            JSONObject obj = (JSONObject) parser.parse(json);
            if( ((Boolean) obj.get("online")) ) {
                String motd = (String) obj.get("motd");
                if(motd == "") motd = "Not Detected!";
                JSONObject players = (JSONObject) obj.get("players");
                JSONObject version = (JSONObject) obj.get("server");
                String vname = (String) version.get("name");
                long vprot = (long) version.get("protocol");
                long currentPlayers = (long) players.get("now");
                long maxPlayers = (long) players.get("max");

                Main.api.notice(sender, ChatFormat.GREEN + ip + ":" + port + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " Online running " + ChatFormat.CYAN + vname + "(" + vprot + ") " + ChatFormat.DARK_BLUE + "with " + ChatFormat.YELLOW + currentPlayers + ChatFormat.DARK_BLUE + " players out of " + ChatFormat.YELLOW + maxPlayers + ChatFormat.DARK_BLUE + " and a " + ChatFormat.MAGENTA + "Message " + ChatFormat.DARK_BLUE + "of: " + ChatFormat.CYAN + motd);

            } else {
                Main.api.notice(sender, ChatFormat.GREEN + ip + ":" + port + ChatFormat.MAGENTA + ":" + ChatFormat.DARK_BLUE + " is Currently Offline or Unreachable.");
            }
        }
        catch (ParseException | IOException except) { System.out.println("Invalid URL"); }
    }

}
