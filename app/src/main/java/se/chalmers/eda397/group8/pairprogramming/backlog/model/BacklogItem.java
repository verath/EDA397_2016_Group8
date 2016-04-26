package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import android.content.Context;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Model of a backlog item.
 */
public class BacklogItem {

    /**
     * The different values an item's status can be.
     */
    public enum Status {
        BACKLOG(R.string.backlog),
        ONGOING(R.string.ongoing),
        READY_FOR_TEST(R.string.ready_for_test),
        DONE(R.string.done);
        int mKey;

        Status(int key) {
            mKey = key;
        }

        /**
         * Gives the name of the status.
         *
         * @param context the application context
         * @return the name
         */
        public String getName(Context context) {
            return context.getString(mKey);
        }
    }

    private static int sIdCounter = 0;
    private final String mId;
    private final String mContent;
    private final String mTitle;
    private final Status mStatus;

    /**
     * Creates a new item with the specified title, content and status.
     *
     * @param title   the title of the item
     * @param content the content of the item
     * @param status  the status of the item
     */
    public BacklogItem(String title, String content, Status status) {
        this.mId = String.valueOf(sIdCounter);
        sIdCounter++;
        this.mContent = content;
        this.mTitle = title;
        this.mStatus = status;
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
     * Gives the status of the item.
     *
     * @return the status
     */
    public Status getStatus() {
        return mStatus;
    }
}
