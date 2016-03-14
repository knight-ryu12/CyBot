package io.github.sammyvsparks.cybot.Methods;

import io.github.sammyvsparks.cybot.ChatFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sammy on 3/13/2016.
 */
public class MinecraftMethods {

    private Commands c;
    private UtilityMethods ut;
    private IrcMethods irc;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void getUser(String user, String channel){
        String User;
        User = user;
        URL url = null;

        try {
            url = new URL("https://minecraft.net/haspaid.jsp?user=" + User);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = null;

        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean result = false;

        try {
            result = Boolean.valueOf(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String out = null; String str = Boolean.toString(result);
        if(result == true){ out = ChatFormat.GREEN+"TRUE"; } if(result == false) { out = ChatFormat.RED+"FALSE"; }

        irc.sendMessage(channel, ChatFormat.TEAL + ">> " + ChatFormat.CYAN + User + ChatFormat.TEAL + " >> " + ChatFormat.BLUE + "PAID: " + out);
    }
    public void getServer(String IP, String Port){

    }

}
