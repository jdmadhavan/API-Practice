package lib.listeners;

import java.io.IOException;

import com.aventstack.extentreports.TestListener;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;

public class ExtentReportsListener implements TestListener {

	@Override
	public void onTestStarted(Test test) {
		
	}

	@Override
	public void onNodeStarted(Test node) {
		
	}

	@Override
	public void onLogAdded(Test test, Log log) {
		
	}

	@Override
	public void onCategoryAssigned(Test test, Category category) {
		
	}

	@Override
	public void onAuthorAssigned(Test test, Author author) {
		
	}

	@Override
	public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
		
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
		
	}

	@Override
	public void onScreencastAdded(Test test, Screencast screencast) throws IOException {
		
	}

}
