package mousavi.alex.belugas.enemy;

import android.graphics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 2/2/13
 * Time: 8:47 PM
 * CS48 rts game project
 */
public class Enemy 
{
	
	private int enemyID;
	
	private double x;
	private double y;
	private double moveXSpeed;
	private double moveYSpeed;
	private boolean alive;
	
	private Bitmap sprite;
	
	public Enemy(int id)
	{
		enemyID = id;
		alive = false;
	}
	
	public boolean update()
	{
		//placeholder move faggot shit
		x += moveXSpeed;
		y += moveYSpeed;
		if (x < 0)
		{
			return true;
		}
		return false;
	}
	
	public void spawn(int sx, int sy)
	{
		//sprite = new 
		x = sx;
		y = sy;
		moveXSpeed = -7;
		moveYSpeed = 0;
		alive = true;
	}
	
	public void die()
	{
		alive = false;
	}
	
	public void draw(Canvas canvas)
	{
		//canvas.drawBitmap(sprite, (int)x, (int)y, null);
		Paint pp = new Paint();
		pp.setColor(Color.BLUE);
		canvas.drawRect((int)x - 10, (int)y - 5, (int)x + 10, (int)y + 5, pp);
	}
	
}
