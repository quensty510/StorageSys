package com.quensty.storagesys.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.Collections;
import java.util.List;

/**
 * @Description： 分页查询结果封装
 * @Author： Laiwenjun
 * @Date： 2023/6/27  13:46
 */
public class MyPage<T> implements IPage<T> {
    protected List<T> data;
    protected long total;
    protected long size;
    protected long current;

    public MyPage() {
        this.data = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public List<T> getRecords() {
        return this.data;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.data = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }
}
