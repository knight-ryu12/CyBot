/*
 * Copyright (c) 2016 Samuel Voeller
 *  All files contained in this are utilizing code created by Cykrix (Samuel Voeller) and is Licensed Under General Public Use Policy with terms that follow
 *     - Original Copyright Header Remains Entact and Original
 *     - Credit is given when source code is utilized externally
 *     - You do not charge or monetize this project. (Keep it open source and free) outside of Optional Donations.
 * =============================================================================
 */

package io.github.sammyvsparks.cybot.Database;

/**
 * All Source Code In The 2 Main Library Files - Credit to Urielsalis [ https://github.com/turtlehunter/IRCApi ] [2 Licence Copies Attached]
 * Developer of Personal Code | Cykrix
 * -- https://github.com/sammyvsparks/CyBot
 */

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;

class ErrorParse {

    void EParse(String sender, String paste) {
        if (ReadError.probframe && ReadError.vboerr) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | VBOs Bug" + ChatFormat.CYAN + "]" + ChatFormat.GREEN + "Fix: Open Minecraft, Navigate to \"Options\", Then Press \"Video Settings\" and toggle \"VBOs\".");
        }

        else if (ReadError.dxdiag && ReadError.dxdiag2) {
            Main.api.send("PangeaBot", ".dx " + paste);
        }

        else if (ReadError.ugraphics) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Outdated Graphics" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Fix: Download Latest Graphics Driver. (DirectX Diagnostic Required)");
        }

        else if (ReadError.pirated || ReadError.debugging) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Invalid / Broken Launcher" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Fix: Re-Download Latest Launcher ( http://www.minecraft.net/download )");
        }

        // No Detected Error Return
        else {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Not Detected" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Please Manually Check The Paste!");
        }
    }
}
