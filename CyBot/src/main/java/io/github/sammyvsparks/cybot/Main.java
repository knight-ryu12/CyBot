/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot;

import me.urielsalis.IRCApi.EventManager;
import me.urielsalis.IRCApi.IRCApi;

import java.util.ArrayList;
import java.util.UUID;

/**
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

public class Main {
    public static IRCApi api;
    public static Main main;
    static String uuid = UUID.randomUUID().toString();
    static String cp = ";";
    static String homechannel = "#botminecraft";
    private static ArrayList<String> channels = new ArrayList<>();

    public Main() {
        System.out.println("Initializing...");
        this.initBot();
    }

    public static void main(String[] args) {
        main = new Main();

    }

    static void refreshSession() {
        uuid = UUID.randomUUID().toString();
    }

    private void joinChannel(String channel) {
        if (channels.contains(channel)) {
            return;
        }
        api.join(channel);
        System.out.println("Joined Channel " + channel);
        channels.add(channel);
    }

    private void initBot() {
        api = new IRCApi();

        new Thread() {
            @Override
            public void run() {
                api.init("irc.esper.net", 6667, "", "DevCyBot", "DevCyBot", "DevCyBot", false);
                api.start();
            }
        }.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Reflection Start... [Loading Files & Systems]");
        EventManager.commandPrefix = cp;
        EventManager.addClass(Listeners.class);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Loading Complete [Reflection Complete]");
        this.joinChannel("#botserver");
        this.joinChannel("#minecrafthelp.breakroom");
        this.joinChannel(homechannel);
        System.out.println("Session Token: " + uuid);
    }
}
