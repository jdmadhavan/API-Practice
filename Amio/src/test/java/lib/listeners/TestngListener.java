package lib.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.testng.IAnnotationTransformer;
import org.testng.IDataProviderListener;
import org.testng.IDataProviderMethod;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestngListener implements IAnnotationTransformer,IRetryAnalyzer, IDataProviderListener{

	int maxcount = 1;

	public void transform(ITestAnnotation annotation,Class testClass, Constructor testConstructor,Method testMethod) {

	}

	public boolean retry(ITestResult result) {
		if (!result.isSuccess() & maxcount < 2) {
			maxcount++;
			return true;
		}
		return false;
	}

	@Override
	public void afterDataProviderExecution(IDataProviderMethod arg0, ITestNGMethod arg1, ITestContext arg2) {

	}

	@Override
	public void beforeDataProviderExecution(IDataProviderMethod dp, ITestNGMethod meth, ITestContext test) {
		List<Integer> indices = dp.getIndices();
		for (Integer integer : indices) {
			System.out.println(integer);
		}
	}

}
























