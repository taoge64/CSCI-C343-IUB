import java.util.ArrayList;

enum DIR {
    LEFT,
    RIGHT,
    UNDER,
    ABOVE
}

public class Region {
    private ArrayList<DIR> dirs;

    // -----

    Region() {
        this.dirs = new ArrayList<>();
    }

    ArrayList<DIR> getDirs() {
        return dirs;
    }

    // -----

    boolean isEmpty() {

        return this.dirs.size()==0;
    }

    void push(DIR d) {
        this.dirs.add(0,d);
    }

    // -----

    public String toString() {
        return dirs.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Region) {
            Region that = (Region) o;
            return dirs.equals(that.dirs);
        }
        return false;
    }

    public int hashCode() {
        return dirs.hashCode();
    }
}
