package trello.demo.entities;

public class Card {
    public String id;
    public boolean closed;
    public String desc;
    public String name;
    public int pos;

    public Card(String id, String name, String desc, boolean closed) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.closed = closed;
    }

    public String getId() {
        return id;
    }

    public Card setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public String toString() {
        return "Card{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", closed=" + closed
                + '}';
    }
}
