package mousavi.alex.belugas.sprites;

import mousavi.alex.belugas.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Laser {

	private int x;
	private int y;
	Bitmap bitmap;
	
	public Laser(Bitmap bitmap, int belugax, int belugay){
		this.bitmap = bitmap;
		x = belugax;
		y = belugay;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}
	
	public void update(long gametime){
		x += 5;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
