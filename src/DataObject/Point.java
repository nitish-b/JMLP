package DataObject;

public class Point implements Comparable<Point>
{
     double x;
     double y;
     int classifier;
     public double distance;

    Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    Point(double x, double y,int classifier)
    {
        this.x=x;
        this.y=y;
        this.classifier=classifier;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    @Override
    public int compareTo(Point o) {
        return this.distance < o.distance ? -1 : 1;
    }

    public int getClassifier()
    {
        return this.classifier;
    }

    public void setDistance(double distance)
    {
        this.distance=distance;
    }

    public double getDistance()
    {
        return this.distance;
    }
}