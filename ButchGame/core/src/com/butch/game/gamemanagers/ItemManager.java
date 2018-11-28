package com.butch.game.gamemanagers;

import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.weapons.MachineGun;
import com.butch.game.gameobjects.weapons.Colt;

import java.util.ArrayList;

public class ItemManager {
    static ArrayList<Gun> gunList;
    static ArrayList<Item> itemList;

    /*

    ALL ITEMS AND IDS
    0 : PistolAmmo
    1 : RifleAmmo
    2 : ShotgunAmmo
    3 : Gold
    4 : HealthPack
    10 : Colt
    11 : Machine Gun

     */

    public ItemManager(){
        gunList = new ArrayList<Gun>();
        itemList = new ArrayList<Item>();
        gunList.add(new MachineGun());
        gunList.add(new Colt());
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
