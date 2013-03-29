package mousavi.alex.belugas.boss;

import android.graphics.*;
import android.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 3/28/13
 * Time: 4:59 PM
 * 
 */
public class Boss 
{
	
	public static final int IDLE = 1;
	public static final int OFFSCREEN = 0;
	public static final int ENTERING = 2;
	
	private Bitmap sprite;
	private double x;
	private double y;
	
	protected int state;
	
	
	public static final int FINAL_X = 600;
	public static final int ENTER_SPEED = 4;
	
	public Boss(Bitmap sprite)
	{
		this.sprite = sprite;
		state = OFFSCREEN;
		x = 1200;
		y = 0;
	}
	
	public void enterScreen()
	{
		state = ENTERING;
	}
	
	public void update()
	{
		switch (state)
		{
			case OFFSCREEN:
				
				break;
			case ENTERING:
			{
				if (x > FINAL_X)
				{
					x -= ENTER_SPEED;
					Log.d("boss", x + "");
				}
				else
				{
					x = FINAL_X;
					state = IDLE;
				}
				break;
			}
		}
	}
	
	
	public void draw(Canvas canvas)
	{
		if (state == OFFSCREEN)
			return;
		
		Paint pp = new Paint();
		//temp draw code
		canvas.drawBitmap(sprite, new Rect(0,0,sprite.getWidth(), sprite.getHeight()), new Rect((int)x, (int)y ,(int)x + sprite.getWidth()* 2,(int)y + sprite.getHeight() * 2), pp);
	}
	
	
}
