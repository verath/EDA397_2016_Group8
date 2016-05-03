package se.chalmers.eda397.group8.pairprogramming.backlog.model;

public class BacklogStatus {

    private static int sIdCounter = 0;
    private final String mId;
    private final String mName;

    public BacklogStatus(String name) {
        this(String.valueOf(sIdCounter), name);
        sIdCounter++;
    }

    public BacklogStatus(String id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    @Override
    public String toString() {
        return mName;
    }
}
