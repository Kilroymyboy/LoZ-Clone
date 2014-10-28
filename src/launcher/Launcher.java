package launcher;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Launcher extends StateBasedGame{
   
   public static final String gamename = "LoZ Clone";
   public static final int menu = 0;
   public static final int play = 1;
   
   public Launcher(String gamename){
      super(gamename);
      this.addState(new Menu(menu));
      this.addState(new Play(play));
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
      this.getState(menu).init(gc, this);
      this.getState(play).init(gc, this);
      this.enterState(menu);
      gc.setTargetFrameRate(60);
      gc.setSmoothDeltas(true);
  	gc.setAlwaysRender(true);
   }
   
   public static void main(String[] args) {
      AppGameContainer appgc;
      try{
         appgc = new AppGameContainer(new Launcher(gamename));
         appgc.setDisplayMode(800, 600, false);
         appgc.start();
      }catch(SlickException e){
         e.printStackTrace();
      }
   }

}