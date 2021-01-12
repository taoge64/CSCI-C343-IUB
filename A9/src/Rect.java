public class Rect {
    private int xmin, ymin, xmax, ymax;

    Rect(int xmin, int ymin, int xmax, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    int getXmin() {
        return xmin;
    }

    int getYmin() {
        return ymin;
    }

    int getXmax() {
        return xmax;
    }

    int getYmax() {
        return ymax;
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the left portion.
     */
    Rect leftOf(Point p) {
        return new Rect(xmin,ymin,p.getX(),ymax);
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the right portion.
     */
    Rect rightOf(Point p) {
        return new Rect(p.getX(),ymin,xmax,ymax);
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the below portion.
     */
    Rect underOf(Point p) {
        return new Rect(xmin,ymin,xmax,p.getY());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the above portion.
     */
    Rect aboveOf(Point p) {
        return new Rect(xmin,p.getY(),xmax,ymax);
    }

    /*
     * Checks if the given point is contained in the rectangle. A
     * point lying on the edge counts as being contained in the
     * rectangle.
     */
    boolean contains(Point p) {
        int x=p.getX();
        int y=p.getY();
        if ((x<=this.getXmax())&&(x>=this.getXmin())&&(y<=this.getYmax())&&(y>=this.getYmin())){
            return true;
        }
        else{return false;}
    }

    /*
     * Checks if this rectangle intersects the given rectangle.
     */
    boolean intersect(Rect r) {
        if (this.contains(new Point(r.xmax,r.ymax))||this.contains(new Point(r.xmax,r.ymin))||
        this.contains(new Point(r.xmin,r.ymax))||this.contains(new Point(r.xmin,r.ymin))||r.contains(new Point(this.xmax,this.ymax))||r.contains(new Point(this.xmax,this.ymin))||
                r.contains(new Point(this.xmin,this.ymax))||r.contains(new Point(this.xmin,this.ymax))){
            return true;
        }
        else{return false;}
    }

    /*
     * Calculates the shortest distance from the given point to the
     * current rectangle. If the point is contained in the rectangle,
     * the distance is 0.
     */
    int distanceSquaredTo(Point p) {
        double result=-1;
        if (contains(p)){
            result= 0;
        }
        else if (p.getX() < getXmin()) {
                if (p.getY() <getYmin()) {
                    result = Math.pow(getXmin()-p.getX(),2)+Math.pow(getYmin()-p.getY(),2);
                } if (p.getY() > getYmax()) {
                    result = Math.pow(getXmin()-p.getX(),2)+Math.pow(getYmax()-p.getY(),2);
                } if(p.getY()>=getYmin()&&p.getY()<=getYmax()) {
                    result = Math.pow(getXmin()-p.getX(),2);
                }
            }
            else if (p.getX() > getXmax()) {
                if (p.getY() < getYmin()) {
                    result = Math.pow(p.getX()-getXmax(),2)+Math.pow(p.getY()-getYmin(),2);
                } else if (p.getY() > getYmax()) {
                    result = Math.pow(p.getX()-getXmax(),2)+Math.pow(p.getY()-getYmax(),2);
                } else {
                    result = Math.pow(p.getX()-getXmax(),2);
                }
            }
            else if ((p.getX() < getXmax()) && (p.getX() > getXmin())) {
                if (p.getY() < getYmin()) {
                    result = Math.pow(p.getY()-getYmin(),2);
                } else {
                    result = Math.pow(p.getY()-getYmax(),2);
                }
            }
        return (int)result;
    }

    public boolean equals(Object o) {
        if (o instanceof Rect) {
            Rect other = (Rect) o;
            return xmin == other.xmin && xmax == other.xmax &&
                    ymin == other.ymin && ymax == other.ymax;
        } else return false;
    }

    public String toString() {
        return String.format("R[(%d,%d)--(%d,%d)]", xmin, ymin, xmax, ymax);
    }
}
