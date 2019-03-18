package com.butch.game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class EntityCreator {
    public static void main(String[] args) {
        Properties prop = new Properties();
        OutputStream output = null;
        //i.e. Gun, Item, Enemy, Player
        String propertyType = "Gun" + "/";
        //Name of the entity
        String propertyName = "Rey";

        try {
            output = new FileOutputStream(propertyType + propertyName + ".properties");

            //Uncomment the required set of entity properties
            //Comment all the ones that aren't required

            // Gun
            prop.setProperty("id", "14");
            prop.setProperty("gunName", "Melee");
            prop.setProperty("gunType", "4");
            prop.setProperty("accuracy", "15");
            prop.setProperty("clip", "9999");
            prop.setProperty("clipSize", "9999");
            prop.setProperty("damage", "10");
            prop.setProperty("oneHanded", "false");
            prop.setProperty("fireRate", "0.5f");
            prop.setProperty("reloadSpeed", "3");
            prop.setProperty("speed", "15");

            // NPC
            prop.setProperty("type", "30");
            prop.setProperty("speech","You've been good to me Butch... go north-east, I'm hearing there's a gap to escape from this mess");
            prop.setProperty("speechFollowup", "Stop Talking To Me");
            //Item
            //Breakable

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
