import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame{
    private static int screenW = 900;
    private static int screenH = 600;

    public Main (String appName) {super(appName);}

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {

    }

    public static void main (String[] Args){
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main(""));
            appgc.setDisplayMode(screenW, screenH, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
