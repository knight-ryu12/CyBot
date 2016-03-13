package io.github.turtlehunter.ircbot;


import ch.jamiete.mcping.MinecraftPing;
import ch.jamiete.mcping.MinecraftPingOptions;
import ch.jamiete.mcping.MinecraftPingReply;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private IRCBot ircBot; // Leave This Alone
    public static Main main; // Leave This Alone
    public static String botname = "changethis"; // ex. CyBot
    public static String password = "changethis"; // ex. CxVxCxBx
    public static String home_server = "#changethis"; // ex. #test_server
    public static String home_network = "irc_ip"; // ex. irc.esper.net
    public static String p_supderadmin = "your_name_here"; // Your User Name Here

    public static void main(String[] args) {
        main = new Main();
    }

    public Main() {
        initBot();
    }
    private void initBot() {
        ircBot = new IRCBot();

        try {
            ircBot.connect(home_network);
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }

        logger("Connected: " + ircBot.isConnected());
        changeName(botname);
        ircBot.joinChannel(home_server);
        ircBot.sendMessage("NickServ", "identify " + password);
    }

    public void invite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        ArrayList<String> superadmin = new ArrayList<String>();
        superadmin.add(p_supderadmin);

        if(!(superadmin.contains(sourceNick))){
            sendError(sourceHostname, "-- You cannot invite me to " + channel + "--");
            return;
        }

        ArrayList<String> admin = new ArrayList<String>();
        admin.add("Cykrix");

        if(admin.contains(sourceNick)){
            ircBot.joinChannel(channel);
        }
    }

    public void received(String channel, String sender, String login, String hostname, String message) {
        if (!(message.startsWith("!"))) logger("Message >> <" + sender + ">" + "<" + channel + "> | " + message);
        if ( (message.startsWith("!"))) logger("Command >> <" + sender + ">" + "<" + channel + "> | " + message);

        ArrayList<String> superadmin = new ArrayList<String>();
        ArrayList<String> admin = new ArrayList<String>();

        superadmin.add(p_supderadmin);
        admin.add(p_supderadmin);

        String[] s = message.split(" ");
        String target = s[1];

        // Help Command
        if (message.startsWith("!help")) {
            ircBot.sendNotice(sender, "-- CyBot Commands --");
            ircBot.sendNotice(sender, "-- Command Prefix '!' -- ");
            ircBot.sendNotice(sender, " (!help | !ping |)");
        }

        // Bot Utilities
        if(message.startsWith("!ping")){
            ircBot.sendNotice(sender, "Pong!");
        }

        // Minecraft Utilities
        if (message.startsWith("!mcping")) {
            try {
                MinecraftPingReply data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname(s[1]).setPort(Integer.parseInt(s[2])));
                String motd = data.getDescription();

                motd = motd.replace("§1", "");
                motd = motd.replace("§2", "");
                motd = motd.replace("§3", "");
                motd = motd.replace("§4", "");
                motd = motd.replace("§5", "");
                motd = motd.replace("§6", "");
                motd = motd.replace("§7", "");
                motd = motd.replace("§8", "");
                motd = motd.replace("§9", "");
                motd = motd.replace("§0", "");
                motd = motd.replace("§a", "");
                motd = motd.replace("§b", "");
                motd = motd.replace("§c", "");
                motd = motd.replace("§d", "");
                motd = motd.replace("§e", "");
                motd = motd.replace("§f", "");
                motd = motd.replace("§r", "");
                motd = motd.replace("§n", "");
                motd = motd.replace("§l", "");
                motd = motd.replace("§k", "");
                motd = motd.replace("§i", "");
                motd = motd.replace("§o", ""); // MOTD FIX

                sendMessage(channel, ChatFormat.DARK_BLUE + "<" + s[1] + ":" + s[2] + ">");
                sendMessage(channel, "Status: " + ChatFormat.GREEN + "ONLINE");
                sendMessage(channel, "Version: " + ChatFormat.MAGENTA + data.getVersion().getName() + " (" + data.getVersion().getProtocol() + ")");
                sendMessage(channel, "Players: " + ChatFormat.YELLOW + data.getPlayers().getOnline() + " / " + data.getPlayers().getMax() );
                sendMessage(channel, "MOTD: " + ChatFormat.BROWN + motd);

            } catch (IOException e) {
                sendMessage(channel, ChatFormat.DARK_BLUE + "<" + s[1] + ":" + s[2] + ">");
                sendMessage(channel, "Status: " + ChatFormat.RED + "OFFLINE or UNREACHABLE");

            }
        }
        if (message.startsWith("!mcuser")) {
            String User;
            User = s[1];
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

            String out = null;
            String str = Boolean.toString(result);
            if(result == true){ out = ChatFormat.GREEN+"TRUE"; }
            if(result == false) { out = ChatFormat.RED+"FALSE"; }

            sendMessage(channel, ChatFormat.GREEN + "|User Check| " + ChatFormat.LIGHT_GRAY +"<"+ User +">"+ " " + ChatFormat.CYAN + "[PAID: " + out + ChatFormat.CYAN + "]");
        }
        if (message.startsWith("!mcstatus")) {
            // WIP : Submit Code If You Feel Like Making This : Checks Mojangs Server Status
        }

        // Channel Utilities
        if (message.startsWith("!join")) {
            if(!(superadmin.contains(sender))){ sendError(sender, " You Cannot Set The Me To Enter Another Channel!"); return;}
            else {
                String joining = s[1];
                if (!(joining.startsWith("#"))) {
                    joining = "#" + joining;
                }
                ircBot.joinChannel(joining);
            }
        }
        if (message.equalsIgnoreCase("!quit")) {
            if(!(superadmin.contains(sender))){
                sendError(sender, " You Cannot Disconnect Me From This Channel!");
                return;
            } else {
                ircBot.partChannel(channel);
            return;
        } }
        if (message.equalsIgnoreCase("!shutdown")) {
            if(!(superadmin.contains(sender))){
                sendError(sender, " You Cannot Close The Bot OS!");
                return;
            } else {
            ircBot.disconnect();
            ircBot.dispose();
            return;
        } }

        // IRC Utilities
        if (message.startsWith("!kick")) {
            User users[] = ircBot.getUsers( channel );
            User u = null;
            User uu = null;

            for( User user : users ){
                if( sender.equals( user.getNick().equals(s[1]) ) ){
                    u = user;
                    break;
                }
            }

            for( User user : users ){
                if( sender.equals( user.getNick().equals(sender) ) ){
                    uu = user;
                    break;
                }
            }

            if(u.isOp()){ sendError(sender, "You Cannot Kick This User!"); return; }
            if(u.hasVoice() && (!(uu.isOp()))){ sendError(sender, "You Cannot Kick This User!"); return; }

            ArrayList<String> kickmsg = new ArrayList<String>();

            int n = 2;
            while(!(s[n].isEmpty())){
                kickmsg.add(s[n]);
                n++;
            }

            ircBot.kick(channel, u.getNick(), kickmsg.toString());

        }
        if (message.startsWith("!ban")) {

            User users[] = ircBot.getUsers( channel );
            User u = null;
            User uu = null;

            for( User user : users ){
                if( sender.equals( user.getNick().equals(s[1]) ) ){
                    u = user;
                    break;
                }
            }

            for( User user : users ){
                if( sender.equals( user.getNick().equals(sender) ) ){
                    uu = user;
                    break;
                }
            }

            if(!(u.isOp())) { sendError(sender, "You Cannot Use This Command"); return; }
            if(u.isOp()){ sendError(sender, "You Cannot Ban This User!"); return; }
            if(u.hasVoice() && (!(uu.isOp()))){ sendError(sender, "You Cannot Ban This User!"); return; }

            ArrayList<String> kickmsg = new ArrayList<String>();

            int n = 2;
            while(!(s[n].isEmpty())){
                kickmsg.add(s[n]);
                n++;
            }

            ircBot.ban(channel, u.getNick());

        }
        if (message.startsWith("!unban")) {

            User users[] = ircBot.getUsers( channel );
            User u = null;
            User uu = null;

            for( User user : users ){
                if( sender.equals( user.getNick().equals(s[1]) ) ){
                    u = user;
                    break;
                }
            }

            for( User user : users ){
                if( sender.equals( user.getNick().equals(sender) ) ){
                    uu = user;
                    break;
                }
            }

            if(!(u.isOp())) { sendError(sender, "You Cannot Use This Command"); return; }

            ircBot.unBan(channel, u.getNick());

        }
    }

    // Custom Methods
    public void changeName(String name) {
        if(!(ircBot.getNick().equals(name))) {
            ircBot.changeNick(name);
        } else {
            return;
        }
    }
    public void sendError(String target, String error) {
        ircBot.sendNotice(target, error);
    }
    public void setDelay(int delay){
        ircBot.setMessageDelay(delay);
    }
    public void sendMessage(String target, String message){
        ircBot.sendMessage(target,ChatFormat.CYAN + message);
    }
    public void logger(String log){
        System.out.println(log);
    }
    public void kick(String target, String[] message){

    }
}

