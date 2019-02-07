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
        String propertyName = "MachineGun";

        try {
            output = new FileOutputStream(propertyType + propertyName + ".properties");

            //Uncomment the required set of entity properties
            //Comment all the ones that aren't required

            // Gun
            prop.setProperty("id", "11");
            prop.setProperty("gunName", "Machine Gun");
            prop.setProperty("gunType", "1");
            prop.setProperty("accuracy", "100");
            prop.setProperty("clip", "25");
            prop.setProperty("clipSize", "25");
            prop.setProperty("damage", "25");
            prop.setProperty("oneHanded", "false");
            prop.setProperty("fireRate", "0.25f");
            prop.setProperty("reloadSpeed", "0.5f");
            prop.setProperty("speed", "15");
            prop.setProperty("gunWalking", "machineGunWalkingAtlas");
            prop.setProperty("gunReloading", "machineGunReloadingAtlas");
            prop.setProperty("gunShooting", "machineGunShootingAtlas");
            prop.setProperty("spriteImg", "machineGunSprite");
            prop.setProperty("gunShotSound", "gunShot");
            prop.setProperty("reloadSoundEffect", "otherReloadEffect");

            //Item

            //Enemy

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
