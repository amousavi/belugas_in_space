package mousavi.alex.belugas.bullet;/**
 * Created with IntelliJ IDEA.
 * User: Fricken Hamster
 * Date: 4/13/13
 * Time: 7:35 PM
 * 
 */
public class QueueBullet 
{
	private int type;
	private int spawnX;
	private int spawnY;
	private int team;

	public QueueBullet(int type, int spawnX, int spawnY, int team)
	{
		this.type = type;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.team = team;
	}

	public int getType()
	{
		return type;
	}

	public int getSpawnX()
	{
		return spawnX;
	}

	public int getSpawnY()
	{
		return spawnY;
	}

	public int getTeam()
	{
		return team;
	}
}
