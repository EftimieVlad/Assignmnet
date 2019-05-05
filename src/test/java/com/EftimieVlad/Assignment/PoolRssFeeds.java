package com.EftimieVlad.Assignment;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PoolRssFeeds {


    @Test
    public void CsvExists() {
        File f = new File("feed.csv");
        Assert.assertTrue("csv file is present",f.exists() && !f.isDirectory());
    }


}
