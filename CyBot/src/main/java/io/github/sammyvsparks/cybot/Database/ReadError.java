package io.github.sammyvsparks.cybot.Database;

/** All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 *  Developer of Personal Code | Cykrix
 *  -- https://github.com/sammyvsparks/CyBot
 */

import io.github.sammyvsparks.cybot.Main;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadError {

    public static ReadError reader = new ReadError();
    ExceptionErrors exceptions = new ExceptionErrors();
    ErrorParse parse = new ErrorParse();

    // Variable Setup (Error Scans)
    public static boolean probframe = false; public static boolean vboerr = false; public static boolean debugging = false; public static boolean ugraphics = false;
    public static boolean pirated = false; public static boolean dxdiag = false; public static boolean dxdiag2 = false; public static boolean av = false;
    public static boolean av2 = false; public static boolean av3 = false;

    static String cardbrand = "";
    // #

    public void readError(String url, String sender) {

        url = url.replace("https", "http");

        if(!( url.contains("http://paste.ubuntu.com/") || url.contains("http://pastebin.com/") || url.contains("http://pastie.org/")
        || url.contains("http://gist.github.com/") || url.contains("http://pastebin.mozilla.org/") ) ){
            Main.api.notice(sender, "Paste Site Unvalidated, Results may not be read correctly! (Please contact bot author to validate site)"); }

        try {
            URL ur = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(ur.openStream()));

            String inputLine; // Scans File For Errors
            while ((inputLine = in.readLine()) != null) {
                // Setup If/Else
                if(inputLine.equals("<><><><>Blank<><><><>")) { }
                // Problematic Frame [VBOs Error]
                else if( (inputLine.contains("Problematic frame:")) ){ probframe = true; }
                else if( (inputLine.contains("[ig7")) || (inputLine.contains("[ig8")) || (inputLine.contains("[ig9")) ){ vboerr = true; }
                else if( (inputLine.contains("[ig75")) ){ vboerr = true; }
                // Outdated Graphics [Update Graphics]
                else if( (inputLine.contains("[ig4")) ){ ugraphics = true; }
                // DirectX Diagnostic Error (Graphics)
                else if( (inputLine.contains("Card name: "))){ dxdiag = true; }
                else if( (inputLine.contains("System Devices"))){ dxdiag2 = true; }
                // Pirated (Cracked) Launcher (Un-Supported, Non-Legal)
                else if( (inputLine.contains("JIT Debugging"))){ debugging = true; }
                else if( (inputLine.contains("cracked-version"))){ pirated = true; }
                // !

                //!
            }

            parse.EParse(sender, url);

            in.close();
        }
        catch (IOException e) {
            Main.api.notice(sender, exceptions.PARSE_FAILED());
        }
    }

}
