package com.example.springboot;

import com.example.springboot.util.ElasticsearchUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTests {

    @Test
    public void createIndex() {
        ElasticsearchUtils.createIndex("test");
        Assert.assertTrue(ElasticsearchUtils.isIndexExist("test"));
    }

    @Test
    public void removeIndex() {
        ElasticsearchUtils.deleteIndex("test");
        Assert.assertFalse(ElasticsearchUtils.isIndexExist("test"));
    }
}
