package launcher;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity
{


    private boolean canMove;


    Player()  throws SlickException
    {
        super("LoZ");


        canMove = true;
        curAnim = lDown;
        pos.x = 200;
        pos.y = 150;
    }


    public boolean canMove()
    {
        return canMove;
    }

    public void moveUp()
    {
    curAnim = wUp;
    }
    public void moveDown()
    {
        curAnim = wDown;
    }
    public void moveLeft()
    {
        curAnim = wLeft;
    }
    public void moveRight()
    {
        curAnim = wRight;
    }

    public void lookUp()
    {
        lUp.restart();
        curAnim = lUp;
    }
    public void lookDown()
    {
        lDown.restart();
        curAnim = lDown;
    }
    public void lookLeft()
    {
        lLeft.restart();
        curAnim = lLeft;
    }
    public void lookRight()
    {
        lRight.restart();
        curAnim = lRight;
    }


}
