package io.github.sammyvsparks.cybot.Database;

import io.github.sammyvsparks.cybot.Main;

/** All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 *  Developer of Personal Code | Cykrix
 *  -- https://github.com/sammyvsparks/CyBot
 */

public class FactoidStorage {

    public void factoidLookup(String sender, String[] lookup){
        int count = 0; boolean send = false;
        for(String str : lookup){
            count++;
        }

        if(count != 2){
            Main.api.notice(sender, "No Player or Factoid Selected.");
        } else { send=true; }

        if(send){
            if(lookup[1].contains("minecraft/")){
                MinecraftLookup(lookup);
            }
            if(lookup[1].contains("mojang/")){
                Main.api.notice(sender, "Mojang Database Is Currently Empty.");
            }
            if(lookup[1].contains("help/")){
                Main.api.notice(sender, "Help and Bug Database is Currently Empty.");
            }
        }
    }

    public String MinecraftLookup(String[] lookup){
        if(lookup[1].equals("minecraft/release")){
            Main.api.notice(lookup[0], lookup[0] + ", Minecraft\'s Initial Release Date is \"October 7, 2011\"");
        }
        if(lookup[1].equals("minecraft/website") || lookup[1].equals("minecraft/web")){
            Main.api.notice(lookup[0], lookup[0] + ", Minecraft\'s Website is Located at ( http://www.minecraft.net/ ).");
        }
        if(lookup[1].equals("minecraft/download") || lookup[1].equals("minecraft/website/download") || lookup[1].equals("minecraft/web/download")){
            Main.api.notice(lookup[0], lookup[0] + ", You can download Minecraft from ( http://www.minecraft.net/download ).");
        }
        if(lookup[1].equals("minecraft/reinstall")){
            Main.api.notice(lookup[0], lookup[0] + ", You may need to reinstall your Local Minecraft Files.");
            Main.api.notice(lookup[0], lookup[0] + ",  Locations( Windows: %appdata%/.minecraft, OS X: ?, Linux and Varients: ? )");
        }

        return "";
    }

}
