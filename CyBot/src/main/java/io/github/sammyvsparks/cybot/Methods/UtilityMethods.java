package io.github.sammyvsparks.cybot.Methods;

import io.github.sammyvsparks.cybot.Main;

/*
* This Class Uses Multi-Class Methods To Link With Every File.
* You can freely edit, update, add more to all these files.
 */

public class UtilityMethods {

    private IrcMethods irc = new IrcMethods();
    private Commands cmd = new Commands();
    private MinecraftMethods mc = new MinecraftMethods();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void kick(String target, String[] message){

    }
    public void ban(String channel, String target){

    }

    public void joinChannel(String channel){
        if(!(channel.startsWith("#"))){ channel = "#" + channel; }

        irc.getLogger("Connection Complete: " + Main.home_network + " >> true >> " + channel);
        Main.ircBot.joinChannel(channel);

    }
    public void leaveChannel(String channel){
        if(!(channel.startsWith("#"))){ channel = "#" + channel; }

        irc.getLogger("Connection Complete: " + Main.home_network + " >> null_route >> " + channel);
        Main.ircBot.joinChannel(channel);
    }

}
