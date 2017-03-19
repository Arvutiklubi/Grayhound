import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Created by koala on 19.03.17.
 */
public class Player extends Rectangle {

    public double speed = 1.1;
    public double jump_power = -0.6;
    public double[] cVtarget = new double[]{0,0};
    public double[] cVcur = new double[]{0,0};

    public Player(double x_, double y_, double w_, double h_)
    {
        // initialize position
        super(x_, y_, w_, h_);
    }

    public void update(int delta, GameContainer container)
    {
        Input input = container.getInput();

        // Keypress event down
        if (input.isKeyDown(Input.KEY_A))
        {
            cVtarget[0] = -speed * delta;
        }

        if (input.isKeyDown(Input.KEY_D))
        {
           cVtarget[0] = speed * delta;
        }

        if (!input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A))
        {
            cVtarget[0] = 0;
        }

        if (input.isKeyPressed(Input.KEY_SPACE))
        {
            Vy = jump_power;
        }

        System.out.println(Vy);
        cVcur[0] = lerp(cVcur[0], cVtarget[0], 0.09 * delta);
        cVcur[1] = lerp(cVcur[1], cVtarget[1], 0.09 * delta);
        control(cVcur);

        super.update(delta, container);
    }

    private static float lerp(float a, float b, float t) {
        if (t < 0)
            return a;
        else if (t > 1)
            return b;
        else
            return a + t * (b - a);
    }

    private static double lerp(double a, double b, double t) {
        if (t < 0)
            return a;
        else if (t > 1)
            return b;
        else
            return a + t * (b - a);
    }
}
