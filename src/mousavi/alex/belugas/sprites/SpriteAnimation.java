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

	public SpriteAnimation(Bitmap spriteSheet, int maxIndex, int animationSpeed)
	{
		this.spriteSheet = spriteSheet;
		this.maxIndex = maxIndex;
		cellWidth = spriteSheet.getWidth() / (maxIndex + 1);
		cellHeight = spriteSheet.getHeight();
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

	public int getCellHeight()
	{
		return cellHeight;
	}

	public int getCellWidth()
	{
		return cellWidth;
	}
}
