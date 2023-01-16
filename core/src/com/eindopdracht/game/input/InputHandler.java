package com.eindopdracht.game.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

    public InputHandler() {

    }

    //TODO: controls
    public float getJoystickPosition() {


        return 0;
    }

    public boolean getButtonPressed(Button button) {
        switch (button) {
            case up:
                return Gdx.input.isKeyPressed(Input.Keys.W);
            case down:
                return Gdx.input.isKeyPressed(Input.Keys.S);
            case left:
                return Gdx.input.isKeyPressed(Input.Keys.A);
            case right:
                return Gdx.input.isKeyPressed(Input.Keys.D);
            case shoot:
                return Gdx.input.isKeyPressed(Input.Keys.E);
            default:
                return false;
        }
    }
}
