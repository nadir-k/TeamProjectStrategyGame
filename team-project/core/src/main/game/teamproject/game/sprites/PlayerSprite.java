package game.teamproject.game.sprites;
/**
 * This class hold the player textures, animations
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.AnyaGame;
import game.teamproject.game.Characters.Player;
import game.teamproject.game.states.BattleState;
import com.badlogic.gdx.utils.Array;

public class PlayerSprite extends Sprite {
    public enum State {STANDING, NORMAL, STUN, AXE, FIRE}
    public State currentAct;
    public State previousAct;

    protected Label playerHealthLabel, playerStaminaLabel, playerSpecialAttackLabel;
    protected OrthographicCamera cam;
    protected Viewport viewport;
    protected BitmapFont black;
    protected Stage stage;

     private TextureRegion anyaStand;
    private Animation normalAttackAnimation, axeAnimation, stunAnimation, fireAnimation;

    private float stateTimer;
    private Texture healthTexture;
    private boolean dead;
    private Player stat;



    /**
     * Creates a player object which has information like textures, health meters, stamina meters.
     *
     * @see PlayerSprite , PlayerSprite#healthMeter, Texture
     */
    public PlayerSprite(BattleState battleState, Player stat){
        super(new Texture("Player/stun.png"));
        this.stat = stat;
        cam = new OrthographicCamera();
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, cam);
        stage = new Stage(viewport, AnyaGame.batch);
        black = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);
        playerHealthLabel = new Label(String.format("Health: %03d", stat.getHealth()), new Label.LabelStyle(black, Color.WHITE));
        healthTexture = new Texture("health_foreground.png");

        Table table = new Table();
        table.setFillParent(true);
        table.add(playerHealthLabel).pad(10,110,0,0);
        table.row();
        table.add(playerStaminaLabel).pad(25,110,0,0);
        table.row();
        table.add(playerSpecialAttackLabel).pad(15,110,0,0);
        table.top().left();
        stage.addActor(table);

        currentAct = State.STANDING;
        previousAct = State.STANDING;
        stateTimer = 0;

        anyaStand = new TextureRegion(new TextureAtlas("Player/stand.atlas").findRegion("stand"), 1,1, 64, 64);
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i=0; i<5; i++){
            TextureRegion playerJump = new TextureRegion(new TextureAtlas("Player/normal.atlas").findRegion("normal"),64 * i, 1, 64, 64);

            frames.add(playerJump);
        }
        frames.add(anyaStand);
        normalAttackAnimation = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 5;i++) {
            frames.add(new TextureRegion(new TextureAtlas("Player/stun.atlas").findRegion("stun"), 64 * i , 1, 64 , 64));
        }
        frames.add(anyaStand);
        stunAnimation = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 3;i++) {
            frames.add(new TextureRegion(new TextureAtlas("Player/axe.atlas").findRegion("axeAttack"), 64 * i , 1, 64 , 64));
        }
        frames.add(anyaStand);
        axeAnimation = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 3;i++) {
            frames.add(new TextureRegion(new TextureAtlas("Player/fire.atlas").findRegion("fireAttack"), 90 * i , 0, 64 , 64));
        }
        frames.add(anyaStand);
        fireAnimation = new Animation(0.1f, frames);
        frames.clear();
    }

    /**
     *
     * @return stage the current stage the player is on
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Check if the player is dead and update the health textures of the players
     * @param dt dealt time of the current time of the game
     */
    public void update(float dt){
        checkDead();
        playerHealthLabel.setText(String.format("Health: %03d", stat.getHealth()));
        setSize(200, 200);
        setRegion(getFrame(dt));
        if (currentAct.equals(State.STANDING)) {
            setPosition(150, 200);
        } else {
            setPosition(450, 200);
        }
    }

    /**
     *  Check is the player is
     */
    public void checkDead() {
        if(stat.getHealth() <= 0){
            stat.setHealth(0);
            setDead(true);
        }

    }


    /**
     * Set the current act to standing
     */
    public void setToStand(){
        currentAct = State.STANDING;
    }

    /**
     * Set the current act to jump attack
     */
    public void setToNormal() {
        currentAct = State.NORMAL;
    }

    /**
     * Set the current act to melee attack
     */
    public void setToStun() {
        currentAct = State.STUN;
    }

    /**
     * Set the current act to ranged
     */
    public void setToFire() {
        currentAct = State.FIRE;
    }

    /**
     * Set the current act to non-melee attack
     */
    public void setToAxe(){
        currentAct = State.AXE;
    }

    /**
     * Check the state if the state is a certain  move it will return all move from the sprite  sheet
     */
    public TextureRegion getFrame(float dt){
        TextureRegion region;
        if(currentAct == State.NORMAL){
            region = (TextureRegion) normalAttackAnimation.getKeyFrame(stateTimer);
        } else if(currentAct == State.STUN){
            region = (TextureRegion) stunAnimation.getKeyFrame(stateTimer);
        } else if(currentAct == State.AXE){
            region = (TextureRegion) axeAnimation.getKeyFrame(stateTimer);
        } else if(currentAct == State.FIRE){
            region = (TextureRegion) fireAnimation.getKeyFrame(stateTimer);
        } else {
            region = anyaStand;
        }

        stateTimer = currentAct == previousAct ? stateTimer + dt: 0;
        previousAct = currentAct;
        return region;
    }

    /**
     * @return <code>Texture</code> the players health meter
     */
    public Texture getHealthTexture() {
        return healthTexture;
    }

    /**
     *
     * @return dead boolean which is true if the player is dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     *
     * @param dead set the boolean to true or false depending if the player is dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

}
