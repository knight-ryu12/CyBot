/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot.Database;

import io.github.sammyvsparks.cybot.Main;

/**
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

public class FactoidStorage {

    public void factoidLookup(String sender, String channel, String[] lookup) {
        int count = 0;
        boolean send = false;
        for (String ignored : lookup) {
            count++;
        }

        if (count != 2) {
            Main.api.notice(sender, "No Player or Factoid Selected.");
        } else {
            send = true;
        }

        if (send) {
            if (lookup[1].contains("minecraft/")) {
                MinecraftLookup(lookup, channel);
            }
            if (lookup[1].contains("mojang/")) {
                Main.api.send(sender, "Mojang Database Is Currently Empty.");
            }
            if (lookup[1].contains("help/")) {
                Main.api.send(sender, "Help and Bug Database is Currently Empty.");
            }
        }
    }

    private String MinecraftLookup(String[] lookup, String channel) {
        switch (lookup[1]) {
            case "minecraft/release":
                Main.api.send(channel, lookup[0] + ", Minecraft\'s Initial Release Date is \"October 7, 2011\"");
                break;
            case "minecraft/website":
            case "minecraft/web":
                Main.api.send(channel, lookup[0] + ", Minecraft\'s Website is Located at ( http://www.minecraft.net/ ).");
                break;
            case "minecraft/download":
            case "minecraft/website/download":
            case "minecraft/web/download":
                Main.api.send(channel, lookup[0] + ", You can download Minecraft from ( http://www.minecraft.net/download ).");
                break;
            case "minecraft/reinstall":
                Main.api.send(channel, lookup[0] + ", You may need to reinstall your Local Minecraft Files.");
                Main.api.send(channel, lookup[0] + ",  Locations( Windows: %appdata%/.minecraft, OS X: ?, Linux and Varients: ? )");
                break;
            case "minecraft/buy":
                Main.api.send(channel, lookup[0] + ", You can purchase Minecraft from ( http://www.minecraft.net/buy/ )");
                Main.api.send(channel, lookup[0] + ", (Note, Prices vary from Region from Region around the World.");
                break;
            case "minecraft/login/throttle":
                Main.api.send(channel, lookup[0] + ", You appear to have Throttled your login, this happens after 3 failed login attempts and lasts for Three (3) hours.");
                Main.api.send(channel, lookup[0] + ", (Note: If you login during this time-frame you will reset the timer. If you still cannot login after please return to the chat.");
                break;
        }
        return "";
    }

}
