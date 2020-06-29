package pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import lib.selenium.PreAndPost;

public class CreateNewIncident extends PreAndPost{
	
	public CreateNewIncident(EventFiringWebDriver driver, ExtentTest test) {	
		this.driver = driver;
		this.test = test;
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		PageFactory.initElements(driver,this);
	}
	 
	@FindBy(id="incident.number") 
	private WebElement eleIncidentNumber;	
	
	@FindBy(id="sys_display.incident.caller_id")
	private WebElement eleCallerId;	
	
	@FindBy(how=How.ID,using="incident.short_description")
	private WebElement eleShortDescription;
	
	public CreateNewIncident getIncidentNumber() {	
		pause(3000);
		incidentNumber = getAttribute(eleIncidentNumber,"value");
		return this;
	}	

	public CreateNewIncident selectUser(String data) {
		typeAndChoose(eleCallerId, data);
		return this;
	}	
	
	public CreateNewIncident typeShortDescription(String data) {
		type(eleShortDescription, data);
		return this;		
	}
	
	public CreateNewIncident clickSubmit() {
		WebElement eleSubmit = locateElement("sysverb_insert");
		click(eleSubmit);
		return this;		
	}
	
}
