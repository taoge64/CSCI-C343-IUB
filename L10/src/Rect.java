interface Rect {
    int getXmin();
    int getYmin();
    int getXmax();
    int getYmax();
    Rect leftOf(Point p);
    Rect rightOf(Point p);
    Rect underOf(Point p);
    Rect aboveOf(Point p);
    boolean contains(Point p);
    boolean intersect(Rect r);
    int distanceSquaredTo(Point p);
}
