package com.teamboid.twitterapi.client;

/**
 * Passes paging information to any API functions that can include page, count, since_id, or max_id parameters.
 * @author Aidan Follestad
 */
public class Paging {

    /**
     * Initializes new Paging instance.
     * @param count The number of results to retrieve.
     */
    public Paging(int count) {
        _count = count;
    }
    /**
     * Initializes new Paging instance.
     * @param count The number of results to retrieve.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
     */
    public Paging(int count, long sinceId, long maxId) {
        _count = count;
        _sinceId = sinceId;
        _maxId = maxId;
    }

    private int _page;
    private int _count;
    private long _sinceId;
    private long _maxId;

    /**
     * @deprecated See https://dev.twitter.com/docs/working-with-timelines
     */
    public int getPage() { return _page; }
    public int getCount() { return _count; }
    public long getSinceId() { return _sinceId; }
    public long getMaxId() { return _maxId; }

    /**
     * @deprecated See https://dev.twitter.com/docs/working-with-timelines
     */
    public Paging setPage(int page) {
        _page = page;
        return this;
    }

    public Paging setCount(int count) {
        _count = count;
        return this;
    }

    public Paging setSinceId(long sinceId) {
        _sinceId = sinceId;
        return this;
    }

    public Paging setMaxId(long maxId) {
        _maxId = maxId;
        return this;
    }

    public String getUrlString(char startingCharacter, boolean includeSinceMaxId) {
        String toReturn = "";
        boolean insertedStarting = false;
        if(_page > 0) {
            toReturn += startingCharacter;
            insertedStarting = true;
            toReturn += "page=" + _page;
        }
        if(_count > 0) {
            if(!insertedStarting) {
                toReturn += startingCharacter;
                insertedStarting = true;
            } else toReturn += "&";
            toReturn += "count=" + _count;
        }
        if(includeSinceMaxId) {
            if(_sinceId > 0) {
                if(!insertedStarting) {
                    toReturn += startingCharacter;
                    insertedStarting = true;
                } else toReturn += "&";
                toReturn += "since_id=" + _sinceId;
            }
            if(_maxId > 0) {
                if(!insertedStarting) {
                    toReturn += startingCharacter;
                    insertedStarting = true;
                } else toReturn += "&";
                toReturn += "max_id=" + _maxId;
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return getUrlString('?', true);
    }
}