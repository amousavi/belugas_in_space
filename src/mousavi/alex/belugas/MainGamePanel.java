package mousavi.alex.belugas;

import java.util.*;

import mousavi.alex.belugas.boss.*;
import mousavi.alex.belugas.bullet.*;
import mousavi.alex.belugas.components.Speed;
import mousavi.alex.belugas.enemy.*;
import mousavi.alex.belugas.sprites.BelugaSequence;
import mousavi.alex.belugas.sprites.Laser;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private MainThread thread;
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public static int GAME_WIDTH = 600;
	public static int GAME_HEIGHT = 400;
	
	private BelugaSequence beluga;
	
	
	private int maxEnemies;
	private int enemiesAlive;
	private ArrayList<Enemy> aliveEnemyList;
	private Deque<Enemy> deadEnemyList;
	
	private int maxBullets;
	private int bulletsAlive;
	private ArrayList<Bullet> aliveBulletList;
	private Deque<Bullet> deadBulletList;
	
	
	private Boss boss;
	private int bossTimer;
	
	public MainGamePanel(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create Beluga and load bitmap
		beluga = new BelugaSequence(
				BitmapFactory.decodeResource(getResources(), R.drawable.belugasequence) 
				, 100, 100	// initial position
				, getWidth(), getHeight()	// width and height of the screen
				, 5, 6);	// FPS and number of frames in the animation
		
		Log.d("Panel", "Start");
		initEnemies(30);
		initBullet(50);
		
		boss = new Boss(BitmapFactory.decodeResource(getResources(), R.drawable.polarbear));
		bossTimer = 120;
		
		// create the game loop thread
		initThread();
		// make the GamePanel focusable so it can handle events
		
		setFocusable(true);
	}
	
	public void initThread(){
		
		thread = new MainThread(getHolder(), this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		beluga.setScreenHeight(getHeight());
		thread.setRunning(true);
		//if(thread.getState() == Thread.State.NEW)thread.start();
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		
	}
	public void pauseThreads(){
		thread.setRunning(false);
		
	}
	
	public void resumeThreads(){
		thread.setRunning(true);
	}
	
	
	
	public void stopThreads(){
		boolean retry = true;
		Log.v("thread","attempting to shut down thread");
		
		while (retry) {
			try {
				thread.join();
				
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		
		
		thread = null;
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	private static final int INVALID_POINTER_ID = -1;
	private int mActivePointerId = INVALID_POINTER_ID;
	float mLastTouchX;
	float mLastTouchY;
	int belugaTouch;
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
	    final int action = ev.getAction();
	    //Log.v("touch",""+ev.getPointerCount());
	    switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				final float x = ev.getX();
				final float y = ev.getY();
				
				mLastTouchX = x;
				mLastTouchY = y;
	
				// Save the ID of this pointer
				mActivePointerId = ev.getPointerId(0);
				//Log.v("touch","pointer id: "+ev.getPointerId(0));
				if(x < getWidth()/2){
					belugaTouch = mActivePointerId;
					beluga.setHeld(true);
					beluga.handleTouch((int)x, (int)y);
					
				} else {
					//make laser code here
	
				}
				break;
			}
			
			case MotionEvent.ACTION_POINTER_DOWN:{
				// make laser here
			}
				
			case MotionEvent.ACTION_MOVE: {
				// Find the index of the active pointer and fetch its position
				final int pointerIndex = ev.findPointerIndex(mActivePointerId);
				final float x = ev.getX(pointerIndex);
				final float y = ev.getY(pointerIndex);
				
				//final float dx = x - mLastTouchX;
				//final float dy = y - mLastTouchY;
				
				//mPosX += dx;
				//mPosY += dy;
				
				mLastTouchX = x;
				mLastTouchY = y;
				
				if(mActivePointerId == belugaTouch)
					beluga.handleTouch((int)x, (int)y);
					
				//if(x < getWidth()/2){
				//	beluga.handleTouch((int)x, (int)y);
				//}
				
				invalidate();
				break;
			}
				
			case MotionEvent.ACTION_UP: {
				final int pointerIndex = ev.findPointerIndex(mActivePointerId);
				//if((int)ev.getX(pointerIndex) < getWidth()/2)
				if(mActivePointerId == belugaTouch)
					beluga.setHeld(false);
				mActivePointerId = INVALID_POINTER_ID;
				
				break;
			}
				
			case MotionEvent.ACTION_CANCEL: {
				mActivePointerId = INVALID_POINTER_ID;
				//beluga.setHeld(false);
				break;
			}
			
			case MotionEvent.ACTION_POINTER_UP: {
				// Extract the index of the pointer that left the touch sensor
				final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
						>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
				final int pointerId = ev.getPointerId(pointerIndex);
				if (pointerId == mActivePointerId) {
					// This was our active pointer going up. Choose a new
					// active pointer and adjust accordingly.
					final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
					mLastTouchX = ev.getX(newPointerIndex);
					mLastTouchY = ev.getY(newPointerIndex);
					mActivePointerId = ev.getPointerId(newPointerIndex);
				}
				break;
			}
	    }
	    
	    return true;
	}
	
	public void initEnemies(int enemynum)
	{
		new EnemyStats(this.getContext());
		maxEnemies = enemynum;
		aliveEnemyList = new ArrayList<Enemy>(enemynum);
		deadEnemyList = new ArrayDeque<Enemy>(enemynum);
		enemiesAlive = 0;
		for (int i = 0; i < enemynum; i++)
		{
			deadEnemyList.add(new Enemy(i));
		}
	}
	
	public void initBullet(int bulletnum)
	{
		new BulletStats(this.getContext());
		maxBullets = bulletnum;
		aliveBulletList = new ArrayList<Bullet>(bulletnum);
		deadBulletList = new ArrayDeque<Bullet>(bulletnum);
		bulletsAlive = 0;
		for (int i = 0; i < bulletnum; i++)
		{
			deadBulletList.add(new Bullet(i));
		}
		
	}
	
	public Enemy spawnEnemy(int sx, int sy)
	{
		if (enemiesAlive == maxEnemies)
		{
			return null;
		}
		Enemy oo = deadEnemyList.removeLast();
		oo.spawn(EnemyStats.SNOWBALL, sx, sy);
		aliveEnemyList.add(oo);
		enemiesAlive++;
		return oo;
	}
	
	public void render(Canvas canvas) {
		
		canvas.drawColor(Color.BLACK);
		
		beluga.draw(canvas);
		
		boss.draw(canvas);

		for (Bullet bullet : aliveBulletList)
		{
			
		}
		Iterator<Enemy> ee = aliveEnemyList.iterator();
		while (ee.hasNext())
		{
			ee.next().draw(canvas);
		}
		
		
	}
	
	
	public void gameTick(long delta)
	{
		beluga.update(delta);
		for (Bullet bullet : aliveBulletList)
		{
			bullet.update();
		}
		for (int i = 0; i < enemiesAlive; i++)
		{
			Enemy oo = aliveEnemyList.get(i);
			if (oo.update())
			{
				oo.die();
				aliveEnemyList.remove(i);
				i--;
				enemiesAlive--;
				deadEnemyList.add(oo);
			}
		}
		boss.update();
		if (bossTimer > 0 && bossTimer != -1)
		{
			bossTimer--;
		}
		else 
		{
			boss.enterScreen();
			bossTimer = -1;
		}
	}

	/**
	 * This is the game update method. It iterates through all the objects
	 * and calls their update method if they have one or calls specific
	 * engine's update method.
	 */
	public void update() {
		long time = System.currentTimeMillis();
		
		gameTick(time);
		if (Math.random() < .1)
		{
			spawnEnemy(1200, (int)(Math.random() * 600));
		}
		/*
		for(int i=0; i<lasers.size();i++){
			if(lasers.get(i).getX() > getWidth()+10){
				lasers.remove(i);
			} else break;
		}
		*/
	}
	public void update(int x,int y) {
		beluga.update(System.currentTimeMillis(),x,y);
	}

	
	public Bullet createBullet(int type, int sx, int sy, int team)
	{
		if (bulletsAlive >= maxBullets)
			return null;
		Bullet oo = deadBulletList.removeFirst();
		aliveBulletList.add(oo);
		bulletsAlive++;
		return oo;
	}

	
}

	

