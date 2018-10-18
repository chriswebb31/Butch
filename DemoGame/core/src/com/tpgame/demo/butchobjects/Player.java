package com.tpgame.demo.butchobjects;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Sprite playerSprite; //player image
    private Vector2 playerPosition; //player position
    private  Vector2 playerVelocity; //player dir and speed
    private float playerSpeedH; //separate for debugging later
    private float playerSpeedV;
    public static InputManager inputManager;

    public Player(Sprite s, Vector2 p, InputManager inputManager){
        this.playerSprite = s;
        this.playerPosition =  p;
        this.playerVelocity = new Vector2(0,0);
        this.playerSpeedH = 30;
        this.playerSpeedV = 30;
        this.inputManager = inputManager;
        playerSprite.scale(2);
    }

    public float getPlayerSpeedH() {
        return playerSpeedH;
    }

    public float getPlayerSpeedV() {
        return playerSpeedV;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Vector2 getPlayerPosition() {
        return playerPosition;
    }

    public Vector2 getPlayerVelocity() {
        return playerVelocity;
    }

    public void setPlayerPosition(Vector2 playerPosition) {
        this.playerPosition = playerPosition;
    }

    public void setPlayerSpeedH(float playerSpeedH) {
        this.playerSpeedH = playerSpeedH;
    }

    public void setPlayerSpeedV(float playerSpeedV) {
        this.playerSpeedV = playerSpeedV;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public void setPlayerVelocity(Vector2 playerVelocity) {
        this.playerVelocity = playerVelocity;
    }

    public void update(float delta){
        if(this.inputManager.verticalAxis > 0){
            this.playerVelocity.y = playerSpeedV * delta;
        }
        else if(this.inputManager.verticalAxis < 0){
            this.playerVelocity.y = -playerSpeedV * delta;
        }
        else{
            this.playerVelocity.y = 0;
        }
        if(this.inputManager.horizontalAxis > 0){
            this.playerVelocity.x = playerSpeedH * delta;
        }
        else if(this.inputManager.horizontalAxis < 0){
            this.playerVelocity.x = -playerSpeedH * delta;
        }
        else{
            this.playerVelocity.x = 0;
        }

        System.out.println(this.playerVelocity.x + " " + this.playerVelocity.y);

        this.playerPosition.x = (this.playerPosition.x + this.playerVelocity.x);
        this.playerPosition.y = (this.playerPosition.y + this.playerVelocity.y);
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(this.playerSprite.getTexture(), this.playerPosition.x, this.playerPosition.y);
    }
}
