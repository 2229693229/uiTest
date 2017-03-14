package com.tencent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;








import java.io.IOException;

import android.R.color;
import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class BitMapTestBase extends UiAutomatorTestCase {
	
	public void saveBitMapToSDcard(Bitmap bitmap,String newNmae){
		FileOutputStream out=null;
		try {
			out=new FileOutputStream("/mnt/sdcard/"+newNmae+".png");
			if (out!=null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.close();
			}
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
	}

/*
 * 截取一张矩形区域图片然后保存
 */
	public void cutBitmap(Rect rect,String path,String ImageName){
		Bitmap bm=BitmapFactory.decodeFile(path);
		bm=bm.createBitmap(bm, rect.left, rect.top, rect.width(), rect.height());
		saveBitMapToSDcard(bm, ImageName);
		
	}
	/*
	 * 获取某点的颜色值
	 * 
	 */
	public int getColorPicel(int x ,int y){
		String path="mnt/sdcard/Img_color_001.jpg";
		File file=new File(path);
		UiDevice.getInstance().takeScreenshot(file);
		Bitmap bm=BitmapFactory.decodeFile(path);
		int color=bm.getPixel(x, y);
		return color;
	}
	   public  UiObject getObjectByCollectionInstance(String id,String type,int instance) throws UiObjectNotFoundException {
	    	UiCollection Collection=new UiCollection(new UiSelector().resourceId(id));
	    	UiObject object=Collection.getChildByInstance(new UiSelector().classNameMatches(View.class.getName()), instance);
			return object;
			
		}

	   public void screenShotAndDrawText(String pathname,String imageName,String text){
		   File file=new File(pathname);
		   UiDevice.getInstance().takeScreenshot(file);
		   Bitmap bim=BitmapFactory.decodeFile(pathname);
		   Bitmap bim1=drawTexBitmap(bim, text);
		   saveBitMapToSDcard(bim1, imageName);
		   
	   }
	   public Bitmap drawTexBitmap(Bitmap bm,String text){
		   int x=bm.getWidth();
		   int y=bm.getHeight();
		   Bitmap newBm=Bitmap.createBitmap(x, y+80, Bitmap.Config.ARGB_8888);
		   
		   /*
		    * 创建画布 画笔
		    */
		   Canvas canvas=new Canvas(newBm);
		   Paint paint=new Paint();
		   /*
		    * 原图基础上叠加一张图片 位置是（0.0）
		    */
		   canvas.drawBitmap(bm, 0, 0,paint);
		   paint.setColor(Color.parseColor("#FF0000"));
		   paint.setTextSize(40);
		   canvas.drawText(text, 20, y+50, paint);
		   canvas.save(Canvas.ALL_SAVE_FLAG);
		   canvas.restore();
		   
		  return newBm;
		   
	   }
	  
	   public String getPath(String name){
		   String path="/mnt/sdcard/"+name+".jpg";
		   
		   return path;
	   }
	   public void takeScreenshot(String name) {
		   String path = getPath(name);
		   File file=new File(path);
		   UiDevice.getInstance().takeScreenshot(file);
	}
	   
	   public Boolean getDiffofTwo(String targetImgPath,String comImgPath,double percent){
		   Bitmap bm1=BitmapFactory.decodeFile(targetImgPath);
		   Bitmap bm2=BitmapFactory.decodeFile(comImgPath);
		   System.out.println(bm1.getHeight());
		   int numdiff=0;
		   for (int i = 0; i <bm1.getHeight() ; i++) {
//			   System.out.println(i);
			   for (int j = 0; j < bm1.getWidth(); j++) {
				   if (bm1.getPixel(j,i)!=bm2.getPixel(j, i)) {
					   numdiff++;
					
				}
				}
		}
		   System.out.println(numdiff);
		   double totalpixels=bm1.getHeight()*bm1.getWidth();
		   System.out.println("相似度"+(1.0-numdiff/totalpixels));
		   return percent<=1.0-numdiff/totalpixels;
		   
		   
		   
		   
	
		   
	   }
	   


















}
