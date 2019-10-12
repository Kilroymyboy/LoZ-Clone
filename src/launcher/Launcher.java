package launcher;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Launcher extends StateBasedGame{
   
   public static final String gamename = Constants.GAMENAME.getText();
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
      this.enterState(play);
      gc.setTargetFrameRate(60);
      gc.setSmoothDeltas(true);
  	gc.setAlwaysRender(true);
   }
   
   public static void main(String[] args) {
      AppGameContainer appgc;
      try{
         appgc = new AppGameContainer(new Launcher(gamename));
         appgc.setDisplayMode(Constants.DISPLAYWIDTH.getValue(), Constants.DISPLAYHEIGHT.getValue(), false);
         appgc.start();
      }catch(SlickException e){
         e.printStackTrace();
      }
   }

}