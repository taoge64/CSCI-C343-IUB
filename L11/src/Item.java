public class Item<E> implements Comparable<Item<E>> {
    private E data;
    private String name;
    private int position;
    private int value;
    private boolean visited;

    Item(E data, String name, int value) {
        this.data = data;
        this.name = name;
        this.value = value;
        this.reset();
    }

    void reset() {
        this.position = -1;
        this.visited = false;
    }

    E getData() {
        return data;
    }

    String getName() {
        return name;
    }

    int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String toString() {
        return String.format("%s[%d]", name, value);
        // return String.format("(N=%s,V=%s,P=%s,V?=%s)", name, value, position, visited);
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item that = (Item) o;
            return name.equals(that.getName());
        } else return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public int compareTo(Item that) {
        return Integer.compare(value, that.getValue());
    }
}