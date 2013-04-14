package mousavi.alex.belugas.bullet;

import android.content.*;
import android.graphics.*;
import mousavi.alex.belugas.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 4/8/13
 * Time: 8:01 PM
 * 
 */
public class BulletStats 
{
	private Context context;
	
	public static final int LASER = 0;

	public static final Bitmap[] LASER_SPRITES = new Bitmap[5];


	public BulletStats(Context context)
	{
		this.context = context;
		insertBullet(LASER, R.drawable.laser);
	}

	private void insertBullet(int type, int drawIndex)
	{
		LASER_SPRITES[type] = BitmapFactory.decodeResource(context.getResources(), drawIndex);
	}
	
}
