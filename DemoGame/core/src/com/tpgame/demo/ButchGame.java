package com.tpgame.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.tpgame.demo.screens.PlayScreen;

public class ButchGame extends Game {
	private Music townMusic;
	@Override
	public void create () {

		setScreen(new PlayScreen());
        townMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Town1.mp3"));
        townMusic.setLooping(true);
        townMusic.setVolume(0.2f);
        townMusic.play();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public  void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}
