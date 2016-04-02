package io.github.sammyvsparks.cybot.Database;

public class FactoidStorage {

    public void factoidMinecraft(String msg) {
        String[] var = msg.split("/");

        if(var[0] == null){
            var[0] = "User";
        } else {
            var[0] = var[0];
        }

    }

}
