package trello.demo.entities;

public class CheckItem {
    private String id;
    private String name;
    private String idChecklist;

    public CheckItem() {
    }

    public CheckItem(String id, String name, String idChecklist) {
        this.id = id;
        this.name = name;
        this.idChecklist = idChecklist;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CheckItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdChecklist() {
        return idChecklist;
    }

    public CheckItem setIdChecklist(String idChecklist) {
        this.idChecklist = idChecklist;
        return this;
    }

    public String toString() {
        return "Board{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", idChecklist='" + idChecklist + '\''
                + '}';
    }
}