package com.EftimieVlad.Assignment;

import com.EftimieVlad.Assignment.controller.RSScontroller;
import com.EftimieVlad.Assignment.entity.RSS;
import com.EftimieVlad.Assignment.service.RSSservice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class RssControllerTest {

    @InjectMocks
    RSScontroller rsScontroller;

    @Mock
    RSSservice rsSservice;

    private static List<RSS> rssList = new ArrayList<>();

    @Before
    public void  setUp()  {
        MockitoAnnotations.initMocks(this);

        RSS rss = new RSS();
        rss.setDescription("test");
        rss.setTitle("test");
        rss.setImage(new byte[20]);
        rss.setPublished_date(new Date());
        rssList.add(rss);

    }

    @Test
    public void testRss () {
    when(rsSservice.getAll()).thenReturn(rssList);
    }
}
