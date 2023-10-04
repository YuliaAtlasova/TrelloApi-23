package trello.demo.entities;

public class List {

    private String id;
    private String name;
    boolean closed;
    private String idBoard;

    public List() {
    }

    public List(String id, String name, boolean closed, String idBoard) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.idBoard = idBoard;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public List setName(String name) {
        this.name = name;
        return this;
    }

    public List setClosed(boolean closed) {
        this.closed = closed;
        return this;
    }

    public List setIdBoard(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }
}