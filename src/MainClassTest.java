
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("getLocalNumber вернул меньше 45",getLocalNumber()>45);
    }

    @Test
    public void testGetClassString(){
        Assert.assertTrue("getClassString не вернул hello или Hello",getClassString().contains("hello")||getClassString().contains("Hello"));
    }

}
