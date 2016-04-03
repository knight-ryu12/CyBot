package io.github.sammyvsparks.cybot;

import me.urielsalis.IRCApi.EventManager;
import me.urielsalis.IRCApi.IRCApi;

import java.util.ArrayList;
import java.util.UUID;

/** All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 *  Developer of Personal Code | Cykrix
 *  -- https://github.com/sammyvsparks/CyBot
 */

public class Main {
    public static IRCApi api;
    public static Main main;
    static ArrayList<String> channels = new ArrayList();
    public static String uuid = UUID.randomUUID().toString();
    public static String homechannel = "#botminecraft";

    public static void main(String[] args) {
        main = new Main();
    }

    public Main() {
        System.out.println("Initializing...");
        this.initBot();
    }

    private void joinChannel(String channel) {
        if(channels.contains(channel)){ return; }
        api.join(channel);
        System.out.println("Joined Channel " + channel);
        channels.add(channel);
    }

    private void initBot() {
        api = new IRCApi();

        new Thread() {
            @Override
            public void run() {
                api.init("irc.esper.net", 6667, "", "CyBot", "CyBot", "CyBot", false);
                api.start();
            }
        }.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Reflection Start... [Loading Files & Systems]");
        EventManager.commandPrefix = ">";
        EventManager.addClass(Listeners.class);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Loading Complete [Reflection Complete]");
        this.joinChannel("#botserver");
        this.joinChannel(homechannel);
        System.out.println("Session Token: " + uuid);
    }

    public static void refreshSession() {
        uuid = UUID.randomUUID().toString();
    }
}
