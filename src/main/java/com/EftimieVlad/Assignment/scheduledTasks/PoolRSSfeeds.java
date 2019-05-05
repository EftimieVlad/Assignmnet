package com.EftimieVlad.Assignment.scheduledTasks;

import com.EftimieVlad.Assignment.reader.CsvFeeds;
import com.EftimieVlad.Assignment.entity.RSS;
import com.EftimieVlad.Assignment.service.RSSservice;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.EftimieVlad.Assignment.reader.CsvReaderTask.getAll;

@Configuration
@EnableScheduling
public class PoolRSSfeeds {
    public static List<RSS> cache = new ArrayList<>();
    private static List<CsvFeeds> csvFeeds = new ArrayList<>();

    @Autowired
    private RSSservice rsSservice;

    @Scheduled(fixedRateString ="${fetchMetrics}")
    public void task () {
        cache.removeAll(cache);
        try {
            receiveFeedInformations();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is the key to the application's workflow
     * {@link com.EftimieVlad.Assignment.reader.CsvReaderTask} is a CSV reader function which reads the RSS feeds links from a CSV
     * this idea was born due to the fact that you can go on server and add new feeds in this csv and at fetchMetrics ( application.properties )
     * interval it will go online (without restarting the app)
     * cache is an easy implementation of cache used in controller to return the last records
     * @throws MalformedURLException
     * @throws IOException
     * @throws FeedException
     */
    private void receiveFeedInformations () throws MalformedURLException,IOException,FeedException {
        csvFeeds = getAll();
        for (CsvFeeds feeds : csvFeeds){
            URL feedURL = new URL(feeds.getUrl());

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedURL));

            for (SyndEntry entry : feed.getEntries()){
                RSS rss = new RSS(entry.getTitle(),entry.getDescription().getValue(),entry.getPublishedDate(),getImage(entry.getEnclosures()));
                cache.add(rss);
                this.rsSservice.save(rss);
            }

        }

    }
    private byte[] getImage(List<SyndEnclosure> url) {
        String imageUrl= "" ;
        byte[] buf = new byte[1024];
        if(url.size() > 0) {
            imageUrl = url.get(0).getUrl();
        }
        try {
            buf = exploitImageLocation(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf;

    }

    /**
     * Byte is the default rep of an image and it can be converted to any other type
     * @param location
     * @return image in byte rep
     * @throws MalformedURLException
     * @throws IOException
     */
    public static byte[] exploitImageLocation(String location) throws MalformedURLException, IOException{
            Image image = new Image();
            image.setUrl(location);
            URL url = new URL(image.getUrl());
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            return out.toByteArray();

    }
}
