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
	private SpriteSheet playerSheet;
	private Animation player, wUp, wDown, wLeft, wRight;
	private Animation lUp, lDown, lLeft, lRight;
	private ArrayList<Rectangle> collisions;

	private int cLayer;
	private int topLayer;
	private int objectsLayer;
	private int backgroundLayer;

	private float mapOffsetX;
	private float mapOffsetY;
	private int playerCenterX;
	private int playerCenterY;
	private int HitBoxUpDownX;
	private int HitBoxUpDownY;
	private int HitBoxLeftX;
	private int HitBoxLeftY;
	private int HitBoxRightX;
	private int HitBoxRightY;
	private boolean canMove;

	public Play(int ID) 
	{
		this.ID = ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stb) throws SlickException 
	{
		map = new TiledMap("res/map/LoZmapTop.tmx");

		wDown = setAnimation("res/sprite/LoZ Dwalk.png", 16, 24, 100, true);
		wUp = setAnimation("res/sprite/LoZ Uwalk.png", 16, 24, 100, true);	
		wRight = setAnimation("res/sprite/LoZ Rwalk.png", 16, 24, 100, true);
		wLeft = setAnimation("res/sprite/LoZ Lwalk.png", 16, 24, 100, true);

		lDown = setAnimation("res/sprite/LoZ Dlook.png", 16, 24, 10000, false);
		lRight = setAnimation("res/sprite/LoZ Rlook.png", 16, 24, 10000, false);
		lUp = setAnimation("res/sprite/LoZ Ulook.png", 16, 24, 10000, false);
		lLeft = setAnimation("res/sprite/LoZ Llook.png", 16, 24, 10000, false);

		lDown.setLooping(false);
		lRight.setLooping(false);
		lUp.setLooping(false);
		lLeft.setLooping(false);

		cLayer = map.getLayerIndex("collision");
		topLayer = map.getLayerIndex("toplayer");
		objectsLayer = map.getLayerIndex("objects");
		backgroundLayer = map.getLayerIndex("background");

		collisions = new ArrayList<Rectangle>();
		mapOffsetX = -200;
		mapOffsetY = -200;
		playerCenterX = 200;
		playerCenterY = 150;
		player = lDown;

		canMove = true;

		
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
		player.draw(playerCenterX, playerCenterY);
		map.render((int)mapOffsetX, (int)mapOffsetY, topLayer);

		g.drawString("Players X: "+HitBoxUpDownX+"\nPlayers Y: "+HitBoxUpDownY, 100, 20); //indicator to see where the player is in his world

		//		for(Rectangle collison : collisions)
		//		{
		//			g.drawRect((int)collison.getX() + (int)mapX, (int)collison.getY() + (int)mapY, (int)collison.getWidth(), (int)collison.getHeight());
		//		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException
	{
		HitBoxUpDownX= (int) ((playerCenterX+3 - mapOffsetX)/8);
		HitBoxUpDownY = (int) ((playerCenterY+25 - mapOffsetY)/8);
		HitBoxRightX = (int) ((playerCenterX+1 - mapOffsetX)/8);
		HitBoxRightY = (int) ((playerCenterY+20 - mapOffsetY)/8);
		HitBoxLeftX = (int) ((playerCenterX-1 - mapOffsetX)/8);
		HitBoxLeftY = (int) ((playerCenterY+20 - mapOffsetY)/8);


		Input input = gc.getInput();

		player.update(delta);

		if (canMove)
		{

			if(input.isKeyDown(Input.KEY_W))
			{
				player = wUp; //change bucky to up image
				mapOffsetY += delta * .1f; //increase the Y coordinates of bucky (move him up)

				if(map.getTileId(HitBoxUpDownX, HitBoxUpDownY-2, cLayer) != 0 ||
						map.getTileId(HitBoxUpDownX+1, HitBoxUpDownY-2, cLayer) != 0)
				{
					mapOffsetY -= delta * .1f; //collition detection
				}

			}

			if(input.isKeyDown(Input.KEY_S)){
				player = wDown;
				mapOffsetY -= delta * .1f;

				if(map.getTileId(HitBoxUpDownX, HitBoxUpDownY, cLayer) != 0 ||
						map.getTileId(HitBoxUpDownX+1, HitBoxUpDownY, cLayer) != 0)
				{
					mapOffsetY += delta * .1f; //collition detection
				}
			}

			if(input.isKeyDown(Input.KEY_A))
			{
				player = wLeft;
				mapOffsetX += delta * .1f;

				if(map.getTileId(HitBoxLeftX, HitBoxLeftY, cLayer) != 0)
				{
					mapOffsetX -= delta * .1f; //collition detection
				}
			}

			if(input.isKeyDown(Input.KEY_D))
			{
				player = wRight;
				mapOffsetX -= delta * .1f;

				if(map.getTileId(HitBoxRightX+2, HitBoxRightY, cLayer) != 0)
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


	private Animation setAnimation (String location, int width, int height, int duration, boolean pingPong) throws SlickException
	{
		Animation anim;
		playerSheet = new SpriteSheet(location, width, height);
		anim = new Animation(playerSheet, duration);
		anim.setPingPong(pingPong);
		return anim;
	}

	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		case 32:
			lRight.restart();
			player = lRight;
			break;

		case 30:
			lLeft.restart();
			player = lLeft;
			break;

		case 31:
			lDown.restart();
			player = lDown;
			break;

		case 17:
			lUp.restart();
			player = lUp;
			break;
		}
	}


}
