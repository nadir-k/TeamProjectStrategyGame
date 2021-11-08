package game.teamproject.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.teamproject.game.Characters.Player;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.states.GameStateManager;
import game.teamproject.game.states.MenuState;
import save.Save;
import save.Statistics;


/**
 * This class initialises all information about the game
 */
public class AnyaGame extends ApplicationAdapter {
	//Screen aspect ratio

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final float PPM = 100;
	public static final short ANYA_BIT = 2;
	public static final short ENEMY_BIT = 4;
	public static SpriteBatch batch;
	private GameStateManager gsm;
	private Save save;
	private Statistics statistics;
	private Player player;
	private DebuffDatabase debuffDatabase;
	public static AssetManager manager;

	/**
	 * Create two field that hold the all the sprite of the game and all the state of the game
	 */
	@Override
	public void create () {
		save = Save.getInstance();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		manager = new AssetManager();
		manager.load("audio/music/themeSong.ogg", Music.class);
		manager.load("audio/sounds/Blip_Select2.wav", Sound.class);
		manager.load("audio/sounds/Hit_Hurt.wav", Sound.class);
		manager.load("audio/sounds/Hit_Hurt5.wav", Sound.class);
		manager.load("audio/sounds/Randomize4.wav", Sound.class);
		manager.finishLoading();
		gsm.push(new MenuState(gsm));
		save.save();
		save.load();
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	/**
	 * Render
	 */
	@Override
	public void render () {
		super.render();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
