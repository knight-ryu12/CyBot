/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot;


import io.github.sammyvsparks.cybot.Database.FactoidStorage;
import io.github.sammyvsparks.cybot.Database.ReadError;
import io.github.sammyvsparks.cybot.Systems.*;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import io.github.sammyvsparks.cybot.Wrappers.IRCWrappers;
import me.urielsalis.IRCApi.events.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static io.github.sammyvsparks.cybot.Main.cp;

/**
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

public class Listeners {
    private static String cpchan = "";
    private ReadError paste = new ReadError();
    private StatusCallBeta MineCallStatus = new StatusCallBeta();
    private ServerCallBeta MineCallServer = new ServerCallBeta();
    private UserCallBeta MineCallUser = new UserCallBeta();
    private UserHistoryBeta MineCallHistory = new UserHistoryBeta();
    private IsUpBeta IsUpCheck = new IsUpBeta();
    private GoogleIt GoogleThisBeta = new GoogleIt();
    private FactoidStorage Factoid = new FactoidStorage();
    private ExceptionErrors exceptions = new ExceptionErrors();

    @Command("index")
    public void helpCommand(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        Main.api.notice(sender, "Help Index -- ");
        Main.api.notice(sender, "Basic Commands: check, user, history, server, status, isup, support");
        Main.api.notice(sender, "Fun Commands: google, rr, credit, google, source");
    }

    @Command("session")
    public void sessionPlay(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        if (sender.equals("Cykrix")) {
            Main.api.notice(sender, ChatFormat.DARK_GREEN + "Unique Session Token: " + ChatFormat.DARK_BLUE + Main.uuid);
        } else {
            Main.api.notice(sender, ChatFormat.RED + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Grabbing Failed - Permission Failure!");
        }

    }

    @Command("rsession")
    public void rsessionPlay(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        if (sender.equals("Cykrix")) {
            Main.api.notice(sender, ChatFormat.DARK_GREEN + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Refreshed!");
            Main.refreshSession();
        } else {
            Main.api.notice(sender, ChatFormat.RED + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Refresh Failed - Permission Failure!");
        }

    }

    @Command("check")
    public void checkPaste(Event event, String[] args) {
        String userCp = ((OnPrivmsg) event).u.getNick();
        cpchan = ((OnPrivmsg) event).chan;
        paste.readError(args[0], userCp);
    }

    @Command("send")
    public void sendFaq(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        Factoid.factoidLookup(sender, channel, args);
    }

    @Command("database")
    public void getDatabase(Event event, String[] args) {
        // TODO : Database List
    }

    @Command("user")
    public void UserCheck(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        MineCallUser.UserCall(sender, channel, args);
    }

    @Command("server")
    public void ServerCheck(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        MineCallServer.ServerCheck(sender, channel, args);
    }

    @Command("status")
    public void StatusCheck(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        MineCallStatus.StatusCall(channel, sender);
    }

    @Command("history")
    public void UserHistoryCheck(Event event, String[] args) {
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        MineCallHistory.HistoryCall(sender, channel, args);
    }

    @Command("isup")
    public void IsUpCheck(Event event, String[] args){
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        IsUpCheck.IsUpCheck(sender, channel, args);
    }

    @Command("google")
    public void GoogleThis(Event event, String[] args){
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        GoogleThisBeta.Google(sender, channel, args);
    }

    @Command("support")
    public void HelpSources(Event event, String[] args){
        String sender = ((OnPrivmsg) event).u.getNick();
        String channel = ((OnPrivmsg) event).chan;
        Main.api.send(channel, " #minecrafthelp [Vanilla Support]; #minecraftforge [Modded Support]; #smp [Server Recruitment]; ");
        Main.api.send(channel, " #spigot [ www.spigotmc.org | Server Support]; #bukkit [Legacy Server Support]");
    }

    @Command("join")
    public void JoinChannel(Event event, String[] args) {
        String chan = args[0];
        if (!chan.startsWith("#")) {
            chan = "#" + chan;
        }

        if (!chan.equals("CyBot") && !chan.equals(Main.homechannel)) {
            Main.api.join(chan);
        }
    }

    @Command("quit")
    public void PartChannel(Event event, String[] args) {
        OnPrivmsg priv = (OnPrivmsg) event;
        if (!priv.chan.equals("CyBot") && !priv.chan.equals("#botminecraft")) {
            Main.api.notice(priv.u.getNick(), "Disconnecting:QUIT_COMMAND | Channel Connection Terminated.");
            Main.api.partChannel(priv.chan, "Disconnected:QUIT_COMMAND");
        }
    }

    @Command("credit")
    public void CreditNotice(Event event, String[] args) {
        OnPrivmsg priv = (OnPrivmsg) event;
        if (!priv.chan.equals("CyBot")) {
            Main.api.send(priv.chan, "Credit to" + ChatFormat.DARK_GREEN + " Urielsalis " + ChatFormat.NORMAL + "for " + ChatFormat.DARK_BLUE + " Urielsalads " + ChatFormat.NORMAL + " and the " + ChatFormat.BLUE + " Intel Driver Database Lookup");
            Main.api.send(priv.chan, "Credit to" + ChatFormat.DARK_GREEN + " Extreme " + ChatFormat.NORMAL + "for " + ChatFormat.DARK_BLUE + " ExtremeBot " + ChatFormat.NORMAL + " and the " + ChatFormat.BLUE + " AMD|Nvidia Driver Database Lookup");
        }
    }

    @Command("source")
    public void SourceCode(Event event, String[] args) {
        OnPrivmsg priv = (OnPrivmsg) event;
        Main.api.send(priv.chan, "My source code is available at: https://github.com/sammyvsparks/CyBot");
    }

    @Command("rr")
    public void RandomRevolver(Event event, String[] args) {
        double x = 0; String chan = ((OnPrivmsg) event).chan;
        Random rand = new Random();
        x = rand.nextInt(100) + 1;
        System.out.println(x);

        if((int) x >= 80.0){ Main.api.send(chan, ChatFormat.GREEN + "Bang Bang!"); }
        else { Main.api.send(chan, ChatFormat.RED + "Click Click!"); }
    }

    @EventHandler("onInvite")
    public void onInvite(Event event) {
        OnInvite inv = (OnInvite) event;
        String chan = inv.chan;

        if (!inv.chan.equals("CyBot")) {
            Main.api.join(chan);
        }
    }

    @EventHandler("onPrivmsg")
    public void onMessage(Event event) {
        OnPrivmsg priv = (OnPrivmsg) event;
        String[] args = priv.msg.split(" ");
        String channel = cpchan;
        if (priv.u.getNick().equals("PangeaBot") || priv.u.getNick().equals("Urielsalads") || priv.u.getNick().equals("ExtremeBot")) {
            // Empty for a REASON!
        } else if (priv.msg.startsWith(cp)) {
            String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println("[CMD][" + timeStamp + "][" + priv.chan + "][" + priv.u.getNick() + "] : " + priv.msg);
        } else {
            String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println("[MSG][" + timeStamp + "][" + priv.chan + "][" + priv.u.getNick() + "] : " + priv.msg);
        }

        if (priv.u.getNick().equals("PangeaBot")) {
            Main.api.send("ExtremeBot", priv.msg);
            Main.api.send("Urielsalads", priv.msg);
        }

        if (priv.chan.equals("CyBot") && priv.u.getNick().equals("Urielsalads")) { // Credit to Urielsalis for Intel Database
            if (priv.msg.contains(".com/product")) {
                Main.api.send(channel, "Driver Information: " + ChatFormat.TEAL + priv.msg);
            } else if (priv.msg.contains("downloadcenter.intel.com/download") || !priv.msg.contains("No drivers")) {
                Main.api.send(channel, "Intel Download: " + ChatFormat.YELLOW + priv.msg);
            } else if (priv.msg.contains("No Drivers")) {
                Main.api.notice(channel, "Intel Download: " + ChatFormat.YELLOW + priv.msg);
            }
        }

        if (priv.chan.equals("CyBot") && priv.u.getNick().equals("ExtremeBot")) { // Credit to Extreme for His Database Lookup
            // TODO : EXTREMEBOT
            System.out.println("ExtremeBot >" + priv.msg);
        }

    }
}
