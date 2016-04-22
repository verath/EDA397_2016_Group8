package group8.eda397.chalmers.se.pairprogramming.backlog.model;

import android.content.Context;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogItem {

    public enum Status {
        BACKLOG(R.string.backlog),
        ONGOING(R.string.ongoing),
        READY_FOR_TEST(R.string.ready_for_test),
        DONE(R.string.done);
        int mKey;

        Status(int key) {
            mKey = key;
        }

        public String getName(Context context) {
            return context.getString(mKey);
        }
    }

    private static int sIdCounter = 0;
    private final String mId;
    private final String mContent;
    private final String mTitle;
    private final Status mStatus;

    public BacklogItem(String title, String content, Status status) {
        this.mId = String.valueOf(sIdCounter);
        sIdCounter++;
        this.mContent = content;
        this.mTitle = title;
        this.mStatus = status;
    }

    public String getId() {
        return mId;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public Status getStatus() {
        return mStatus;
    }
}
