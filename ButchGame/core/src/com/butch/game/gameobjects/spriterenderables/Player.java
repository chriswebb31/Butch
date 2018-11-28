package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.Items.PistolAmmo;
import com.butch.game.gameobjects.Items.RifleAmmo;
import com.butch.game.gameobjects.Items.ShotgunAmmo;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.weapons.MachineGun;

import java.util.ArrayList;

public class Player extends Renderable {
    float xAxis, yAxis, speed = 0;

    public int rifleAmmo = 10;
    public int pistolAmmo = 10;
    public int shotgunAmmo = 10;

    private static ArrayList<Gun> gunInventory;
    private static ArrayList<ItemPickup> itemInventory;
    private static ArrayList<ItemPickup> itemCollection; //items in range if collection

    private Gun activeGun;
    private int gunInventoryIteration = 0;

    private ArrayList<Rectangle> mapColliders;
    private boolean canMove;
    private Vector2 velocity;

    private Vector2 leftHandIKoffset = new Vector2().setZero();
    private Vector2 rightHandIKoffset = new Vector2().setZero();

    private Rectangle intersector;

    public Player(Vector2 startPosition, ArrayList<Rectangle>mapStaticColliders){
        this.setPosition(startPosition);
        this.mapColliders = mapStaticColliders;
        this.TAG = "player";
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)));
        this.getSprite().setScale(10);

        this.velocity = new Vector2().setZero();
        this.canMove = true;
        this.speed = 10;

        this.gunInventory = new ArrayList<Gun>();
        this.itemInventory = new ArrayList<ItemPickup>();
        this.itemCollection = new ArrayList<ItemPickup>();
        this.gunInventory.add(new MachineGun());
