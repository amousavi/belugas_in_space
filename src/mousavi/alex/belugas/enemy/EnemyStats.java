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
	
	public static final Bitmap[] ENEMY_SPRITES = new Bitmap[5];
	
	
	public EnemyStats(Context context)
	{
		this.context = context;
		insertEnemy(SNOWBALL, R.drawable.enemy_snowball);
	}
	
	private void insertEnemy(int type, int drawIndex)
	{
		ENEMY_SPRITES[type] = BitmapFactory.decodeResource(context.getResources(), drawIndex);
	}
}
