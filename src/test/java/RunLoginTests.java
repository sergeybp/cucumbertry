import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

/**
 * Created by sergeybp on 07.06.17.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/Login.features",
        glue = "steps"
)
public class RunLoginTests { // NOSONAR SonarLint does not know about @RunWith(Cucumber.class)
}


