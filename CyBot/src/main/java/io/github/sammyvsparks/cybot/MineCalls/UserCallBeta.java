package io.github.sammyvsparks.cybot.MineCalls;

/** All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 *  Developer of Personal Code | Cykrix
 *  -- https://github.com/sammyvsparks/CyBot
 */

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UserCallBeta {

    JSONParser parser = new JSONParser();
    ExceptionErrors exception = new ExceptionErrors();

    // Code Stats Here

    public void UserCall(String sender, String[] args) { int c = 0;
        String target = "Invalid";
        for(String arg : args){
            c++;
        }
        if(c != 2){
            Main.api.notice(sender, "Invalid Username!");
        } if (c == 2){
            target = args[1];
        }
        URL paid = null;
        URL migr = null;
        URL uuid = null;
        String JRead = null; String JRead2 = null; String JRead3 = null;

        try {
            paid = new URL("https://minecraft.net/haspaid.jsp?user=" + target);
            migr = new URL("https://us.mc-api.net/v3/migrated/" + target);
            uuid = new URL("https://us.mc-api.net/v3/uuid/" + target);
        } catch (MalformedURLException e) {
            Main.api.notice(sender, exception.API_OFFLINE());
        }

        System.out.println("[User][" + target + "] ");

        String PAID = "";
        boolean MIGRATED_C = false;
        String MIGRATED;
        String UUID = "";

        try {
            Scanner s = new Scanner(paid.openStream(), "UTF-8");
            JRead = s.useDelimiter("\\A").next();
            s.close();

            Scanner s2 = new Scanner(migr.openStream(), "UTF-8");
            JRead2 = s2.useDelimiter("\\A").next();
            s2.close();

            Scanner s3 = new Scanner(uuid.openStream(), "UTF-8");
            JRead3 = s3.useDelimiter("\\A").next();
            s3.close();

            boolean pay = Boolean.parseBoolean(JRead);
            JSONObject mig = (JSONObject) parser.parse(JRead2);
            JSONObject uui = (JSONObject) parser.parse(JRead3);

            PAID = Boolean.toString(pay);
            MIGRATED_C = (boolean) mig.get("migrated");
            UUID = (String) uui.get("full_uuid");

        } catch (ParseException | IOException e) {
            Main.api.notice(sender, exception.PARSE_FAILED());
        }

        // Now Lets Output Checks
        if(MIGRATED_C) MIGRATED = ChatFormat.GREEN + "Migrated"; else MIGRATED = ChatFormat.RED + "Migrated";
        if(PAID.equals("true")) PAID = ChatFormat.GREEN + "Paid"; else PAID = ChatFormat.RED + "Paid";
        Main.api.notice(sender, ChatFormat.CYAN + "[ " + ChatFormat.DARK_BLUE + target + ChatFormat.YELLOW + " | " + ChatFormat.DARK_BLUE + UUID + ChatFormat.CYAN + " ] [" + PAID + ChatFormat.CYAN +  "] [" + MIGRATED + ChatFormat.CYAN + "]");
    }

}
