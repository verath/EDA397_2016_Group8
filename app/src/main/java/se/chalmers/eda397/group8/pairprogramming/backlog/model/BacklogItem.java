package se.chalmers.eda397.group8.pairprogramming.backlog.model;

/**
 * Model of a backlog item.
 */
public class BacklogItem {

    private static int sIdCounter = 0;
    private final String mId;
    private final String mContent;
    private final String mTitle;
    private final String mStatusId;

    /**
     * Creates a new item with the specified title, content and status.
     *
     * @param title    the title of the item
     * @param content  the content of the item
     * @param statusId the status of the item
     */
    public BacklogItem(String title, String content, String statusId) {
        this(String.valueOf(sIdCounter), title, content, statusId);
        sIdCounter++;
    }

    /**
     * Creates a new item with an already existing ID, title, content and status.
     *
     * @param id       the ID of the item
     * @param title    the title of the item
     * @param content  the content of the item
     * @param statusId the status of the item
     */
    public BacklogItem(String id, String title, String content, String statusId) {
        this.mId = id;
        this.mContent = content;
        this.mTitle = title;
        this.mStatusId = statusId;
    }

    /**
     * Gives the unique ID of the item.
     *
     * @return the ID
     */
    public String getId() {
        return mId;
    }

    /**
     * Gives the content of the item.
     *
     * @return the content
     */
    public String getContent() {
        return mContent;
    }

    /**
     * Gives the title of the item.
     *
     * @return the title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Gives the status ID of the item.
     *
     * @return the status ID
     */
    public String getStatusId() {
        return mStatusId;
    }
}
