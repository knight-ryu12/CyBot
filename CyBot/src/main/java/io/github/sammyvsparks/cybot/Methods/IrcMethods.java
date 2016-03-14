package io.github.sammyvsparks.cybot.Methods;

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;

/**
 * Created by sammy on 3/13/2016.
 */
public class IrcMethods {

    private Commands c;
    private MinecraftMethods mc;
    private IrcMethods irc;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void getLogger(String log){
        System.out.println(log);
    }

    public void changeName(String name) {
        if(!(Main.ircBot.getNick().equals(name))) {
            Main.ircBot.changeNick(name);
        } else {
            return;
        }
    }

    public void sendError(String target, String error) {
        Main.ircBot.sendNotice(target, error);
    }
    public void sendMessage(String target, String message){
        Main.ircBot.sendMessage(target, ChatFormat.CYAN + message);
    }

    public void setDelay(int delay){
        Main.ircBot.setMessageDelay(delay);
    }

}
