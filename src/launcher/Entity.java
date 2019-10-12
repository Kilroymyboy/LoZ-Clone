package launcher;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Entity
{
    protected Animation curAnim;
    protected int xPos, yPos;
    protected int HitBoxUpDownX;
    protected int HitBoxUpDownY;
    protected int HitBoxLeftX;
    protected int HitBoxLeftY;
    protected int HitBoxRightX;
    protected int HitBoxRightY;

    protected Animation wUp, wDown, wLeft, wRight;
    protected Animation lUp, lDown, lLeft, lRight;
    private SpriteSheet spriteSheet;

    Entity(String entity) throws SlickException
    {
        wDown = setAnimation("res/sprite/" + entity +" Dwalk.png", 16, 24, 100, true);
        wUp = setAnimation("res/sprite/" + entity +" Uwalk.png", 16, 24, 100, true);
        wRight = setAnimation("res/sprite/" + entity +" Rwalk.png", 16, 24, 100, true);
        wLeft = setAnimation("res/sprite/" + entity +" Lwalk.png", 16, 24, 100, true);

        lDown = setAnimation("res/sprite/" + entity +" Dlook.png", 16, 24, 10000, false);
        lDown.setLooping(false);
        lRight = setAnimation("res/sprite/" + entity +" Rlook.png", 16, 24, 10000, false);
        lRight.setLooping(false);
        lUp = setAnimation("res/sprite/" + entity +" Ulook.png", 16, 24, 10000, false);
        lUp.setLooping(false);
        lLeft = setAnimation("res/sprite/" + entity +" Llook.png", 16, 24, 10000, false);
        lLeft.setLooping(false);
    }

    public void render()
    {
        curAnim.draw(xPos, yPos);
    }

    public void update(int delta, float mapOffsetX, float mapOffsetY)
    {
        curAnim.update(delta);

        HitBoxUpDownX= (int) ((xPos+3 - mapOffsetX)/8);
        HitBoxUpDownY = (int) ((yPos+25 - mapOffsetY)/8);
        HitBoxRightX = (int) ((xPos+1 - mapOffsetX)/8);
        HitBoxRightY = (int) ((yPos+20 - mapOffsetY)/8);
        HitBoxLeftX = (int) ((xPos-1 - mapOffsetX)/8);
        HitBoxLeftY = (int) ((yPos+20 - mapOffsetY)/8);
    }

    public int getXHitbox()
    {
        return HitBoxUpDownX;
    }
    public int getYHitbox()
    {
        return HitBoxUpDownY;
    }
    public int getLXHitbox()
    {
        return HitBoxLeftX;
    }
    public int getLYHitbox()
    {
        return HitBoxLeftY;
    }
    public int getRXHitbox()
    {
        return HitBoxRightX;
    }
    public int getRYHitbox()
    {
        return HitBoxRightY;
    }

    private Animation setAnimation (String location, int width, int height, int duration, boolean pingPong) throws SlickException
    {
        Animation anim;
        spriteSheet = new SpriteSheet(location, width, height);
        anim = new Animation(spriteSheet, duration);
        anim.setPingPong(pingPong);
        return anim;
    }


}
