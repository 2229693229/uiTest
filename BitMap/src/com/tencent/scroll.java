package com.tencent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class scroll extends UiAutomatorTestCase {

	public static void main(String[] args) {
		String jarName, testClass, testName, androidId;
		jarName="demo";
		testClass="com.tencent.uiscrollable";
		testName="testFling";
		androidId="5";
		
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
		
	

	}
//	public void testUiScrollable() throws UiObjectNotFoundException{
//		UiScrollable scroll = new UiScrollable(new UiSelector().className("android.widget.ListView"));
//		scroll.scrollForward();
//		
//	}

}
