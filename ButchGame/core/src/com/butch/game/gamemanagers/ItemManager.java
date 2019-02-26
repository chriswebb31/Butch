package com.butch.game.gamemanagers;

import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.weapons.MachineGun;
import com.butch.game.gameobjects.weapons.Colt;
import com.butch.game.gameobjects.weapons.GunCreator;

import java.util.ArrayList;

public class ItemManager {
    static ArrayList<Gun> gunList;
    static ArrayList<Item> itemList;

    /*

    ALL ITEMS AND IDS
    0 : PistolAmmo
    1 : RifleAmmo
    2 : ShotgunAmmo
    3 : CoinItem
    4 : WhiskyItem
    10 : Colt
    11 : Machine Gun

    TYPES
    0 : Gun
    1 : Melee
    2 : Ammo
    3 : item

     */

    public ItemManager(){
        gunList = new ArrayList<Gun>();
        itemList = new ArrayList<Item>();

        gunList.add(new GunCreator("MachineGun"));
        gunList.add(new GunCreator("Revolver"));
        gunList.add(new GunCreator("Musket"));
        gunList.add(new GunCreator("Shotgun"));
    }

    public Gun getGun(int id){
        for (Gun gun:gunList) {
            if(gun.id == id){
                return gun;
            }
        }
        return null;
    }

    public Item getItem(int id){
        for (Item item:itemList) {
            if(item.id == id){
                return item;
            }
        }
        return null;
    }
}
