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

public class Play extends BasicGameState{

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


	public Play(int ID) 
	{
		this.ID = ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stb) throws SlickException
	{
		map = new TiledMap("res/map/LoZmapTop.tmx");
		player = new Player();

		cLayer = map.getLayerIndex("collision");
		topLayer = map.getLayerIndex("toplayer");
		objectsLayer = map.getLayerIndex("objects");
		backgroundLayer = map.getLayerIndex("background");

		collisions = new ArrayList<Rectangle>();
		mapOffsetX = -200;
		mapOffsetY = -200;

		
		for (int y = 0; y < map.getHeight(); y++)
		{
			for (int x = 0; x < map.getWidth(); x++)
			{
				if (map.getTileId(x, y, cLayer) == 461)
				{
					collisions.add(new Rectangle((x*8), (y*8) ,8,8));
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException 
	{
		g.scale(2f , 2f);
		map.render((int)mapOffsetX, (int)mapOffsetY, backgroundLayer);
		map.render((int)mapOffsetX, (int)mapOffsetY, cLayer);
		map.render((int)mapOffsetX, (int)mapOffsetY, objectsLayer);
		player.render();
		map.render((int)mapOffsetX, (int)mapOffsetY, topLayer);

//		g.drawString("Players X: "+HitBoxUpDownX+"\nPlayers Y: "+HitBoxUpDownY, 100, 20); //indicator to see where the player is in his world

		//		for(Rectangle collison : collisions)
		//		{
		//			g.drawRect((int)collison.getX() + (int)mapX, (int)collison.getY() + (int)mapY, (int)collison.getWidth(), (int)collison.getHeight());
		//		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException
	{

		Input input = gc.getInput();

		player.update(delta, mapOffsetX, mapOffsetY);

		if (player.canMove())
		{

			if(input.isKeyDown(Input.KEY_W))
			{
				player.moveUp();
				mapOffsetY += delta * .1f; //increase the Y coordinates of bucky (move him up)

				if(map.getTileId(player.getXHitbox(), player.getYHitbox()-2, cLayer) != 0 ||
						map.getTileId(player.getXHitbox()+1, player.getYHitbox()-2, cLayer) != 0)
				{
					mapOffsetY -= delta * .1f; //collition detection
				}

			}

			if(input.isKeyDown(Input.KEY_S)){
				player.moveDown();
				mapOffsetY -= delta * .1f;

				if(map.getTileId(player.getXHitbox(), player.getYHitbox(), cLayer) != 0 ||
						map.getTileId(player.getXHitbox()+1, player.getYHitbox(), cLayer) != 0)
				{
					mapOffsetY += delta * .1f; //collition detection
				}
			}

			if(input.isKeyDown(Input.KEY_A))
			{
				player.moveLeft();
				mapOffsetX += delta * .1f;

				if(map.getTileId(player.getLXHitbox(), player.getLYHitbox(), cLayer) != 0)
				{
					mapOffsetX -= delta * .1f; //collition detection
				}
			}

			if(input.isKeyDown(Input.KEY_D))
			{
				player.moveRight();
				mapOffsetX -= delta * .1f;

				if(map.getTileId(player.getRXHitbox()+2, player.getRYHitbox(), cLayer) != 0)
				{
					mapOffsetX += delta * .1f; //collition detection
				}
			}
		}
	}

	@Override
	public int getID() 
	{
		return ID;
	}

	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		case 32:
			player.lookRight();
			break;

		case 30:
			player.lookLeft();
			break;

		case 31:
			player.lookDown();
			break;

		case 17:
			player.lookUp();
			break;
		}
	}


}
