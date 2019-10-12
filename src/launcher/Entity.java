package launcher;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Entity
{
    protected Animation curAnim;
    protected Vector pos;
    protected Vector HitBoxUpDown;
    protected Vector HitBoxLeft;
    protected Vector HitBoxRight;

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

        pos = new Vector(0);
        HitBoxUpDown = new Vector(0);
        HitBoxRight = new Vector(0);
        HitBoxLeft = new Vector(0);
    }

    public void render()
    {
        curAnim.draw(pos.x, pos.y);
    }

    public void update(int delta, float mapOffsetX, float mapOffsetY)
    {
        curAnim.update(delta);

        HitBoxUpDown.set(
                (int) ((pos.x + 3 - mapOffsetX)/8),     //x
                (int) ((pos.y + 25 - mapOffsetY)/8));   //y
        HitBoxRight.set(
                (int) ((pos.x + 1 - mapOffsetX)/8),     //x
                (int) ((pos.y + 20 - mapOffsetY)/8));   //y
        HitBoxLeft.set(
                (int) ((pos.x - 1 - mapOffsetX)/8),     //x
                (int) ((pos.y + 20 - mapOffsetY)/8));   //y
    }

    public Vector getUpDownHitbox()
    {
        return HitBoxUpDown;
    }

    public Vector getLeftHitbox()
    {
        return HitBoxLeft;
    }

    public Vector getRightHitbox()
    {
        return HitBoxRight;
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
