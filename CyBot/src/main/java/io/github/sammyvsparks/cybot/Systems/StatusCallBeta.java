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
 * Developer of Personal Code | Cykrix (Help from Urielsalis for JSON)--^
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
import java.net.URL;
import java.util.Scanner;

public class StatusCallBeta {

    private JSONParser parser = new JSONParser();
    private ExceptionErrors exception = new ExceptionErrors();

    public void StatusCall(String channel, String sender) {
        URL Status;

        try {
            Status = new URL("https://status.mojang.com/check");
            System.out.println("[Ping][StatusCheck] " + Status.toString());
            Scanner JReader = new Scanner(Status.openStream(), "UTF-8");
            String JRead = JReader.useDelimiter("\\A").next();
            JReader.close();

            JSONArray read = (JSONArray) parser.parse(JRead);

            JSONObject minecraftnet = (JSONObject) read.get(0);
            JSONObject minecraftsession = (JSONObject) read.get(1);
            JSONObject accountmojang = (JSONObject) read.get(2);
            JSONObject authmojang = (JSONObject) read.get(3);
            JSONObject skinsminecraft = (JSONObject) read.get(4);
            JSONObject authservermojang = (JSONObject) read.get(5);
            JSONObject sessionminecraft = (JSONObject) read.get(6);
            JSONObject texturesminecraft = (JSONObject) read.get(8);
            JSONObject mojangcom = (JSONObject) read.get(9);
            JSONObject mojangapi = (JSONObject) read.get(7);

            String minecraftnet_s = (String) minecraftnet.get("minecraft.net");
            String minecraftsession_s = (String) minecraftsession.get("session.minecraft.net");
            String accountmojang_s = (String) accountmojang.get("account.mojang.com");
            String authmojang_s = (String) authmojang.get("auth.mojang.com");
            String skinsminecraft_s = (String) skinsminecraft.get("skins.minecraft.net");
            String authservermojang_s = (String) authservermojang.get("authserver.mojang.com");
            String sessionminecraft_s = (String) sessionminecraft.get("sessionserver.mojang.com");
            String texturesminecraft_s = (String) texturesminecraft.get("textures.minecraft.net");
            String mojangcom_s = (String) mojangcom.get("mojang.com");
            String mojangapi_s = (String) mojangapi.get("api.mojang.com");

            // Parse
            switch (minecraftnet_s) {
                case "green":
                    minecraftnet_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    minecraftnet_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    minecraftnet_s = ChatFormat.RED + "";
                    break;
            }

            switch (minecraftsession_s) {
                case "green":
                    minecraftsession_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    minecraftsession_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    minecraftsession_s = ChatFormat.RED + "";
                    break;
            }

            switch (accountmojang_s) {
                case "green":
                    accountmojang_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    accountmojang_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    accountmojang_s = ChatFormat.RED + "";
                    break;
            }

            switch (skinsminecraft_s) {
                case "green":
                    skinsminecraft_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    skinsminecraft_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    skinsminecraft_s = ChatFormat.RED + "";
                    break;
            }

            switch (authmojang_s) {
                case "green":
                    authmojang_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    authmojang_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    authmojang_s = ChatFormat.RED + "";
                    break;
            }

            switch (authservermojang_s) {
                case "green":
                    authservermojang_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    authservermojang_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    authservermojang_s = ChatFormat.RED + "";
                    break;
            }

            switch (sessionminecraft_s) {
                case "green":
                    sessionminecraft_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    sessionminecraft_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    sessionminecraft_s = ChatFormat.RED + "";
                    break;
            }

            switch (texturesminecraft_s) {
                case "green":
                    texturesminecraft_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    texturesminecraft_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    texturesminecraft_s = ChatFormat.RED + "";
                    break;
            }

            switch (mojangcom_s) {
                case "green":
                    mojangcom_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    mojangcom_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    mojangcom_s = ChatFormat.RED + "";
                    break;
            }

            switch (mojangapi_s) {
                case "green":
                    mojangapi_s = ChatFormat.DARK_GREEN + "";
                    break;
                case "yellow":
                    mojangapi_s = ChatFormat.YELLOW + "";
                    break;
                default:
                    mojangapi_s = ChatFormat.RED + "";
                    break;
            }

            Main.api.send(channel, ChatFormat.DARK_BLUE + "Minecraft Status: " + ChatFormat.CYAN + "[" + minecraftnet_s + "Website" + ChatFormat.CYAN + "][" + minecraftsession_s + "Sessions" + ChatFormat.CYAN + "][" + skinsminecraft_s + "Skins" + ChatFormat.CYAN + "][" + texturesminecraft_s + "Textures" + ChatFormat.CYAN + "]");
            Main.api.send(channel, ChatFormat.DARK_BLUE + "Mojang Status: " + ChatFormat.CYAN + "[" + mojangcom_s + "Website" + ChatFormat.CYAN + "][" + accountmojang_s + "Account" + ChatFormat.CYAN + "][" + authmojang_s + "Auth" + ChatFormat.CYAN + "][" + authservermojang_s + "AuthServer" + ChatFormat.CYAN + "][" + sessionminecraft_s + "SessionsServer" + ChatFormat.CYAN + "][" + mojangapi_s + "API" + ChatFormat.CYAN + "]");

        } catch (IOException | ParseException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }
    }

}
