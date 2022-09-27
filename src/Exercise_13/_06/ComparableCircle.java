package Exercise_13._06;

import Exercise_10._11.Circle2D;

class ComparableCircle extends Circle2D implements Comparable<ComparableCircle> {
    /** Construct a ComparableRectangle with specified properties */
    public ComparableCircle(double radius) {
        super(radius);
    }

    @Override
    public int compareTo(ComparableCircle o) {
        if (this.getRadius() > o.getRadius())
            return 1;
        else if (this.getRadius() < o.getRadius())
            return -1;
        else
            return 0;
    }
}

