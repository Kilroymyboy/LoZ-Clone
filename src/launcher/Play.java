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

public class Play extends BasicGameState {

    private int ID;
    private TiledMap map;
    private ArrayList<Rectangle> collisions;

    private int cLayer;
    private int topLayer;
    private int objectsLayer;
    private int backgroundLayer;
    private Player player;

    private float mapOffsetX;
    private float mapOffsetY;


    public Play(int ID) {
        this.ID = ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stb) throws SlickException {
        map = new TiledMap(Constants.MAPLOCATION.getText());
        player = new Player();

        cLayer = map.getLayerIndex(Constants.MAPCOLLISION.getText());
        topLayer = map.getLayerIndex(Constants.MAPTOPLAYER.getText());
        objectsLayer = map.getLayerIndex(Constants.MAPOBJECTS.getText());
        backgroundLayer = map.getLayerIndex(Constants.MAPBACKGROUND.getText());

        collisions = new ArrayList<Rectangle>();
        mapOffsetX = -200;
        mapOffsetY = -200;


        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.getTileId(x, y, cLayer) == 461) {
                    collisions.add(new Rectangle((x * 8), (y * 8), 8, 8));
                }
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException {
        g.scale(Constants.GAMESCALE.getValue(), Constants.GAMESCALE.getValue());
        map.render((int) mapOffsetX, (int) mapOffsetY, backgroundLayer);
        map.render((int) mapOffsetX, (int) mapOffsetY, cLayer);
        map.render((int) mapOffsetX, (int) mapOffsetY, objectsLayer);
        player.render();
        map.render((int) mapOffsetX, (int) mapOffsetY, topLayer);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException {

        Input input = gc.getInput();

        player.update(delta, mapOffsetX, mapOffsetY);

        if (player.canMove()) {

            if (input.isKeyDown(Input.KEY_W)) {
                player.moveUp();
                mapOffsetY += delta * .1f; //increase the Y coordinates of bucky (move him up)

                if (map.getTileId(player.getUpDownHitbox().x, player.getUpDownHitbox().y - 2, cLayer) != 0 ||
                        map.getTileId(player.getUpDownHitbox().x + 1, player.getUpDownHitbox().y - 2, cLayer) != 0) {
                    mapOffsetY -= delta * .1f; //collition detection
                }

            }

            if (input.isKeyDown(Input.KEY_S)) {
                player.moveDown();
                mapOffsetY -= delta * .1f;

                if (map.getTileId(player.getUpDownHitbox().x, player.getUpDownHitbox().y, cLayer) != 0 ||
                        map.getTileId(player.getUpDownHitbox().x + 1, player.getUpDownHitbox().y, cLayer) != 0) {
                    mapOffsetY += delta * .1f; //collition detection
                }
            }

            if (input.isKeyDown(Input.KEY_A)) {
                player.moveLeft();
                mapOffsetX += delta * .1f;

                if (map.getTileId(player.getLeftHitbox().x, player.getLeftHitbox().y, cLayer) != 0) {
                    mapOffsetX -= delta * .1f; //collition detection
                }
            }

            if (input.isKeyDown(Input.KEY_D)) {
                player.moveRight();
                mapOffsetX -= delta * .1f;

                if (map.getTileId(player.getRightHitbox().x + 2, player.getRightHitbox().y, cLayer) != 0) {
                    mapOffsetX += delta * .1f; //collition detection
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
