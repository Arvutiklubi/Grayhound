import jdk.internal.util.xml.impl.Pair;

/**
 * Created by koala on 19.03.17.
 */
public class PhysicsObject2D {
    public double x, y;
    public double Vx, Vy;
    public double Ax, Ay;
    public double gravity;

    public PhysicsObject2D(float x_, float y_)
    {
        x = x_; y = y_;
        Vx = 0; Vy = 0;
        Ax = 0; Ay = 0;
    }

    public void update(int Delta)
    {
    }

    public double[] getDeltaPos(int Delta)
    {
        double dx = Vx * Delta + Ax * Math.pow(Delta, 2) / 2;
        double dy = Vy * Delta + Ax * Math.pow(Delta, 2) / 2;

        return new double[]{dx, dy};
    }

    public double[] getDeltaVel(int Delta)
    {

        double dVx = Ax * Delta;
        double dVy = Ay * Delta;

        return new double[]{dVx, dVy};

    }
}
