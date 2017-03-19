import org.newdawn.slick.*;
import java.util.ArrayList;

/**
 * Created by koala on 19.03.17.
 */
public class Rectangle extends PhysicsObject2D {

    public double w, h;
    public boolean isStatic;
    private ArrayList<Rectangle> dbgmap = new ArrayList<Rectangle>();

    public Rectangle(double x_,double y_,double w_,double h_) {
        super(x_, y_);
        this.w = w_;
        this.h = h_;
    }

    public void setStatic(boolean status)
    {
        isStatic = status;
    }

    public void update(int Delta, GameContainer container)
    {
    }

    public void physicsUpdate(int Delta, ArrayList<Rectangle> map, double[] dp, double[] dV) {
        if (isStatic) {
            return;
        }

        /*

        for (Rectangle other : map) {
            // Could be optimized but ehh.
            if (this.x < other.x + other.w && this.x + this.w > other.x)
            {
                dp[0] = 0;
                dV[0] = 0;
            }

            if ( this.y < other.y + other.h && this.h + this.y > other.y)
            {
                dp[1] = 0;
                dV[1] = 0;
            }
        }*/

        updatePos(dp);
        updateVel(dV);
    }

    public void render(GameContainer container, Graphics g)
    {
        g.setColor(Color.cyan);
        g.drawRect((int) x, (int) y, (int) w, (int) h);
    }


}
