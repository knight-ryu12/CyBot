package io.github.turtlehunter.ircbot;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

/**
 * turtlehunter.github.IRCBot - uriel IRCBot 21/2/2016
 */
public class IRCBot extends PircBot {
    public IRCBot() {
        this.setName("CykrixBot"); //Dont use urielsalisBot or they scold me :(
        this.setLogin("IRCBot");
        //config here
    }

    public static User u;

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        Main.main.received(channel, sender, login, hostname, message);
    }

    protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        Main.main.invite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
    }
}
