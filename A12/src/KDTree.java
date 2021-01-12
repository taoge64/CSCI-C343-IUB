import java.util.*;

// -----

abstract class XTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static XTree makeXTree(List<Point> xPoints, List<Point> yPoints, int bound) {
        if (xPoints.size()<=bound){
            return new XEmpty();
        }
        else{
            List<Point> xleft= xPoints.subList(0,xPoints.size()/2);
            List<Point> xright= xPoints.subList(xPoints.size()/2+1,xPoints.size());
            List<Point> yleft= new ArrayList<Point>();
            List<Point> yright= new ArrayList<Point>();
            for (Point i : yPoints){
                if (xleft.contains(i)){
                    yleft.add(i);
                }
                if(xright.contains(i)){
                    yright.add(i);
                }
            }

            return new XNode(xPoints.get(xPoints.size()/2),YTree.makeYTree(yleft,xleft,bound),YTree.makeYTree(yright,xright,bound));
        }
    }
}

abstract class YTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static YTree makeYTree(List<Point> yPoints, List<Point> xPoints, int bound) {

        if (yPoints.size()<=bound){
            return new YEmpty();
        }
        else{
            List<Point> yleft= yPoints.subList(0,yPoints.size()/2);
            List<Point> yright= yPoints.subList(yPoints.size()/2+1,yPoints.size());
            List<Point> xleft= new ArrayList<Point>();
            List<Point> xright= new ArrayList<Point>();
            for (Point i : xPoints){
                if (yleft.contains(i)){
                    xleft.add(i);
                }
                if(yright.contains(i)){
                    xright.add(i);
                }
            }

            return new YNode(yPoints.get(yPoints.size()/2),XTree.makeXTree(xleft,yleft,bound),XTree.makeXTree(xright,yright,bound));
        }
        }
    }

// -----

class XEmpty extends XTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

class YEmpty extends YTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

// -----

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    public Region findRegion(Point p) {
        if (this.point.equals(p)){
            return new Region();
        }
        else if (p.getX()<this.point.getX()){
            Region recordleft=this.left.findRegion(p);
            recordleft.push(DIR.LEFT);
            return recordleft;
        }
        else{
            Region recordright=this.right.findRegion(p);
            recordright.push(DIR.RIGHT);
            return recordright;
        }
    }
}

class YNode extends YTree {
    private Point point;
    private XTree under, above;

    YNode(Point point, XTree under, XTree above) {
        this.point = point;
        this.under = under;
        this.above = above;
    }

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    public Region findRegion(Point p) {
        if (this.point.equals(p)){
            return new Region();
        }
        else if (p.getY()<this.point.getY()){
            Region recordunder=this.under.findRegion(p);
            recordunder.push(DIR.UNDER);
            return recordunder;
        }
        else{
            Region recordabove=this.above.findRegion(p);
            recordabove.push(DIR.ABOVE);
            return recordabove;
        }
    }
}