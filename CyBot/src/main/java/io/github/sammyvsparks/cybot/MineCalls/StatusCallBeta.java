package io.github.sammyvsparks.cybot.MineCalls;

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by sammy on 3/30/2016.
 */
public class StatusCallBeta {

    JSONParser parser = new JSONParser();
    ExceptionErrors exception = new ExceptionErrors();

    public void StatusCallBeta(String sender) {
        URL Status = null;

        try {
            Status = new URL("https://status.mojang.com/check");
            System.out.println("[Ping][StatusCheck] " + Status.toString());
            Scanner JReader = new Scanner(Status.openStream(), "UTF-8");
            String JRead = JReader.useDelimiter("\\A").next();
            JReader.close();

            JSONArray read = (JSONArray) parser.parse(JRead);

            JSONObject minecraftnet = (JSONObject) read.get(0);
            JSONObject minecraftsession = (JSONObject) read.get(1);
            JSONObject accountmojang = (JSONObject) read.get(2);
            JSONObject authmojang = (JSONObject) read.get(3);
            JSONObject skinsminecraft = (JSONObject) read.get(4);
            JSONObject authservermojang = (JSONObject) read.get(5);
            JSONObject sessionminecraft = (JSONObject) read.get(6);
            JSONObject texturesminecraft = (JSONObject) read.get(8);
            JSONObject mojangcom = (JSONObject) read.get(9);
            JSONObject mojangapi = (JSONObject) read.get(7);

            String minecraftnet_s = (String) minecraftnet.get("minecraft.net");
            String minecraftsession_s = (String) minecraftsession.get("session.minecraft.net");
            String accountmojang_s = (String) accountmojang.get("account.mojang.com");
            String authmojang_s = (String) authmojang.get("auth.mojang.com");
            String skinsminecraft_s = (String) skinsminecraft.get("skins.minecraft.net");
            String authservermojang_s = (String) authservermojang.get("authserver.mojang.com");
            String sessionminecraft_s = (String) sessionminecraft.get("sessionserver.mojang.com");
            String texturesminecraft_s = (String) texturesminecraft.get("textures.minecraft.net");
            String mojangcom_s = (String) mojangcom.get("mojang.com");
            String mojangapi_s = (String) mojangapi.get("api.mojang.com");

            // Parse
            if(minecraftnet_s.equals("green")) minecraftnet_s = ChatFormat.DARK_GREEN + "";
            else if(minecraftnet_s.equals("yellow")) minecraftnet_s = ChatFormat.YELLOW + "";
            else minecraftnet_s = ChatFormat.RED + "";

            if(minecraftsession_s.equals("green")) minecraftsession_s = ChatFormat.DARK_GREEN + "";
            else if(minecraftsession_s.equals("yellow")) minecraftsession_s = ChatFormat.YELLOW + "";
            else minecraftsession_s = ChatFormat.RED + "";

            if(accountmojang_s.equals("green")) accountmojang_s = ChatFormat.DARK_GREEN + "";
            else if(accountmojang_s.equals("yellow")) accountmojang_s = ChatFormat.YELLOW + "";
            else accountmojang_s = ChatFormat.RED + "";

            if(skinsminecraft_s.equals("green")) skinsminecraft_s = ChatFormat.DARK_GREEN + "";
            else if(skinsminecraft_s.equals("yellow")) skinsminecraft_s = ChatFormat.YELLOW + "";
            else skinsminecraft_s = ChatFormat.RED + "";

            if(authmojang_s.equals("green")) authmojang_s = ChatFormat.DARK_GREEN + "";
            else if(authmojang_s.equals("yellow")) authmojang_s = ChatFormat.YELLOW + "";
            else authmojang_s = ChatFormat.RED + "";

            if(authservermojang_s.equals("green")) authservermojang_s = ChatFormat.DARK_GREEN + "";
            else if(authservermojang_s.equals("yellow")) authservermojang_s = ChatFormat.YELLOW + "";
            else authservermojang_s = ChatFormat.RED + "";

            if(sessionminecraft_s.equals("green")) sessionminecraft_s = ChatFormat.DARK_GREEN + "";
            else if(sessionminecraft_s.equals("yellow")) sessionminecraft_s = ChatFormat.YELLOW + "";
            else sessionminecraft_s = ChatFormat.RED + "";

            if(texturesminecraft_s.equals("green")) texturesminecraft_s = ChatFormat.DARK_GREEN + "";
            else if(texturesminecraft_s.equals("yellow")) texturesminecraft_s = ChatFormat.YELLOW + "";
            else texturesminecraft_s = ChatFormat.RED + "";

            if(mojangcom_s.equals("green")) mojangcom_s = ChatFormat.DARK_GREEN + "";
            else if(mojangcom_s.equals("yellow")) mojangcom_s = ChatFormat.YELLOW + "";
            else  mojangcom_s = ChatFormat.RED + "";

            if(mojangapi_s.equals("green")) mojangapi_s = ChatFormat.DARK_GREEN + "";
            else if(mojangapi_s.equals("yellow")) mojangapi_s = ChatFormat.YELLOW + "";
            else mojangapi_s = ChatFormat.RED + "";

            Main.api.notice(sender, ChatFormat.CYAN + "[" + minecraftnet_s + "Website" + ChatFormat.CYAN +  "][" + minecraftsession_s + "Sessions" + ChatFormat.CYAN + "][" + skinsminecraft_s + "Skins" + ChatFormat.CYAN + "][" + texturesminecraft_s + "Textures" + ChatFormat.CYAN + "]" );
            Main.api.notice(sender, ChatFormat.CYAN + "[" + mojangcom_s + "Website" + ChatFormat.CYAN + "][" + accountmojang_s + "Account" + ChatFormat.CYAN +  "][" + authmojang_s + "Auth" + ChatFormat.CYAN +  "][" + authservermojang_s + "AuthServer" + ChatFormat.CYAN +  "][" + sessionminecraft_s + "SessionsServer" + ChatFormat.CYAN +  "][" + mojangapi_s + "API" + ChatFormat.CYAN + "]");

        } catch (IOException | ParseException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }
    }

}
