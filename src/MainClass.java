import org.junit.Assert;
import org.junit.Test;

public class MainClass {
    public int getLocalNumber(){
        return 14;
    }
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("getLocalNumber не вернул 14",getLocalNumber()==14);
    }
}
