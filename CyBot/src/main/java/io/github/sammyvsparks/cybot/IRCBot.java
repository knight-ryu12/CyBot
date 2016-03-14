package io.github.sammyvsparks.cybot;

import org.jibble.pircbot.PircBot;

public class IRCBot extends PircBot {
    public IRCBot() {
        this.setName("changeme"); // Change This
        this.setLogin("changeme"); // Change This
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        Main.main.received(channel, sender, login, hostname, message);
    }

    @Override
    protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        Main.main.invite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
    }
}
