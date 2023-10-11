package trello.demo.entities;

import java.util.ArrayList;

public class Checklist {
    private String id;
    private String name;
    private String idBoard;
    private String idCard;
    private String pos;
    private ArrayList<CheckItem> checkItems;

    public Checklist() {
    }

    public Checklist(String id, String name, String idBoard, String idCard) {
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.idBoard = idBoard;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Checklist setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public Checklist setIdCard(String idBoard) {
        this.idCard = idBoard;
        return this;
    }

    public String toString() {
        return "Board{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", idBoard='" + idBoard + '\''
                + ", idCard=" + idCard
                + '}';
    }
}