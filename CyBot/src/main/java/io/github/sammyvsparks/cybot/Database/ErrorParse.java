
package io.github.sammyvsparks.cybot.Database;

import io.github.sammyvsparks.cybot.ChatFormat;
import io.github.sammyvsparks.cybot.Main;

public class ErrorParse {

    public void EParse(String sender, String paste) {
        if (ReadError.probframe && ReadError.vboerr) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | VBOs Bug" + ChatFormat.CYAN + "]" + ChatFormat.GREEN + "Fix: Open Minecraft, Press \"Options\", Press \"Video Settings\" and toggle \"VBOs\".");
        } else if (ReadError.dxdiag && ReadError.dxdiag2) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Pixel Format Error" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Fix: Download Latest Graphics. (Possible Download Below)");
            Main.api.send("PangeaBot", ".dx " + paste);
        } else if (ReadError.ugraphics) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Outdated Graphics" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Fix: Download Latest Graphics. (DirectX Diagnostic Required)");
        } else if (ReadError.pirated || ReadError.debugging) {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Invalid / Broken Launcher" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Fix: Re-Download Latest Launcher ( http://www.minecraft.net/download )");
        }

        // No Detected Error Return
        else {
            Main.api.notice(sender, ChatFormat.CYAN + "[" + ChatFormat.DARK_GREEN + paste + ChatFormat.CYAN + "] [" + ChatFormat.DARK_BLUE + "Possible Error | Not Detected" + ChatFormat.CYAN + "] " + ChatFormat.GREEN + "Please Manually Check The URL!");
        }
    }
}
