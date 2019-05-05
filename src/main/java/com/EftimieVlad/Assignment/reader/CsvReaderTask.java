package com.EftimieVlad.Assignment.reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CsvReaderTask {

    private static Logger logger = LoggerFactory.getLogger(CsvReaderTask.class);

    public static List<CsvFeeds> getAll () throws IOException {
        List<CsvFeeds> list = new ArrayList<>();
                Reader reader = Files.newBufferedReader(Paths.get("feeds.csv"));

            CsvToBean<CsvFeeds> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CsvFeeds.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CsvFeeds> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CsvFeeds csvUser = csvUserIterator.next();
                logger.info("Name : " + csvUser.getName());
                logger.info("Url : " + csvUser.getUrl());
                list.add(csvUser);
            }
        return list;
    }
}
