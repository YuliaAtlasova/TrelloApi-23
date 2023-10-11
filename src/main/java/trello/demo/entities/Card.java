package trello.demo.entities;

import java.util.ArrayList;
import java.util.Date;

public class Card {
    public String id;
    public ArrayList<String> checkItemStates;
    public boolean closed;
    public String coordinates;
    public String creationMethod;
    public Date dateLastActivity;
    public String desc;
    public String due;
    public String dueReminder;
    public String email;
    public String idBoard;
    public String idList;
    public ArrayList<String> idMembers;
    public ArrayList<String> idMembersVoted;
    public int idShort;
    public ArrayList<String> labels;
    public String locationName;
    public boolean manualCoverAttachment;
    public String name;
    public int pos;
    public String shortLink;
    public String shortUrl;
    public boolean subscribed;
    public String url;

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
