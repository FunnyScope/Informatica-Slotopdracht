package com.eindopdracht.game.input;


import com.eindopdracht.game.EindOpdracht;

public class InputHandler {

    private EindOpdracht game;

    public InputHandler(EindOpdracht game) {
        this.game = game;
    }

    //TODO: controls
    public float getJoystickPosition() {


        return 0;
    }

    public boolean getButtonPressed(Button button) {
        switch (button) {
            case up:
                return false;
            case down:
                return false;
            case left:
                return false;

            case right:
                return false;

            case shoot:
                return false;

            default:
                return false;

        }
    }
}
