package game.teamproject.game.sprites;
/**
 * Class that hold the animation of the enemy and the texture of the enemy
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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.Characters.Enemy;
import game.teamproject.game.states.BattleState;
import com.badlogic.gdx.utils.Array;

public class EnemySprite extends Sprite implements Disposable {
    public enum Act{STANDING, ATTACK}
    public Act currentAct, previousAct;
    protected TextureRegion enemyStand;
    public Animation attackAnimation;
    public float stateTimer;

    private boolean dead;
    protected Label enemyHealthLabel, enemyStaminaLabel;
    private Texture enemyHealthMeterTexture;
    protected OrthographicCamera cam;
    protected Viewport viewport;
    protected BitmapFont black;
    protected Stage stage;
    private Enemy stat;




    /**
     * Constructs an <code>EnemySprite</code> object to give it necessary information from the get-go
     * like Health, Stamina etc.
     */
    public EnemySprite(BattleState battleState, Enemy enemy) {
        super(new Texture("Player/stun.png"));
        this.stat = enemy;
        dead = false;
        enemyHealthMeterTexture = new Texture("health_foreground.png");
        cam = new OrthographicCamera();
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, cam);
        stage = new Stage(viewport, AnyaGame.batch);
        black = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);
        //notification = new Label("EnemySprite is dead a new enemy will appear shortly ",new Label.LabelStyle(black, Color.BROWN));
        enemyHealthLabel = new Label(String.format("Health: %03d", stat.getHealth()), new Label.LabelStyle(black, Color.WHITE));
        Table table = new Table();
        table.setFillParent(true);
        table.add(enemyHealthLabel).pad(10, 0, 0, 110);
        table.row();
        table.add(enemyStaminaLabel).pad(25, 0, 0, 110);
        table.top().right();
        stage.addActor(table);

        currentAct = Act.STANDING;
        previousAct = Act.STANDING;
        stateTimer = 0;
        enemyStand = new TextureRegion((new TextureAtlas("bandit/stand.atlas")).findRegion("stand"), 1, 1, 64, 64);
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i=0; i<4; i++){
            frames.add(new TextureRegion( (new TextureAtlas("bandit/attack.atlas")).findRegion("attack"), 64*i, 1 ,64, 64));
        }
        frames.add(enemyStand);
        attackAnimation = new Animation(0.1f, frames);
        frames.clear();
    }


    public EnemySprite(BattleState battleState, Enemy enemy, String enemyType) {
        super(new Texture("Player/stun.png"));

        this.stat = enemy;
        dead = false;
        enemyHealthMeterTexture = new Texture("health_foreground.png");
        cam = new OrthographicCamera();
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, cam);
        stage = new Stage(viewport, AnyaGame.batch);
        black = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);
        enemyHealthLabel = new Label(String.format("Health: %03d", stat.getHealth()), new Label.LabelStyle(black, Color.WHITE));
        Table table = new Table();
        table.setFillParent(true);
        table.add(enemyHealthLabel).pad(10, 0, 0, 110);
        table.row();
        table.add(enemyStaminaLabel).pad(25, 0, 0, 110);
        table.top().right();
        stage.addActor(table);
        currentAct = Act.STANDING;
        previousAct = Act.STANDING;
        createAnimation(enemyType);
    }


    //Constructs an EnemySprite object to give it necessary information that will be required
    public void createAnimation(String enemyType){
        enemyStand = new TextureRegion((new TextureAtlas(Gdx.files.internal(enemyType + "/stand.atlas"))).findRegion("stand"), 1, 1, 64, 64);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        if(enemyType.equals("zuzzanah")){
            for(int i=0; i<4; i++){
                frames.add(new TextureRegion((new TextureAtlas(Gdx.files.internal(enemyType + "/attack.atlas"))).findRegion("attack"), 77*i, 1 ,64, 64));
            }
        } else if(enemyType.equals("hunter")){
            for(int i=0; i<3; i++){
                frames.add(new TextureRegion((new TextureAtlas(Gdx.files.internal(enemyType + "/attack.atlas"))).findRegion("attack"), 64*i, 1 ,64, 64));
            }
        } else{
            for(int i=0; i<4; i++) {
                frames.add(new TextureRegion((new TextureAtlas(Gdx.files.internal(enemyType + "/attack.atlas"))).findRegion("attack"), 64 * i, 1, 64, 64));
            }
        }
        frames.add(enemyStand);
        attackAnimation = new Animation(0.1f, frames);
        frames.clear();
    }

    /**
     *
     * @return Health textures of what the Hp bar look like
     */
    public Texture getEnemyHealthMeterTexture() {
        return enemyHealthMeterTexture;
    }

    /**
     *
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Check if the enemy hp is less then zero and update the enemy health bar
     * @param dt delta time of the game
     */
    public void update(float dt) {
        checkDead();
        enemyHealthLabel.setText(String.format("Health: %03d", stat.getHealth()));
        setSize(200, 190);
        setRegion(getFrame(dt));
        if (currentAct.equals(Act.STANDING)) {
            setPosition(500, 200);
        } else {
            setPosition(200, 200);
        }
    }


    /**
     * Check if the heath is less the zero if it is then it will set it to zero and set the boolean if the player is dead to true
     */

    public void checkDead() {
        if(stat.getHealth() <= 0){
            stat.setHealth(0);
            dead = true;
        }

    }

    /**
     * Check what button has the user click and depending on which one the user click a different animation will appear
     * @param dt delta time of the game
     * @return The animation that relate to the jump animation
     */
    public TextureRegion getFrame(float dt){
        TextureRegion region;


        if(currentAct == Act.ATTACK){
            region =(TextureRegion) attackAnimation.getKeyFrame(stateTimer);
        } else {
            region = enemyStand;
        }
        stateTimer = currentAct == previousAct ? stateTimer + dt: 0;
        previousAct = currentAct;
        return region;
    }

    /**
     * Set the current act to non-melee attack
     */
    public void setToAttack(){
        currentAct = Act.ATTACK;
    }



    /**
     * Set the current act to standing
     */
    public void setToStand(){
        currentAct = Act.STANDING;
    }

    /**
     * Get rid of all the texture that is not being used
     */
    @Override
    public void dispose() {



    }

    /**
     *
     * @return dead a boolean that is true when the player is dead and false if the player is alive
     */
    public boolean isDead() {
        return dead;
    }


}


