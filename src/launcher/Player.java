package launcher;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity
{

    private Animation wUp, wDown, wLeft, wRight;
    private Animation lUp, lDown, lLeft, lRight;
    private boolean canMove;
    private SpriteSheet playerSheet;

    Player()  throws SlickException
    {
        super();

        wDown = setAnimation("res/sprite/LoZ Dwalk.png", 16, 24, 100, true);
        wUp = setAnimation("res/sprite/LoZ Uwalk.png", 16, 24, 100, true);
        wRight = setAnimation("res/sprite/LoZ Rwalk.png", 16, 24, 100, true);
        wLeft = setAnimation("res/sprite/LoZ Lwalk.png", 16, 24, 100, true);

        lDown = setAnimation("res/sprite/LoZ Dlook.png", 16, 24, 10000, false);
        lDown.setLooping(false);
        lRight = setAnimation("res/sprite/LoZ Rlook.png", 16, 24, 10000, false);
        lRight.setLooping(false);
        lUp = setAnimation("res/sprite/LoZ Ulook.png", 16, 24, 10000, false);
        lUp.setLooping(false);
        lLeft = setAnimation("res/sprite/LoZ Llook.png", 16, 24, 10000, false);
        lLeft.setLooping(false);

        canMove = true;
        curAnim = lDown;
        xPos = 200;
        yPos = 150;
    }


    private Animation setAnimation (String location, int width, int height, int duration, boolean pingPong) throws SlickException
    {
        Animation anim;
        playerSheet = new SpriteSheet(location, width, height);
        anim = new Animation(playerSheet, duration);
        anim.setPingPong(pingPong);
        return anim;
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
