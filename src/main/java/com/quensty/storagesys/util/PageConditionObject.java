package com.quensty.storagesys.util;

/**
 * @Description：
 * @Author： Laiwenjun
 * @Date： 2023/6/27  13:50
 */
public class PageConditionObject<T> {
    private MyPage<T> page;

    private T entity;

    public MyPage<T> getPage() {
        return page;
    }

    public void setPage(MyPage<T> page) {
        this.page = page;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
