/**
 * 
 */
package mousavi.alex.belugas.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * @author impaler
 *
 */
public class BelugaSequence {
	
	private static final String TAG = BelugaSequence.class.getSimpleName();

	private Bitmap bitmap;		// the animation sequence
	private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
	private int frameNr;		// number of frames in animation
	private int currentFrame;	// the current frame
	private long frameTicker;	// the time of the last frame update
	private int framePeriod;	// milliseconds between each frame (1000/fps)
	
	private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
	private int spriteHeight;	// the height of the sprite
	
	private int x;				// the X coordinate of the object (top left of the image)
	private int y;				// the Y coordinate of the object (top left of the image)
	
	private boolean screenHeld;
	
	int screenHeight;
	int screenWidth;
	
	public BelugaSequence(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		framePeriod = 1000 / fps;
		frameTicker = 0l;
		screenHeight = height;
		screenWidth = width;
	}
	
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Rect getSourceRect() {
		return sourceRect;
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}
	public int getFrameNr() {
		return frameNr;
	}
	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}
	public int getCurrentFrame() {
		return currentFrame;
	}
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	public int getFramePeriod() {
		return framePeriod;
	}
	public void setFramePeriod(int framePeriod) {
		this.framePeriod = framePeriod;
	}
	public int getSpriteWidth() {
		return spriteWidth;
	}
	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}
	public int getSpriteHeight() {
		return spriteHeight;
	}
	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setScreenHeight(int y){
		screenHeight = y;
	}
	
	public void setHeld(boolean bool){
		if(!bool){vert=0;horiz=0;}
		screenHeld = bool;
	}
	int vert = 0;
	int horiz = 0;
	private int tx;
	private int ty;
	public void handleTouch(int touchX,int touchY){
		if(Math.abs(touchY-(y+spriteHeight/2)) < 40){
			vert = 0;
		}else if(touchY > y + spriteHeight/2){
			vert = 1;
		} else if(touchY < y + spriteHeight/2){
			vert = -1;
		} else vert = 0;
		
		if(touchX > x + spriteWidth/2){
			horiz = 1;
		} else if(touchX < x + spriteWidth/2){
			horiz = -1;
		} else horiz = 0;
		
		
	}
	
	
	
	// the update method for Beluga
	public void update(long gameTime) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
		
		if (screenHeld){
			int newpos = y+(8*vert);
			if (newpos > 0 && newpos < screenHeight-spriteHeight){
				y += 8 * vert;
				//x += 8 * horiz;
			} else{
				//Log.v("newpos",Integer.toString(screenHeight));
			}
		}
		
		
		
	}
	
	public void update(long gameTime,int tx,int ty) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
		
		if (tx > x + spriteWidth/2){
			x += 5;
		} else {
			x -= 5;
		}
		
		if(ty>y + spriteHeight){
			y+=5;
		} else {
			y -= 5;
		}
	}
	
	// the draw method which draws the corresponding frame
	public void draw(Canvas canvas) {
		// where to draw the sprite
		Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		
		
	}



}
