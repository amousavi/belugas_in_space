package mousavi.alex.belugas.enemy;

import android.content.*;
import android.graphics.*;
import mousavi.alex.belugas.*;

import android.graphics.BitmapFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 3/26/13
 * Time: 3:27 PM
 * 
 */
public class EnemyStats 
{
	private Context context;
	
	public static final int SNOWBALL = 0;
	public static final int LASER_SHARK = 1;
	
	public static final int ENEMY_TYPES = 5;
	public static final Bitmap[] ENEMY_SPRITES = new Bitmap[ENEMY_TYPES];
	public static final int[] ENEMY_SPRITEFRAMES = new int[ENEMY_TYPES];
	public static final int[] ENEMY_SPEED = new int[ENEMY_TYPES];
	
	
	public EnemyStats(Context context)
	{
		this.context = context;
		insertEnemy(SNOWBALL, R.drawable.enemy_snowball, 2);
		insertEnemy(LASER_SHARK, R.drawable.enemy_laser_shark, 0);
	}
	
	private void insertEnemy(int type, int drawIndex, int frames)
	{
		ENEMY_SPRITES[type] = BitmapFactory.decodeResource(context.getResources(), drawIndex);
		ENEMY_SPRITEFRAMES[type] = frames;
	}
}
