package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import java.util.UUID;

/**
 * Model of a backlog item.
 */
public class BacklogItem {

    private final String mId;
    private final String mContent;
    private final String mTitle;
    private final String mStatusId;
    private final String mRequirementId;

    /**
     * Creates a new item with the specified title, content and status.
     *
     * @param title         the title of the item
     * @param content       the content of the item
     * @param statusId      the status of the item
     * @param requirementId the requirement associated with this item.
     */
    public BacklogItem(String title, String content, String statusId, String requirementId) {
        this(UUID.randomUUID().toString(), title, content, statusId, requirementId);
    }

    /**
     * Creates a new item with an already existing ID, title, content and status.
     *
     * @param id            the ID of the item
     * @param title         the title of the item
     * @param content       the content of the item
     * @param statusId      the status of the item
     * @param requirementId the requirement associated with this item.
     */
    public BacklogItem(String id, String title, String content, String statusId,
                       String requirementId) {
        this.mId = id;
        this.mContent = content;
        this.mTitle = title;
        this.mStatusId = statusId;
        this.mRequirementId = requirementId;
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

    public String getRequirementId() {
        return mRequirementId;
    }
}
