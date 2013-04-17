/**
 * 
 */
package mousavi.alex.belugas.sprites;

import android.graphics.*;
import android.util.*;
import mousavi.alex.belugas.*;
import mousavi.alex.belugas.bullet.*;

/**
 * @author impaler
 *
 */
public class Beluga
{
	
	private static final String TAG = "Beluga";

	private SpriteAnimation sprite;
	
	private int x;				// the X coordinate of the object (top left of the image)
	private int y;				// the Y coordinate of the object (top left of the image)
	private int prevY;

	private int moveSpeed;
	private int moveXSpeed;
	private int moveYSpeed;
	private int moveTarY;
	
	private int shootXOff;
	private int shootYOff;
	
	private int rotation;
	
	private boolean screenHeld;
	
	MainGamePanel gamePanel;
	
	
	private int screenHeight;
	private int screenWidth;
	
	private int spriteWidth;
	private int spriteHeight;
	
	private boolean testHeld;
	
	public Beluga(MainGamePanel gamePanel, Bitmap bitmap, int sx, int sy, int width, int height) 
	{
		this.gamePanel = gamePanel;
		this.x = sx;
		this.y = sy;
		screenHeight = height;
		screenWidth = width;
		
		sprite = new SpriteAnimation(bitmap, 5, 3);
		spriteWidth = sprite.getCellWidth();
		spriteHeight = sprite.getCellHeight();
		
		shootXOff = spriteWidth / 2;
		shootYOff = spriteHeight / 2;
		
		moveSpeed = 8;
		moveXSpeed = 0;
		moveYSpeed = 0;
		rotation = 0;
		testHeld = false;
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
		if(!bool){
			moveYSpeed =0;
			moveXSpeed =0;
		}
		screenHeld = bool;
	}
	
	public void shootBullet()
	{
		gamePanel.shootBullet(BulletStats.LASER, x, y, 0);
	}
	
	
	public void handleTouch(int touchX,int touchY)
	{
		//Log.d("BTouch", ""+touchY);
		//Log.d("BTouch", ""+spriteHeight);
		moveTarY = touchY;
		
		if(touchX > x + spriteWidth/2){
			moveXSpeed = 1;
		} else if(touchX < x + spriteWidth/2){
			moveXSpeed = -1;
		} else moveXSpeed = 0;
		
	}
	
	
	// the update method for Beluga
	public void update(long gameTime) {
		sprite.update();
		if (screenHeld)
		{
			if(Math.abs(moveTarY-(y+spriteHeight/2)) < 40){//on beluga
				moveYSpeed = 0;
			}else if(moveTarY > y + spriteHeight/2)
			{
				if (rotation < 15)
				{
					rotation ++;
				}
				moveYSpeed = moveSpeed;
			} else if(moveTarY < y + spriteHeight/2){
				if (rotation > -15)
				{
					rotation--;
				}
				moveYSpeed = -moveSpeed;
			} else
			{
				moveYSpeed = 0;
			}
		}
		y += moveYSpeed;
		/*if (screenHeld){
			int newpos = y+(8* moveYSpeed);
			if (newpos > 0 && newpos < MainGamePanel.GAME_HEIGHT-spriteHeight){
				y += 8 * moveYSpeed;
				//x += 8 * moveXSpeed;
			} else{
				//Log.v("newpos",Integer.toString(screenHeight));
			}
		}*/
		//Log.d("BTouch", ""+screenHeld);
			
		if (y == prevY)
		{
			if (rotation > 0)
			{
				rotation -= 1;
			}
			else if (rotation < 0)
			{
				rotation += 1;
			}
		}
		prevY = y;

		
	}
	
	public void update(long gameTime,int tx,int ty) {
		/*sprite.update();
		
		
		if (tx > x + spriteWidth/2){
			x += 5;
		} else {
			x -= 5;
		}
		
		if(ty>y + spriteHeight){
			y+=5;
		} else {
			y -= 5;
		}*/
	}
	
	// the draw method which draws the corresponding frame
	public void draw(Canvas canvas) {
		// where to draw the sprite
		
		canvas.rotate(rotation,(int)( MainGamePanel.DRAW_WIDTH_RATIO * (x - spriteWidth / 2)), (int)(MainGamePanel.DRAW_HEIGHT_RATIO * (y - spriteHeight / 2)));
		Paint pp = new Paint();
		sprite.draw(canvas,(int)( MainGamePanel.DRAW_WIDTH_RATIO * (x - spriteWidth / 2)),(int)(MainGamePanel.DRAW_HEIGHT_RATIO * (y - spriteHeight / 2)), pp);
		//sprite.draw(canvas,(int) (x - spriteWidth / 2),(int)(y - spriteHeight / 2), pp);
		canvas.restore();
		
	}



}
