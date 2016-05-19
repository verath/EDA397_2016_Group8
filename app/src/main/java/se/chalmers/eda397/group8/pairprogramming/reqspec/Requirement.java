package se.chalmers.eda397.group8.pairprogramming.reqspec;


import android.support.annotation.NonNull;

public class Requirement {

    private static int cCount;

    @NonNull
    private final String mId;

    @NonNull
    private final String mReqSpecId;

    @NonNull
    private final String mPageNumber;

    public Requirement(@NonNull String reqSpecId, @NonNull String pageNumber) {
        this(Integer.toString(cCount++), reqSpecId, pageNumber);
    }

    public Requirement(@NonNull String id, @NonNull String reqSpecId, @NonNull String pageNumber) {
        mId = id;
        mReqSpecId = reqSpecId;
        mPageNumber = pageNumber;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getReqSpecId() {
        return mReqSpecId;
    }

    @NonNull
    public String getPageNumber() {
        return mPageNumber;
    }
}
