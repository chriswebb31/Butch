package com.butch.game.gamemanagers;

import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.weapons.CHOPPER;
import com.butch.game.gameobjects.weapons.Pistol;

import java.util.ArrayList;

public class ItemManager {
    static ArrayList<Gun> gunList;
    static ArrayList<Item> itemList;

    public ItemManager(){
        gunList = new ArrayList<Gun>();
        itemList = new ArrayList<Item>();
        gunList.add(new CHOPPER());
        gunList.add(new Pistol());
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
