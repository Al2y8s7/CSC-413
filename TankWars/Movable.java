package TankWars;

import java.util.Observable;

/**
 *
 * @author Alnguye 3/21/18
 */
public class Movable extends GameObject {

    @Override
    public void collide(GameObject gameObject) {
    }

    @Override
    public void collide(Tank tank) {
    }

    @Override
    public void collide(Bullet bullet) {
    }

    @Override
    public void collide(BreakableWall breakableWall) {
    }

    @Override
    public void collide(PowerUp powerUp) {
    }

    @Override
    public void update(Observable o, Object obj) {
    }

    public void move(int x, int y) {

    }

    public void moveUp(int x) {

    }

    public void moveDown(int x) {

    }

    public void moveLeft(int y) {

    }

    public void moveRight(int y) {

    }

    public int getDirection(int x, int y) {
        return 0;
    }

    public void setDirection() {

    }

}
