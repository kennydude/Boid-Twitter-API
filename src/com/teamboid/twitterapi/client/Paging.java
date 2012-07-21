package com.teamboid.twitterapi.client;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 7/21/12
 * Time: 4:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Paging {

    public Paging(int count) {
        _count = count;
    }
    public Paging(int count, int page) {
        _count = count;
        _page = page;
    }
    public Paging(int count, long sinceId, long maxId) {
        _count = count;
        _sinceId = sinceId;
        _maxId = maxId;
    }
    public Paging(int count, int page, long sinceId, long maxId) {
        _count = count;
        _page = page;
        _sinceId = sinceId;
        _maxId = maxId;
    }

    private int _count;
    private int _page;
    private long _sinceId;
    private long _maxId;

    public int getCount() { return _count; }
    public int getPage() { return _page; }
    public long getSinceId() { return _sinceId; }
    public long getMaxId() { return _maxId; }

    public String getUrlString() {
        String toReturn = "";
        if(_count > 0) toReturn += "&count=" + _count;
        if(_page > 0) toReturn += "&page=" + _page;
        if(_sinceId > 0) toReturn += "&since_id=" + _sinceId;
        if(_maxId > 0) toReturn += "&max_id=" + _maxId;
        return toReturn;
    }
}
