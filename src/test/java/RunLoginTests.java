
/**
 * Created by sergeybp on 07.06.17.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions (features ="classpath:features/Login.features",
        glue={"steps"})
public class RunLoginTests extends AbstractTestNGCucumberTests{
}