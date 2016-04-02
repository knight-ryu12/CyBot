/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package io.github.sammyvsparks.cybot.Wrappers;

import io.github.sammyvsparks.cybot.Main;
import me.urielsalis.IRCApi.events.Command;
import me.urielsalis.IRCApi.events.Event;
import me.urielsalis.IRCApi.events.OnPrivmsg;

import java.util.ArrayList;

public class IRCWrappers {

    public void joinChannel(Event event, String[] args){
        OnPrivmsg priv = (OnPrivmsg) event;
        ArrayList<String> ch = new ArrayList<>();

        for(String arg: args) {
            if (!ch.contains(arg)) {
                if(!(arg.startsWith("#"))){ arg = "#" + arg; }
                Main.api.join(arg);
                ch.add(arg);
            }
        }
    }

    public void partChannel(Event event, String[] args){
        OnPrivmsg priv = (OnPrivmsg) event;
        if(priv.u.getNick() != ""){ }
        else {
            ArrayList<String> ch = new ArrayList<>();

            for (String arg : args) {
                if (!ch.contains(arg)) {
                    if (!(arg.startsWith("#"))) {
                        arg = "#" + arg;
                    }
                    Main.api.partChannel(arg, " Disconencted! ");
                    ch.add(arg);
                }
            }

        }
    }
}
