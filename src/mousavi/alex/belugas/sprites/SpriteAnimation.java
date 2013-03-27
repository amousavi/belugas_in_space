package mousavi.alex.belugas.sprites;

import android.graphics.*;
import android.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 3/26/13
 * Time: 4:56 PM
 * 
 */
public class SpriteAnimation 
{
	private Bitmap spriteSheet;
	private int cellWidth;
	private int cellHeight;
	private int curIndex;
	private int maxIndex;
	private double indexDelta;
	
	private int animationSpeed;
	private Rect sourceRect;

	public SpriteAnimation(Bitmap spriteSheet, int cellWidth, int cellHeight, int animationSpeed)
	{
		this.spriteSheet = spriteSheet;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		maxIndex = spriteSheet.getWidth() / cellWidth - 1;
		curIndex = 0;
		this.animationSpeed = animationSpeed;
		indexDelta = animationSpeed;
		sourceRect = new Rect(0, 0, cellWidth, cellHeight);
	}

	public void setCurIndex(int curIndex)
	{
		this.curIndex = curIndex;
	}
	
	public void update()
	{
		indexDelta -= 1;
		if (indexDelta <= 0)
		{
			
			if (curIndex >= maxIndex)
			{
				sourceRect.left = 0;
				//sourceRect.top = 0;
				sourceRect.right = cellWidth;
				curIndex = 0;
				Log.d("SpriteAnimate" , "" + spriteSheet.getHeight());
			}
			else 
			{
				sourceRect.right += cellWidth;
				sourceRect.left += cellWidth;
				curIndex ++;
			}
			indexDelta += animationSpeed;
		}
	}
	
	public void draw(Canvas canvas, int xx, int yy, Paint pp)
	{
		canvas.drawBitmap(spriteSheet, sourceRect, new Rect(xx, yy, xx + cellWidth , yy + cellHeight ), pp);
	}
}
