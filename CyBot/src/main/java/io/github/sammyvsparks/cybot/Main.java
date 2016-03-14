package io.github.sammyvsparks.cybot;


import io.github.sammyvsparks.cybot.Methods.Commands;
import io.github.sammyvsparks.cybot.Methods.IrcMethods;
import io.github.sammyvsparks.cybot.Methods.MinecraftMethods;
import io.github.sammyvsparks.cybot.Methods.UtilityMethods;
import org.jibble.pircbot.IrcException;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private Commands c;
    private MinecraftMethods mc;
    private UtilityMethods ut;
    private IrcMethods irc;

    public static IRCBot ircBot; // Leave This Alone
    public static Main main; // Leave This Alone
    public static String botname = "changeme"; // ex. CyBot -- This is Automatically Grouped With Your Bots Main Account
    public static String password = "changeme"; // ex. CxVxCxBx
    public static String home_server = "#changeme"; // ex. #test_server
    public static String home_network = "changeme"; // ex. irc.esper.net
    public static String p_host = "changeme"; // Your User Name Here
    public static ArrayList<String> superadmin = new ArrayList<String>();
    public static ArrayList<String> admin = new ArrayList<String>();

    public static void main(String[] args) {
        main = new Main();
    }

    public Main() {
        initBot();
    }

    public void initBot() {

        ircBot = new IRCBot();
        c = new Commands();
        mc = new MinecraftMethods();
        ut = new UtilityMethods();
        irc = new IrcMethods();

        try {
            ircBot.connect(home_network);
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }

        irc.changeName(botname);
        ut.joinChannel(home_server);
        irc.sendMessage("NickServ", "identify " + password);

        superadmin.add(p_host);
    }

    // Moving to Own Class Shortly
    public void invite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        if(superadmin.contains(sourceNick) || sourceNick.equals(p_host)) {
            irc.sendMessage(sourceNick, sourceNick + ": I have Joined The Channel [" + channel + "]");
            ut.joinChannel(channel);
        } else {
            irc.sendError(sourceNick, "You Cannot Invite Me To " + channel + ". @" + sourceNick );
        }
    }

    public void received(String channel, String sender, String login, String hostname, String message) {
        if (!(message.startsWith("!"))) irc.getLogger("Message >> <" + sender + ">" + "<" + channel + "> + " + message);
        if ((message.startsWith("!"))) irc.getLogger("Command >> <" + sender + ">" + "<" + channel + "> + " + message);

        superadmin.add(""); // Adds User To SUPER_ADMIN
        admin.add(""); // Adds User To ADMIN

        String[] s = message.split(" ");

        if(message.startsWith("--help")) {
            c.helpCommand(sender, channel);
        }
    }
}

