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
    private final String mPage;
    private final String mPDFName;

    /**
     * Creates a new item with the specified title, content and status.
     *
     * @param title    the title of the item
     * @param content  the content of the item
     * @param statusId the status of the item
     * @param page     The page to reference
     * @param PDFName  The page to reference
     */
    public BacklogItem(String title, String content, String statusId, String page, String PDFName) {
        this(UUID.randomUUID().toString(), title, content, statusId, page, PDFName);
    }

    /**
     * Creates a new item with an already existing ID, title, content and status.
     *
     * @param id       the ID of the item
     * @param title    the title of the item
     * @param content  the content of the item
     * @param statusId the status of the item
     * @param page     The page referenced
     * @param PDFName  The page to reference
     */
    public BacklogItem(String id, String title, String content, String statusId, String page, String PDFName) {
        this.mId = id;
        this.mContent = content;
        this.mTitle = title;
        this.mStatusId = statusId;
        this.mPage = page;
        this.mPDFName = PDFName;
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

    public String getPage() {
        return mPage;
    }

    public String getmPDFName() {
        return mPDFName;
    }
}
