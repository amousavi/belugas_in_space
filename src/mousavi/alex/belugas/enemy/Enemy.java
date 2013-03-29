package mousavi.alex.belugas.enemy;

import android.graphics.*;
import mousavi.alex.belugas.sprites.*;

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
	
	private int type;
	
	private SpriteAnimation sprite;
	
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
		sprite.update();
		if (x < 0)
		{
			return true;
		}
		return false;
	}
	
	public void spawn(int tt, int sx, int sy)
	{
		//sprite = new 
		type = tt;
		x = sx;
		y = sy;
		moveXSpeed = -7;
		moveYSpeed = 0;
		
		alive = true;
		sprite = new SpriteAnimation(EnemyStats.ENEMY_SPRITES[EnemyStats.SNOWBALL], 2, 7);
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
		sprite.draw(canvas,(int)x, (int)y, pp);
	}
	
}
