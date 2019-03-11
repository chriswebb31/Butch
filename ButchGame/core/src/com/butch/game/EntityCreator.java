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
        String propertyType = "NPC" + "/";
        //Name of the entity
        String propertyName = "Morgan";

        try {
            output = new FileOutputStream(propertyType + propertyName + ".properties");

            //Uncomment the required set of entity properties
            //Comment all the ones that aren't required

            // Gun
//            prop.setProperty("id", "13");
//            prop.setProperty("gunName", "Musket");
//            prop.setProperty("gunType", "0");
//            prop.setProperty("accuracy", "15");
//            prop.setProperty("clip", "1");
//            prop.setProperty("clipSize", "1");
//            prop.setProperty("damage", "100");
//            prop.setProperty("oneHanded", "false");
//            prop.setProperty("fireRate", "1");
//            prop.setProperty("reloadSpeed", "3");
//            prop.setProperty("speed", "15");

            // NPC
            prop.setProperty("type", "0");
            prop.setProperty("speech", "I think I heard some scuffling up ahead!");
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
