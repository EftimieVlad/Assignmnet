package com.EftimieVlad.Assignment.scheduledTasks;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import static com.EftimieVlad.Assignment.entity.RSS.isUniquie;

@Configuration
@EnableScheduling
public class PoolRSSfeeds {
    private List<RSS> cache = new ArrayList<>();

    @Autowired
    private RSSservice rsSservice;

    @Scheduled(fixedRateString ="${fetchMetrics}")
    public void task () {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
    }

    private void test () throws MalformedURLException,IOException,FeedException {
        URL feedURL = new URL("http://feeds.nos.nl/nosjournaal?format=xml");

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedURL));


        for (SyndEntry entry : feed.getEntries()){
            RSS rss = new RSS(entry.getTitle(),entry.getDescription().getValue(),entry.getPublishedDate(),getImage(entry.getEnclosures()));
            if (isUniquie(cache,rss.getTitle(),rss.getPublished_date())) {
                System.out.println(rss.getPublished_date());
                System.out.println(rss.getTitle());
            }
            else
                cache.add(rss);
             this.rsSservice.save(rss);
        }

    }
    private static byte[] getImage(List<SyndEnclosure> url) throws MalformedURLException, IOException{
        Image image = new Image();
        if(url.size() > 0) {
            image.setUrl(url.get(0).getUrl());
            URL urls = null;

            urls = new URL(image.getUrl());

            InputStream in = null;

            in = new BufferedInputStream(urls.openStream());

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
        else
            return null;
    }
}
