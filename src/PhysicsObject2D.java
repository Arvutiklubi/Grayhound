import org.newdawn.slick.*;

public class PhysicsObject2D {
    public double x, y;
    public double Vx, Vy;
    public double Ax, Ay;
    public double cVx, cVy;
    public double cAx, cAy;
    public double gravity = 0.001f;

    public PhysicsObject2D(double x_, double y_)
    {
        x = x_; y = y_;
        Vx = 0; Vy = 0;
        Ax = 0; Ay = 0;
    }

    public void update(int Delta, GameContainer container)
    {

    }

    public void render(GameContainer container, Graphics g)
    {

    }

    public void physicsUpdate(int Delta)
    {

    }

    public double[] getDeltaPos(int Delta)
    {
        double dx = (Vx + cVx) * Delta + (Ax + cAx) * Math.pow(Delta, 2) / 2;
        double dy = (Vy + cVy) * Delta + (Ay + gravity + cAy) * Math.pow(Delta, 2) / 2;

        return new double[]{dx, dy};
    }

    public double[] getDeltaVel(int Delta)
    {

        double dVx = Ax * Delta;
        double dVy = (Ay + gravity) * Delta;

        return new double[]{dVx, dVy};

    }

    public void updatePos(double dpx, double dpy)
    {
        x += dpx;
        y += dpy;
    }

    public void updateVel(double dVx, double dVy)
    {
        Vx += dVx;
        Vy += dVy;

    }

    public void updatePos(double[] dpx)
    {
        x += dpx[0];
        y += dpx[1];
    }

    public void updateVel(double[] dVx)
    {
        Vx += dVx[0];
        Vy += dVx[1];
    }

    public void control(double[] cV)
    {
        cVx = cV[0];
        cVy = cV[1];
    }
}
