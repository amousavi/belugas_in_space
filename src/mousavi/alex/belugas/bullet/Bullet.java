package mousavi.alex.belugas.bullet;

import android.graphics.*;
import mousavi.alex.belugas.*;
import mousavi.alex.belugas.sprites.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 4/8/13
 * Time: 8:01 PM
 * 
 */
public class Bullet 
{
	MainGamePanel gamePanel;
	private int id;
	
	
	private int type;
	private Bitmap bitmap;
	private SpriteAnimation sprite;
	
	private double x;
	private double y;
	private int team;
	
	private boolean alive;
	
	
	private int lifeTime;
	
	public Bullet(MainGamePanel gamePanel, int id)
	{
		this.gamePanel = gamePanel;
		this.id = id;
		alive = false;
	}
	
	public void spawn(int type, int sx, int sy, int team)
	{
		this.type = type;
		this.team = team;
		x = sx;
		y = sy;
		alive = true;
		lifeTime = 300;
		
		bitmap = BulletStats.BULLET_SPRITES[type];
		sprite = new SpriteAnimation(bitmap, 0, 0);
	}
	
	public void update()
	{
		if (!alive)
			return;
		x += 5;
		if (lifeTime <= 0)
		{
			die();
		}
		else
		{
			lifeTime --;
		}
	}
	
	public void die()
	{
		alive = false;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void draw(Canvas canvas)
	{
		Paint pp = new Paint();
		pp.setColor(Color.BLUE);
		sprite.draw(canvas,(int)(x * MainGamePanel.DRAW_WIDTH_RATIO), (int)(y * MainGamePanel.DRAW_HEIGHT_RATIO), pp);
	}
}
