package com.sentryc.service.dto;

import java.util.List;

public class AppResponseDTO<T> {
    private PageMeta meta;
    private List<T> data;

    // Getters and setters
    public PageMeta getMeta() {
        return this.meta;
    }

    public void setMeta(PageMeta meta) {
        this.meta = meta;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
