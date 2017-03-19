import org.newdawn.slick.*;

public class PhysicsObject2D {
    public double x, y;
    public double Vx, Vy;
    public double Ax, Ay;
    public double gravity;

    public PhysicsObject2D(double x_, double y_)
    {
        x = x_; y = y_;
        Vx = 0; Vy = 0;
        Ax = 0; Ay = 0;
    }

    public void update(int Delta)
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
        double dx = Vx * Delta + Ax * Math.pow(Delta, 2) / 2;
        double dy = Vy * Delta + (Ay + gravity) * Math.pow(Delta, 2) / 2;

        return new double[]{dx, dy};
    }

    public double[] getDeltaVel(int Delta)
    {

        double dVx = Ax * Delta;
        double dVy = Ay * Delta;

        return new double[]{dVx, dVy};

    }

    public void updatePos(double dpx, double dpy)
    {
        x += dpx;
        y += dpy;
    }

    public void updateVel(double dVx, double dVy)
    {
        x += dVx;
        y += dVy;

    }

    public void updatePos(double[] dpx)
    {
        x += dpx[0];
        y += dpx[1];
    }

    public void updateVel(double[] dVx)
    {
        x += dVx[0];
        y += dVx[1];
    }
}
