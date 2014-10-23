package launcher;


import java.util.ArrayList;
import java.awt.Rectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState{

	private int ID;
	private TiledMap map;
	private SpriteSheet playerSheet;
	private Animation player, wUp, wDown, wLeft, wRight;
	private Animation lUp, lDown, lLeft, lRight;
	private ArrayList<Rectangle> collisions;
	private int cLayer;
	private float mapX;
	private float mapY;
	private int playerX;
	private int playerY;
	private Rectangle playerBox;
	private boolean canMove;
	private Shape playerBoundingBox;

	public Play(int ID) 
	{
		this.ID = ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stb) throws SlickException 
	{
		map = new TiledMap("res/map/LoZmap.tmx");

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

		playerBoundingBox = new Shape() {

			@Override
			public Shape transform(Transform arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void createPoints() {
				// TODO Auto-generated method stub

			}
		};
		cLayer = map.getLayerIndex("collision");
		collisions = new ArrayList<Rectangle>();
		mapX = -100;
		mapY = -100;
		playerX = 200;
		playerY = 150;

		player = lDown;

		canMove = true;

		//		for (int y = 0; y < map.getHeight(); y++)
		//		{
		//			for (int x = 0; x < map.getWidth(); x++)
		//			{
		for (int y = 0; y < map.getHeight(); y++)
		{
			for (int x = 0; x < map.getWidth(); x++)
			{
				if (map.getTileId(x, y, cLayer) == 461)
				{
					collisions.add(new Rectangle((x*8), (y*8) ,8,8));
					return;
				}
			}
		}
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException 
	{
		g.scale(2f , 2f);
		map.render((int)mapX, (int)mapY);
		g.drawString("Players X: "+mapX*-1+"\nPlayers Y: "+mapY*-1, 100, 20); //indicator to see where bucky is in his world

		playerBox = new Rectangle((int)playerX, (int)playerY+16, 16, 8);

		g.drawRect((int)playerBox.getX(), (int)playerBox.getY(), (int)playerBox.getWidth(), (int)playerBox.getHeight());


		for(Rectangle collison : collisions)
		{
			g.drawRect((int)collison.getX() + (int)mapX, (int)collison.getY() + (int)mapY, (int)collison.getWidth(), (int)collison.getHeight());
		}
		player.draw(playerX, playerY);



	}

	@Override
	public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException
	{

		Input input = gc.getInput();
		player.update(delta);

		if (canMove)
		{

			if(input.isKeyDown(Input.KEY_W)){
				player = wUp; //change bucky to up image
				mapY += delta * .1f; //increase the Y coordinates of bucky (move him up)

				
				if(map.getTileId((int)mapX*-1 / 8,(int)mapY*-1 / 8, 0) != 461)
				{
					System.out.println(map.getTileId((int)mapX*-1 / 8, (int)mapY*-1 / 8, 0));
				}
				
//				for (Rectangle collison : collisions)
//				{
//					if(collison.intersects(playerBox))
//					{
//						mapY -= delta * .2f; //collition detection
//					}
//				}
			}

			if(input.isKeyDown(Input.KEY_S)){
				player = wDown;
				mapY -= delta * .1f;

				for (Rectangle collison : collisions)
				{
					if(playerBox.intersects(collison))
					{
						mapY += delta * .2f;
					}
				}
				//			if(mapY<-600){
				//				mapY += delta * .2f; //collition detection
				//			}
			}

			if(input.isKeyDown(Input.KEY_A))
			{
				player = wLeft;
				mapX += delta * .1f;

				for (Rectangle collison : collisions)
				{
					if(playerBox.intersects(collison))
					{
						mapX -= delta * .2f;
					}
				}
				//			if(mapX>324)
				//			{
				//				mapX -= delta * .2f;  //collition detection
				//			}
			}

			if(input.isKeyDown(Input.KEY_D))
			{
				player = wRight;
				mapX -= delta * .1f;

				for (Rectangle collison : collisions)
				{
					if(playerBox.intersects(collison))
					{
						mapX += delta * .2f;
					}
				}
				//			if(mapX<-840)
				//			{
				//				mapX += delta * .2f;  //collition detection
				//			}

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
			player = lRight;
			lRight.setCurrentFrame(0);
			lRight.start();
			break;

		case 30:
			player = lLeft;
			lLeft.setCurrentFrame(0);
			lLeft.start();
			break;

		case 31:
			player = lDown;
			lDown.setCurrentFrame(0);
			lDown.start();
			break;

		case 17:
			player = lUp;
			lUp.setCurrentFrame(0);
			lDown.start();
			break;
		}
	}
}
