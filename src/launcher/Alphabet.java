package launcher;

import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Alphabet 
{
	private SpriteSheet upperCase;
	private SpriteSheet lowerCase;
	private SpriteSheet punct;
	private SpriteSheet numbers;
	private Graphics g;


	public Alphabet(Graphics g) throws SlickException 
	{

		this.g = g;
		upperCase = new SpriteSheet("res/font/upper.png", 8, 16);
		lowerCase = new SpriteSheet("res/font/lower.png", 8, 16);
		punct = new SpriteSheet("res/font/punct.png", 8, 16);
		numbers = new SpriteSheet("res/font/number.png", 8, 16);
	}

	/**
	 * @return the charImage
	 */
	public void printString(String stringToPrint, int coordX, int coordY) 
	{
		char[] arrayToPrint = stringToPrint.toCharArray();
		int counter = 0;
		int curChar;
		int printLocX = coordX;
		int printLocY = coordY;
		Image charToPrint;


		while (arrayToPrint.length > counter)
		{	
			curChar = (int)arrayToPrint[counter];
			if (curChar != (int)' ')
			{

				if (printLocX > 760)
				{
					printLocX = coordX;
					printLocY += 20; // push it down row + 4 pixels
					System.out.println("down a line");
				}
				else if (printLocX < 40)  // edge of screen
				{
					printLocX = 40;
				}

				if (printLocY < 40 || printLocY > 560) // edge of screen
				{
					printLocY = coordY;
					System.out.println("Y print too far");
				}



				if (curChar >= 65 && curChar <= 90) // Uppercase
				{
					curChar -= 65; // set its 0 location to Uppercase A
					charToPrint = upperCase.getSprite(curChar, 0).getScaledCopy(2f);
					g.drawImage(charToPrint, printLocX, printLocY);
				}
			}
			counter++;
			printLocX += 16;
		}
		// TODO fill rest of images
	}
}

