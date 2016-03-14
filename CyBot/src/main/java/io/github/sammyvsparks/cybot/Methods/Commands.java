package io.github.sammyvsparks.cybot.Methods;

import io.github.sammyvsparks.cybot.Main;

/**
 * Created by sammy on 3/13/2016.
 */
public class Commands {


    private MinecraftMethods mc;
    private UtilityMethods ut;
    private IrcMethods irc;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void helpCommand(String sender, String channel){
        Main.ircBot.sendMessage(channel, "Testing Testing");
    }

}
