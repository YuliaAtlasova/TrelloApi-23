package trello.demo.entities;

public class Board {
    private String id;
    private String name;
    private String desc;
    private boolean closed;

    public Board() {
    }

    public Board(String id, String name, String desc, boolean closed) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.closed = closed;
    }

    public String getId() {
        return id;
    }

    public Board setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Board setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Board setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public boolean isClosed() {
        return closed;
    }

    public Board setClosed(boolean closed) {
        this.closed = closed;
        return this;
    }

    @Override
    public String toString() {
        return "Board{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", closed=" + closed
                + '}';
    }
}
