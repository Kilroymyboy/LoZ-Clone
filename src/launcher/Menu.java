package launcher;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Menu extends BasicGameState
{
	private int ID;

	public Menu(int ID) 
	{
		this.ID = ID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame stb) throws SlickException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stb, Graphics g) throws SlickException 
	{

	}

	@Override
	public void update(GameContainer gc, StateBasedGame stb, int delta) throws SlickException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() 
	{
		return ID;
	}

	
}
