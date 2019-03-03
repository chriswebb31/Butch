package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.NewGameScreen;

public class CutsSceneScreen extends NewGameScreen {
private ButchGame game;
private Stage stage;
private Viewport viewport;
    private Stage _UIStage;
    private Viewport _UIViewport;
    private Actor _followingActor;
    private boolean _isCameraFixed = true;
    private Dialog _messageBoxUI;
    private Label _label;
    private Image _transitionImage;
    public CutsSceneScreen(ButchGame game, FitViewport gameViewPort) {
        super(1,game, gameViewPort);
    }

}
