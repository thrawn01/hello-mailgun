import org.junit.Test;

public class HelloMailgunTest {

    @Test
    public void print() {
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            HelloMailgun.SendSMTP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String s = out.toString();
        //Assert.assertEquals( "Hello World", s);
    }
}