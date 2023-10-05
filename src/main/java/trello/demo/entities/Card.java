package trello.demo.entities;

import java.util.ArrayList;
import java.util.Date;

public class Card{
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
//    public ArrayList<IdChecklist> idChecklists;
//    public ArrayList<IdLabel> idLabels;
    public String idList;
    public ArrayList<String> idMembers;
    public ArrayList<String> idMembersVoted;
    public int idShort;
    public ArrayList<String> labels;
//    public Limits limits;
    public String locationName;
    public boolean manualCoverAttachment;
    public String name;
    public int pos;
    public String shortLink;
    public String shortUrl;
    public boolean subscribed;
    public String url;
}
