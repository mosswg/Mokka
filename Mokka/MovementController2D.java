package Mokka;

import Mokka.Shape.AbstractShape;

public interface MovementController2D {

    public void move(AbstractShape Pos);

    public void setActive(boolean active);

    public void toggleActive();

    public MovementController2D copy();
}
