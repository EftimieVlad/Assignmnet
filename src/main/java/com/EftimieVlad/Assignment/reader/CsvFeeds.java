package com.EftimieVlad.Assignment.reader;

import com.opencsv.bean.CsvBindByName;

public class CsvFeeds {
    @CsvBindByName(column = "name", required = true)
    private String name;
    @CsvBindByName(column = "url", required = true)
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
