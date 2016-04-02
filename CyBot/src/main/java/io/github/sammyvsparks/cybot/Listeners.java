package io.github.sammyvsparks.cybot;

import io.github.sammyvsparks.cybot.Database.ReadError;
import io.github.sammyvsparks.cybot.MineCalls.ServerCallBeta;
import io.github.sammyvsparks.cybot.MineCalls.StatusCallBeta;
import io.github.sammyvsparks.cybot.MineCalls.UserCallBeta;
import io.github.sammyvsparks.cybot.SystemCalls.ExceptionErrors;
import io.github.sammyvsparks.cybot.Wrappers.IRCWrappers;
import me.urielsalis.IRCApi.events.*;


public class Listeners {
    ReadError paste = new ReadError();
    StatusCallBeta MineCallStatus = new StatusCallBeta();
    ServerCallBeta MineCallServer = new ServerCallBeta();
    UserCallBeta MineCallUser = new UserCallBeta();
    ExceptionErrors exceptions = new ExceptionErrors();
    IRCWrappers wrappers = new IRCWrappers();
    static String userCp = "";

    public Listeners() {
    }

    @Command("help")
    public void helpCommand(Event event, String[] args) {
        String sender = ((OnPrivmsg)event).u.getNick();
        Main.api.notice(sender, "Help Index -- ");
        Main.api.notice(sender, " >query server <IP>:<PORT> | >query user <USERNAME> | >query status | >scan <URL> |");
    }

    @Command("session")
    public void sessionPlay(Event event, String[] args) {
        String sender = ((OnPrivmsg)event).u.getNick();
        if(sender.equals("Cykrix")) {
            Main.api.notice(sender, ChatFormat.DARK_GREEN + "Unique Session Token: " + ChatFormat.DARK_BLUE + Main.uuid);
        } else {
            Main.api.notice(sender, ChatFormat.RED + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Grabbing Failed - Permission Failure!");
        }

    }

    @Command("rsession")
    public void rsessionPlay(Event event, String[] args) {
        String sender = ((OnPrivmsg)event).u.getNick();
        if(sender.equals("Cykrix")) {
            Main.api.notice(sender, ChatFormat.DARK_GREEN + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Refreshed!");
            Main.refreshSession();
        } else {
            Main.api.notice(sender, ChatFormat.RED + "Unique Session Token: " + ChatFormat.DARK_BLUE + "Refresh Failed - Permission Failure!");
        }

    }

    @Command("scan")
    public void checkPaste(Event event, String[] args) {
        String sender = ((OnPrivmsg)event).u.getNick();
        this.paste.readError(args[0], sender);
        userCp = sender;
    }

    @Command("query")
    public void QuerySystem(Event event, String[] args) {
        String sender = ((OnPrivmsg)event).u.getNick();
        String[] var4 = args;
        int var5 = args.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String s = var4[var6];
            if(s.contains("status")) {
                this.MineCallStatus.StatusCallBeta(sender);
                return;
            }

            if(s.contains("server")) {
                this.MineCallServer.ServerCheck(sender, args);
                return;
            }

            if(s.contains("user")) {
                userCp = sender;
                this.MineCallUser.UserCall(sender, args);
                return;
            }

            Main.api.notice(sender, this.exceptions.INVALID_INPUT_CHECK());
        }

    }

    @Command("join")
    public void JoinChannel(Event event, String[] args) {
        String chan = args[0];
        if(!chan.startsWith("#")) {
            chan = "#" + chan;
        }

        if(!chan.equals("CyBot")) {
            Main.api.join(chan);
        }
    }

    @Command("quit")
    public void PartChannel(Event event, String[] args) {
        OnPrivmsg priv = (OnPrivmsg)event;
        if(!priv.chan.equals("CyBot")) {
            Main.api.partChannel(priv.chan, "Disconnected:QUIT_COMMAND");
        }
    }

    @EventHandler("onInvite")
    public void onInvite(Event event) {
        OnInvite inv = (OnInvite)event;
        String chan = inv.chan;
        if(!inv.chan.equals("CyBot")) {
            Main.api.join(chan);
        }
    }

    @EventHandler("onPrivmsg")
    public void onMessage(Event event) {
        OnPrivmsg priv = (OnPrivmsg)event;
        String[] args = priv.msg.split(" ");
        if(!priv.u.getNick().equals("Urielsalads") || !priv.u.getNick().equals("ExtremeBot")) {
            System.out.println("[" + priv.chan + "][" + priv.u.getNick() + "] : " + priv.msg);
        }

        if(priv.chan.equals("CyBot") && priv.u.getNick().equals("PangeaBot")) {
            Main.api.send("Urielsalads", priv.msg);
            Main.api.send("ExtremeBot", priv.msg);
        }

        if(priv.chan.equals("CyBot") && priv.u.getNick().equals("Urielsalads")) {
            System.out.println("Uriel > " + priv.msg);
            if(priv.msg.contains("downloadcenter.intel.com/product")) {
                Main.api.notice(userCp, "Driver Information: " + ChatFormat.TEAL + priv.msg);
            } else if(priv.msg.contains("downloadcenter.intel.com/download") || priv.msg.contains("No drivers")) {
                Main.api.notice(userCp, "Intel Download: " + ChatFormat.YELLOW + priv.msg);
            }
        }

        if(priv.chan.equals("CyBot") && priv.u.getNick().equals("ExtremeBot")) {
            System.out.println("Extreme >" + priv.msg);
        }

    }
}