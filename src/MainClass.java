package src;

import org.junit.Assert;
import org.junit.Test;

public class MainClass {

    private int class_number = 20;
    private String class_string = "Hello, world";

    public int getLocalNumber(){
        return 46;
    }
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("getLocalNumber не вернул 14",getLocalNumber()==14);
    }

    public int getClassNumber(){
        return class_number;
    }

    public String getClassString(){
        return class_string;
    }
}
