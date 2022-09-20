package Exercise_13._05;

public abstract class GeometricObject implements Comparable<GeometricObject> {
    public static GeometricObject max(GeometricObject gO1, GeometricObject gO2) {
       return gO1.compareTo(gO2) == 1 ? gO1 : gO2;
    }

    @Override
    public int compareTo(GeometricObject o) {
        return this.getArea() > o.getArea() ?  1 :
               this.getArea() < o.getArea() ? -1 : 0;
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}
