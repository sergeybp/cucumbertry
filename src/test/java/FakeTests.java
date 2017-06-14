import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by sergeybp on 14.06.17.
 */
public class FakeTests {

    @Step("Running first fake test.")
    @Test
    public void someTest() {
        for (int x = 0; x < 2000; x++) {
            Assert.assertTrue(x < 2000);
        }
    }

    @Step("Running another fake test.")
    @Test
    public void anotherTest(){
        Assert.assertTrue(1 < 100);
        int x = 2;
        Assert.assertEquals(Math.max(2, 10), 10);
    }

    @Step("Running broken test. Will fail.")
    @Test
    public void brokenTest(){
        Assert.assertEquals(1, 2);
    }

}
