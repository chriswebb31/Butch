package com.butch.game.dialouge;
//import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
public class DialougeBox extends Table{
    private String targetText ="";
    private float animTimer =0.0f;
    private float animationTotalTime = 0.0f;
    private float time_per_character = 0.05f;
    private STATE state = STATE.IDLE;
    private Label textLabel;


    private enum STATE{
        ANIMATING,
        IDLE,
        ;
    }

    public DialougeBox(){
        //super(skin);
       // this.setBackground("dialoguebox");
        textLabel = new Label(String.format("\n"),  new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        this.add(textLabel).expand().align(Align.left).pad(5f);
    }
    public void animateText(String text){
        targetText = text;
        animationTotalTime =text.length()*time_per_character;
        state= STATE.ANIMATING;
        animTimer=0f;
    }
    public Boolean isFinished(){
        if (state==STATE.IDLE ){
            return true;
        }
        else{
            return false;
        }
    }
    private void setText(String text){
        if(!text.contains("\n")){
            text+= "\n";
        }
        this.textLabel.setText(text);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (state==STATE.ANIMATING){
            animTimer+= delta;
            if (animTimer> animationTotalTime){
                state = STATE.IDLE;
                animTimer= animationTotalTime;
            }
            String actuallyDisplayedText ="";
            int charactersToDisplay= (int)((animTimer/animationTotalTime)*targetText.length());
            for (int i =0;i<charactersToDisplay;i++) {
                actuallyDisplayedText += targetText.charAt(i);
            }
            if(!actuallyDisplayedText.equals(textLabel.getText().toString())){
                setText(actuallyDisplayedText);
            }
        }
    }

    @Override
    public float getPrefWidth(){
        return 200f;
    }
}
