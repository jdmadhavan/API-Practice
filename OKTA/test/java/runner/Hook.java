package runner;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lib.selenium.PreAndPost;

public class Hook extends PreAndPost{

	@Before
	public void before(Scenario sc) {
		startTestCase(sc.getName(), sc.getId());
	}
	
	@After
	public void after() {
		closeAllBrowsers();
	}

}





