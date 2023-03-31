package com.eindopdracht.game.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.eindopdracht.game.control.Handler;

public class InputHandler {

    private Handler handler;

    public InputHandler(Handler handler) {
        this.handler = handler;
    }

    //TODO: controls
    public float getJoystickPosition() {
        Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        float screenWidth = handler.hub.getViewport().getScreenWidth();
        float screenHeight = handler.hub.getViewport().getScreenHeight();
        Vector2 screenMiddle = new Vector2(screenWidth / 2, screenHeight / 2);

        Vector2 mousePositionRelativeToMiddle = new Vector2(
                mousePosition.x - screenMiddle.x,
                (mousePosition.y - screenMiddle.y)
        );

        System.out.println(mousePositionRelativeToMiddle);

        float angleRadians = (float) (Math.atan2(mousePositionRelativeToMiddle.y, mousePositionRelativeToMiddle.x));

        return angleRadians;
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
                return Gdx.input.isKeyPressed(Input.Keys.SPACE);
            default:
                return false;
        }
    }
}
