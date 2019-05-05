package com.EftimieVlad.Assignment;


import com.rometools.rome.feed.rss.Image;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


import static com.EftimieVlad.Assignment.scheduledTasks.PoolRSSfeeds.exploitImageLocation;

public class ImageTest {
    private byte[] imageOne;
    private byte[] imageTwo;
    private BufferedImage originalImage;

    public static boolean ok = true;
    @Before
    public void takeImages() {
        try {

            originalImage =  ImageIO.read(new File("1008x567.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "jpg", baos );
            baos.flush();
            imageOne = baos.toByteArray();
            baos.close();
            imageTwo  = exploitImageLocation("https://nos.nl/data/image/2019/05/04/547817/1008x567.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public  void testRomeImageVsImageIo () {

        try (FileOutputStream fos = new FileOutputStream("1.jpg")) {
            fos.write(imageOne);
       } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("2.jpg")) {
            fos.write(imageTwo);
     } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Best memory usage
        Assert.assertNotEquals(imageOne,imageTwo);
    }
    @Test
    public void testImageDetails () {
        //standard output from the rss feed
        Assert.assertTrue(originalImage.getWidth() == 1008);
        Assert.assertTrue(originalImage.getHeight() == 567);
}
}