//        this.gunInventory.add(new Colt());
        this.activeGun = this.gunInventory.get(0);

        for (Gun gun:gunInventory) {
            if(gun != activeGun){
                gun.activeForRender = false;
            }
        }

        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0); //how far away from sprite center is the left hand

        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
    }
    private void inputHandler() { // handle inputs
        if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 1;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            try{
                activeGun.Shoot();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            try{
                if(this.gunInventory.size() -1 > this.gunInventoryIteration){
                    this.activeGun.activeForRender = false;
                    this.gunInventoryIteration++;
                    this.activeGun = gunInventory.get(this.gunInventoryIteration);
                } else{
                    this.activeGun.activeForRender = false;
                    this.gunInventoryIteration = 0;
                    this.activeGun = gunInventory.get(this.gunInventoryIteration);
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            for (Renderable renderable:RenderableManager.renderableObjects) {
                if(renderable.TAG == "item" && renderable.activeForRender){
                    ItemPickup itemPickup = (ItemPickup) renderable;
                    intersector = new Rectangle();
                    if(Intersector.overlaps(itemPickup.collectionRange, this.getCollider())){
                        itemCollection.add(itemPickup);
                        itemPickup.activeForRender = false;
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void movementHandler() {
        if (canMove) { //if player isnt blocked
            if (yAxis > 0) {
                velocity.y = 1;
            } else if (yAxis < 0) {
                velocity.y = -1;
            } else {
                velocity.y = 0;
            }
            if (xAxis > 0) {
                velocity.x = 1;
            } else if (xAxis < 0) {
                velocity.x = -1;
            } else {
                velocity.x = 0;
            }
            this.getCollider().setCenter(this.getPosition().x + velocity.x * speed, this.getPosition().y + velocity.y * speed);

            for (Rectangle staticCollider: RenderableManager.mapColliders) {
                if(this.getCollider().overlaps(staticCollider)){
                    intersector = new Rectangle();
                    Intersector.intersectRectangles(this.getCollider(), staticCollider, intersector);
                    if(intersector.x > this.getCollider().x){
                        //Intersects with right side
                        velocity.x = clamp(velocity.x, -1, 0.001f);
                    }
                    else if(intersector.x + intersector.width < this.getCollider().x + this.getCollider().width) {
                        //Intersects with left side
                        velocity.x = clamp(velocity.x, 0.001f, 1);
                    }

                    if(intersector.y > this.getCollider().y){
                        //Intersects with top side
                        velocity.y = clamp(velocity.y, -1, 0.001f);
                    }
                    else if(intersector.y + intersector.height < this.getCollider().y + this.getCollider().height){
                        //intersects with bottom side
                        velocity.y = clamp(velocity.y, 0.001f, 1);
                    }
                }
            }

            for (Renderable renderable: RenderableManager.renderableObjects) {
                if(renderable.getCollider().overlaps(this.getCollider())){
                    if(renderable.TAG.equals("breakable")){
                        intersector = new Rectangle();
                        Intersector.intersectRectangles(this.getCollider(), renderable.getCollider(), intersector);
                        if(intersector.x > this.getCollider().x){
                            //Intersects with right side
                            velocity.x = clamp(velocity.x, -1, 0.001f);
                        }
                        else if(intersector.x + intersector.width < this.getCollider().x + this.getCollider().width) {
                            //Intersects with left side
                            velocity.x = clamp(velocity.x, 0.001f, 1);
                        }

                        if(intersector.y > this.getCollider().y){
                            //Intersects with top side
                            velocity.y = clamp(velocity.y, -1, 0.001f);
                        }
                        else if(intersector.y + intersector.height < this.getCollider().y + this.getCollider().height){
                            //intersects with bottom side
                            velocity.y = clamp(velocity.y, 0.001f, 1);
                        }
                    }
                }
            } //Loop activeForRender breakables
            System.out.println("Velocity: " + velocity);

            //interpolate this for clean movement
            this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        }
    }

    private void flipHandler() {
        try{
            if (ButchGame.mousePosition().x >= this.getPosition().x) { // if direction is right
                this.getSprite().setFlip(false, false);
                activeGun.getSprite().setFlip(false, false);
            } else { //if direction is left or not right
                this.getSprite().setFlip(true, false);
                activeGun.getSprite().setFlip(false, true); //
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public Vector2 getAimDirection() {
        /*
            Determining a Vector Given Two Points

            PQ→=(xQ−xP,yQ−yP)

         */
        Vector2 aimDirection = new Vector2(ButchGame.mousePosition().x - this.getPosition().x, ButchGame.mousePosition().y - this.getPosition().y);
        return aimDirection;
    }
    public Vector2 getWeaponPosition(){
        Vector2 pos;
        if(ButchGame.mousePosition().x >= getPosition().x){
            if(this.activeGun.oneHanded){
                pos = new Vector2(getPosition().x + leftHandIKoffset.x, getPosition().y + leftHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition());
            }
        }
        else{
            if(this.activeGun.oneHanded){
                pos = new Vector2(getPosition().x + rightHandIKoffset.x, getPosition().y + rightHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition());
            }
        }
        return pos;
    }

    public static float clamp(float val, float min, float max) { //simple clamp function
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public void update() {
        this.activeGun.player = this;
        this.activeGun.activeForRender = true;
        inputHandler();
        movementHandler();
        flipHandler();

        if(itemCollection.size() > 0){
            for(ItemPickup item:itemCollection){
                addItem(item);
            }
            itemCollection.clear();
        }

        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.activeGun.activeForRender = true;
    }

    @Override
    public void takeHit(float damage) {

    }

    public void addItem(ItemPickup item){
        switch (item.type){
            case 0:
                gunInventory.add(ButchGame.itemManager.getGun(item.id));
            case 1:
                itemInventory.add(ButchGame.itemManager.getItem(item.id));
            if(item.type == 2){
                System.out.println(item);
                System.out.println("type:"+ item.type);
                Item itemObj = (Item) item;
                System.out.println("AmmoCount:" +itemObj.quantity);
               switch (itemObj.id){
                   case 0:
                       PistolAmmo newPistolAmmo = (PistolAmmo) item;
                       this.pistolAmmo += newPistolAmmo.quantity;
                   case 1:
                       RifleAmmo newRifleAmmo = (RifleAmmo) item;
                       this.rifleAmmo += newRifleAmmo.quantity;
                   case 2:
                       ShotgunAmmo newShotgunAmmo = (ShotgunAmmo) item;
                       this.shotgunAmmo += newShotgunAmmo.quantity;
               }
               item.activeForRender = false;
            }
        }
    }
}
