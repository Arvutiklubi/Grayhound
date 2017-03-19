import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame{
    private final boolean HOST = false;

    private static int screenW = 900;
    private static int screenH = 600;

    private ArrayList<PhysicsObject2D> gameObjects = new ArrayList<>();

    private Host host;
    private Client client;

    public Main (String appName) {super(appName);}

    @Override
    public void init(GameContainer container) throws SlickException {
        gameObjects.add(new Player(0,0,100, 100));
        gameObjects.add(new Player(150,200,100, 100));

        if (HOST) {
            host = new Host();
            host.setGameObjects(gameObjects);

            new Thread(host).start();
        } else {
            client = new Client();
            client.setGameObjects(gameObjects);
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (HOST) {
            for (int i = 0; i < gameObjects.size(); i++) {
                if (i != 1) {
                    gameObjects.get(i).update(delta, container);
                }
            }
        } else {
            new Thread(client).start();
            gameObjects.get(1).update(delta, container);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        for (PhysicsObject2D obj : gameObjects)
        {
            obj.render(container, g);
        }

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
