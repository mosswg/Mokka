package Mokka;

import Mokka.Internal.Keys;
import Mokka.Maths.Matrix4f;
import Mokka.Maths.Vector2f;
import Mokka.Shape.*;
import Mokka.Maths.Vector3f;

public class PlayerController2D implements MovementController2D {
    Vector3f acceleration = new Vector3f();
    public Vector3f velocity = new Vector3f();
    public float speed;
    public float friction;
    public float maxSpeed;
    boolean isActive;

    public PlayerController2D(float speed) {
        this.speed = speed;
        this.friction = speed / 5;
        this.maxSpeed = speed * 2;
    }

    public PlayerController2D(float speed, float friction) {
        this.speed = speed;
        this.friction = friction;
        this.maxSpeed = speed * 2;
    }

    public PlayerController2D(float speed, float friction, float maxSpeed) {
        this.speed = speed;
        this.friction = friction;
        this.maxSpeed = maxSpeed;
    }



    @Override
    public void Move(AbstractShape shape) {

        acceleration = new Vector3f();

        acceleration.x = ((Internal.keyIsPressedOrHeld(Keys.W) ? speed : 0) + (Internal.keyIsPressedOrHeld(Keys.RIGHT) ? speed : 0)) - ((Internal.keyIsPressedOrHeld(Keys.A) ? speed : 0) + (Internal.keyIsPressedOrHeld(Keys.LEFT) ? speed : 0));

        acceleration.y = ((Internal.keyIsPressedOrHeld(Keys.W) ? speed : 0) + (Internal.keyIsPressedOrHeld(Keys.UP) ? speed : 0)) - ((Internal.keyIsPressedOrHeld(Keys.S) ? speed : 0) + (Internal.keyIsPressedOrHeld(Keys.DOWN) ? speed : 0));


        acceleration.normalize();

        if (!acceleration.isZero() && isActive) {
            velocity.x += acceleration.x;
            velocity.y += acceleration.y;
            velocity.clampabs(maxSpeed);
        }

        shape.translate(velocity);

        velocity = velocity.moveToward(new Vector3f(), friction);
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void toggleActive() {
        isActive = !isActive;
    }

    @Override
    public MovementController2D copy() {
        PlayerController2D out = new PlayerController2D(speed, friction, maxSpeed);
        out.velocity = this.velocity;
        out.acceleration = this.acceleration;
        return out;
    }

    private void MoveCamera(AbstractShape shape) {
        if (shape.getClass() == Rect.class) {
            Rect rect = ((Rect) shape);
            float height = Window.getHeight() + Window.getCameraY();
            float bottom = Window.getCameraY();
            float left = Window.getCameraX();
            float width = Window.getWidth() + Window.getCameraX();
            Matrix4f translation = rect.getTranslation();

            float x = translation.getData(3).x + rect.getPos().x;
            float y = translation.getData(3).y + rect.getPos().y;
            Vector2f size = rect.getSize();
            if ((x + size.x * 2) > width) {
                Window.translateCamera(-(width - (x + size.x*2))/25, 0, 0);
            }
            else if ((x - size.x * 2) < left) {
                Window.translateCamera(-(left - (x - size.x*2))/25, 0, 0);
            }

            if ((y + size.y * 2) > height) {
                Window.translateCamera(0, -(height - (y + size.y * 2))/25, 0);
            }
            else if ((y - size.y * 2) < bottom) {
                Window.translateCamera(0, -(bottom - (y - size.y * 2))/25, 0);
            }
        }
    }
}
