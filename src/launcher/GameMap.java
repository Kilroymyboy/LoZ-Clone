package launcher;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class GameMap extends TiledMap
{
    private int cLayer;
    private int topLayer;
    private int objectsLayer;
    private int backgroundLayer;
    private float mapOffsetX;
    private float mapOffsetY;
    private ArrayList<Rectangle> collisions;
    GameMap()throws SlickException
    {
        super(Constants.MAPLOCATION.getText());
        cLayer = this.getLayerIndex(Constants.MAPCOLLISION.getText());
        topLayer = this.getLayerIndex(Constants.MAPTOPLAYER.getText());
        objectsLayer = this.getLayerIndex(Constants.MAPOBJECTS.getText());
        backgroundLayer = this.getLayerIndex(Constants.MAPBACKGROUND.getText());
        collisions = new ArrayList<Rectangle>();
        mapOffsetX = -200;
        mapOffsetY = -200;


        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getTileId(x, y, cLayer) == 461) {
                    collisions.add(new Rectangle((x * 8), (y * 8), 8, 8));
                }
            }
        }

    }

    public void renderBase()
    {
        this.render((int) mapOffsetX, (int) mapOffsetY, backgroundLayer);
        this.render((int) mapOffsetX, (int) mapOffsetY, cLayer);
        this.render((int) mapOffsetX, (int) mapOffsetY, objectsLayer);
    }

    public void renderAbove()
    {
        this.render((int) mapOffsetX, (int) mapOffsetY, topLayer);
    }

    public float getX()
    {
        return mapOffsetX;
    }
    public float getY()
    {
        return mapOffsetY;
    }

    public void move(float newX, float newY)
    {
        mapOffsetX += newX;
        mapOffsetY += newY;
    }

    public int collisionIndex()
    {
        return cLayer;
    }

}
