package launcher;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState
{
	private int ID;
	private Image smallCursor;
	private SpriteSheet cursorSpriteSheet;
	private Animation cursor;
	private int cursorLocation;
	private Image[] nameArray;
	private Graphics g;
	private Image background;
	private Alphabet alphabetOutput;
	private String stringToPrint;
	private NameEnterArray nameEnterer;
	
	
	
	private int mX;
	private int mY;


	public Menu(int ID) 
	{
		this.ID = ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stb) throws SlickException 
	{
		background = new Image("res/map/Tilesets/TitleScreen.png");
		smallCursor = new Image("res/sprite/FairyCursor.png");
		cursorLocation = 0;
		nameArray = new Image[8]; // 8 characters long
		stringToPrint = "";
		

		cursorSpriteSheet = new SpriteSheet(smallCursor.getScaledCopy(2f), 32, 32);
		cursor = new Animation(cursorSpriteSheet, 300);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException 
	{
		nameEnterer = new NameEnterArray(this);
		alphabetOutput = new Alphabet(g);
		this.g = g;
		g.drawImage(background, 0, 0);
		g.drawString("X:" + mX + " Y:" + mY, 600, 20);
		cursor.draw (80 + (60*cursorLocation), 267);
		alphabetOutput.printString(stringToPrint, 105, 67);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException 
	{
		Input input = gc.getInput();
		cursor.update(delta);
		
		mX = input.getMouseX();
		mY = input.getMouseY();
		
		if(input.isKeyDown(Input.KEY_UP))
		{
	
		}

		if(input.isKeyDown(Input.KEY_DOWN))
		{
			stringToPrint = "TESTING MY PRINT STREAM";
		}

		if(input.isKeyPressed(Input.KEY_LEFT))
		{
			cursorLocation--;
		}

		if(input.isKeyPressed(Input.KEY_RIGHT))
		{
			cursorLocation++;
		}

		if (cursorLocation > 9)
			cursorLocation = 0;
		else if (cursorLocation < 0)
			cursorLocation = 9;

	}

	@Override
	public int getID() 
	{
		return ID;
	}


	public void setStringToPrint(String stringToPrint) {
		this.stringToPrint = stringToPrint;
	}

	public String getStringToPrint() {
		return stringToPrint;
	}


}
