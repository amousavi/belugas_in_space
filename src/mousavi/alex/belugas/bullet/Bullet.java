package mousavi.alex.belugas.bullet;

import android.graphics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 4/8/13
 * Time: 8:01 PM
 * 
 */
public class Bullet 
{
	private int id;
	private int type;
	private Bitmap bitmap;
	
	private double x;
	private double y;
	
	private boolean alive;
	
	
	private int lifeTime;
	
	public Bullet(int id)
	{
		this.id = id;
		alive = false;
	}
	
	public void spawn(int type, int sx, int sy)
	{
		this.type = type;
		x = sx;
		y = sy;
		alive = true;
		
		bitmap = BulletStats.LASER_SPRITES[type];
	}
	
	public void update()
	{
		if (lifeTime <= 0)
		{
			
		}
	}
	
	public void die()
	{
		
	}
	
	public void draw(Canvas canvas)
	{
		
	}
}
