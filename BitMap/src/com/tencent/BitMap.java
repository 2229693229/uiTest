package com.tencent;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class BitMap extends BitMapTestBase {

	public static void main(String[] args) {
//		String jarName,testClass, testName,androidId;
//		jarName="demo";//生成的jar包名
//		testClass="com.tencent.BitMap";//测试包名
//		testName="";//测试类名
//		androidId="6";//对应androidSDK版本
//		
//		new UiAutomatorHelper(jarName, testClass, testName, androidId);

		String workspase, className, jarName, androidId, sdkpath;
		workspase="F:\\Users\\0622\\workspace\\BitMap";
		className="com.tencent.BitMap";
		jarName="demo1";
		androidId="6";
		sdkpath="E:\\android-sdk";
		CtsHelper cts=new CtsHelper(workspase, className, jarName, androidId, sdkpath );
		//cts.setDevices("0123456789");
		cts.runTest();
	}
	public void TestBitMap(){
		String Path="/mnt/sdcard/testBitmap.jpg";
		File storePath=new File(Path);
		UiDevice.getInstance().takeScreenshot(storePath);
		Bitmap bitMap=BitmapFactory.decodeFile(Path);
		saveBitMapToSDcard(bitMap, "bitmap");
		
	

	}

  public void testCutImageAndColorPixel() throws UiObjectNotFoundException{
	  Rect rect=getObjectByCollectionInstance("com.tencent.qlauncher.lite:id/hotseat", View.class.getName(), 1).getBounds();
	  String path="mnt/sdcard/Img_cut_001.jpg";
		File file=new File(path);
		UiDevice.getInstance().takeScreenshot(file);
	  cutBitmap(rect, path,"cut_image_001");
	  System.out.println("ddd ");
	  int color=getColorPicel(rect.centerX(), rect.centerY());
	  System.out.println("颜色值为："+color);
  }
  
  public void  testEmbedText() {
	  String name="testEmbedText";
	  screenShotAndDrawText(getPath(name), "testEmbed", "这是.桌面截图");
}
  public void testIsSame(){
	  String targetPath="testcompare";
	  takeScreenshot("target");
	  sleep(1100);
	  System.out.println();
	  UiDevice.getInstance().click(679, 313);
	  sleep(1000);
	  takeScreenshot("compare");
	  boolean b=getDiffofTwo(getPath("target"), getPath("compare"), 1.0d);
	  System.out.println(getPath("target"));
	  System.out.println("对比结果为"+b);
  }
}
