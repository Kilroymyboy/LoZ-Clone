package launcher;


import java.util.ArrayList;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMap;

public class GameLoop extends BasicGameState {

    private int ID;
    private GameMap map;
    private Player player;

    public GameLoop(int ID) {
        this.ID = ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stb) throws SlickException {
        map = new GameMap();
        player = new Player();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException {
        g.scale(Constants.GAMESCALE.getValue(), Constants.GAMESCALE.getValue());
        map.renderBase();
        player.render();
        map.renderAbove();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException {

        Input input = gc.getInput();

        player.update(delta, map.getX(), map.getY());

        if (player.canMove()) {

            if (input.isKeyDown(Input.KEY_W)) {
                player.moveUp();
                map.move(0,delta * .1f); //increase the Y coordinates of bucky (move him up)

                if (map.getTileId(player.getUpDownHitbox().x, player.getUpDownHitbox().y - 2, map.collisionIndex()) != 0 ||
                        map.getTileId(player.getUpDownHitbox().x + 1, player.getUpDownHitbox().y - 2, map.collisionIndex()) != 0) {
                    map.move(0, -delta * .1f); //collition detection
                }

            }

            if (input.isKeyDown(Input.KEY_S)) {
                player.moveDown();
                map.move(0, -delta * .1f);

                if (map.getTileId(player.getUpDownHitbox().x, player.getUpDownHitbox().y, map.collisionIndex()) != 0 ||
                        map.getTileId(player.getUpDownHitbox().x + 1, player.getUpDownHitbox().y, map.collisionIndex()) != 0) {
                    map.move(0, delta * .1f); //collition detection
                }
            }

            if (input.isKeyDown(Input.KEY_A)) {
                player.moveLeft();
                map.move(delta * .1f,0);

                if (map.getTileId(player.getLeftHitbox().x, player.getLeftHitbox().y, map.collisionIndex()) != 0) {
                    map.move(-delta * .1f,0);
                }
            }

            if (input.isKeyDown(Input.KEY_D)) {
                player.moveRight();
                map.move(-delta * .1f,0);

                if (map.getTileId(player.getRightHitbox().x + 2, player.getRightHitbox().y, map.collisionIndex()) != 0) {
                    map.move(delta * .1f,0); //collition detection
                }
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    public void keyReleased(int key, char c)
    {
        if (key == Constants.RIGHT.getValue())
        {
            player.lookRight();
        }
        else if (key == Constants.LEFT.getValue())
        {
            player.lookLeft();
        }
        else if (key == Constants.DOWN.getValue())
        {
            player.lookDown();
        }
        else if (key == Constants.UP.getValue())
        {
            player.lookUp();
        }
    }
}
