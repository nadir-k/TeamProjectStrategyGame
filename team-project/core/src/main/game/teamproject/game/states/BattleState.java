package game.teamproject.game.states;
/**
 * Class for main Battle State (Endless mode and Story mode)
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.teamproject.game.AnyaGame;
import game.teamproject.game.BattleLogic;
import game.teamproject.game.Characters.LevelSystem;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.controller.Controller;
import game.teamproject.game.sprites.EnemySprite;
import game.teamproject.game.sprites.PlayerSprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.PooledLinkedList;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.teamproject.game.Notification;
import game.teamproject.game.Characters.Enemy;
import game.teamproject.game.Characters.Player;
import save.Save;

public class BattleState extends State implements Screen {

    private Notification notification;
    private PlayerSprite playerSprite;
    private EnemySprite enemySprite;
    private Player player;
    private Enemy enemy;
    private LevelSystem levelSystem;
    private World world;
    protected OrthographicCamera cam;
    protected Viewport viewport;
    private float count;
    private Texture background;
    private Texture uiBackground;
    private Texture playerBar;
    private Texture enemyBar;
    private Controller controller;
    private BattleLogic battleLogic;
    private boolean battleOver;
    private boolean playerTurn;
    private Music music;
    private int enemyTimer;
    private String level;
    private String enemyType;
    private int currentScore;
    private int spawnTimer;
    public BattleState(GameStateManager gsm, String enemyType, String levelBackground) {
        super(gsm);
        level = levelBackground;
        this.enemyType = enemyType;
        cam = new OrthographicCamera();
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, cam);
        levelSystem = new LevelSystem();
        player = Player.getInstance();
        player.setHeathToMax();
        enemy = new Enemy();
        currentScore = 0;
        spawnTimer = 0;
        playerSprite = new PlayerSprite(this, player);
        enemySprite = new EnemySprite(this, enemy, enemyType);
        background = new Texture(Gdx.files.internal(levelBackground + ".png"));
        uiBackground = new Texture("dialog_box.png");
        playerBar = new Texture("Battle/health_background.png");
        enemyBar = new Texture("Battle/health_background.png");
        world = new World(new Vector2(0, 0), true);
        notification = new Notification();
        controller = new Controller();
        battleLogic = new BattleLogic();
        battleOver = false;
        playerTurn = true;


        music = AnyaGame.manager.get("audio/music/themeSong.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }

    public BattleState(GameStateManager gsm) {
        this(gsm, "bandit", "endless");
    }

    private boolean isEndless() {
        return level.equals("endless");
    }


    private void postBattle() {
        if (enemySprite.isDead()) {
            gsm.set(new VictoryState(gsm, level));
            }
            if (playerSprite.isDead()) {
                if (currentScore > player.getStatistics().getScore()) {
                    player.getStatistics().setScore(currentScore);
                    Save.getInstance().save();
                }
                gsm.set(new GameOverState(gsm, level, enemyType, currentScore));

            }
        }

        public void respawnEnemy () {
            if (level.equals("endless")) {
                if (enemySprite.isDead()) {
                    currentScore++;
                    enemy = new Enemy();
                    enemySprite = new EnemySprite(this, enemy, enemyType);
                }

            }
            spawnTimer = 0;
        }


        private void playerTurn () {
            if (playerTurn) {
                if (controller.isRegularAttack()) {
                    battleLogic.useMove(player.getMoveSet(), Move.NORMAL, enemy);
                    playerSprite.setToNormal();
                    playerTurn = !playerTurn;

                    AnyaGame.manager.get("audio/sounds/Blip_Select2.wav", Sound.class).play();
                } else if (controller.isArmorShreadAttack()) {
                    battleLogic.useMove(player.getMoveSet(), Move.ARMORBREAK, enemy);
                    playerSprite.setToAxe();
                    playerTurn = !playerTurn;
                } else if (controller.isStunAttack()) {
                    battleLogic.useMove(player.getMoveSet(), Move.STUN, enemy);
                    playerSprite.setToStun();
                    playerTurn = !playerTurn;
                } else if (controller.isFireAttack()) {
                    battleLogic.useMove(player.getMoveSet(), Move.FIRE, enemy);
                    playerSprite.setToFire();
                    playerTurn = !playerTurn;
                }

            }
        }

        private void battle () {

            if (!playerTurn) {
                enemyTurn();
            }
            if (playerTurn) {
                playerTurn();
                enemyTimer = 0;
            }
        }

        private void enemyTurn () {
            if (enemyTimer == 3) {
                playerSprite.setToStand();
            }
            if (enemyTimer == 4) {
                enemySprite.setToAttack();
            }

            if (enemyTimer == 5) {
                enemySprite.setToStand();
                if (enemy.getCurrentDebuffs().containsKey(Debuff.BURNED)) {
                    System.out.println("Enemy take damage from being burned");
                    enemy.setHealth(enemy.getHealth() - DebuffDatabase.getInstance().getDebuffDetails(Debuff.BURNED).getValue());
                }
                if (!enemy.getCurrentDebuffs().containsKey(Debuff.STUNNED)) {
                    battleLogic.useMove(enemy.getMoveSet(), Move.NORMAL, player);

                    enemySprite.setToStand();
                }
                checkMoveSetCooldown();
                enemy.reduceAllDebuffsDuration();
                player.reduceSkillCooldown();
                playerTurn = !playerTurn;
            }
        }


        @Override
        protected void handleInput () {
            playerTurn();
        }

        @Override
        protected void update ( float dt){
            handleInput();
            count += dt;
            if (count >= 1) {
                if (!playerTurn) {
                    enemyTimer++;

                }
                if(enemySprite.isDead() && isEndless()) {
                    spawnTimer++;
                }
                count = 0;

            }

            playerSprite.update(dt);
            enemySprite.update(dt);
            if (spawnTimer == 2) {
                enemySprite.dispose();;
            }
            if (spawnTimer == 4) {
                respawnEnemy();
            }
            if (isEndless()) {
                if (!playerSprite.isDead()) {
                    battle();
                }
                else {
                    postBattle();
                }
            }
            if (!isEndless()) {
                if (!playerSprite.isDead() && !enemySprite.isDead()) {
                    battle();

                } else {
                    postBattle();
                }
            }

        }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(background, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.draw(uiBackground, -25, 0, AnyaGame.WIDTH + 55, AnyaGame.HEIGHT / 3);
        sb.draw(playerBar, 0, 533, 350, 70);
        sb.draw(enemyBar, 450, 533, 350, 70);
        playerSprite.draw(sb);
        sb.draw(playerSprite.getHealthTexture(), 0, 533,(player.getHealth() * (AnyaGame.WIDTH / 2 - 50)) / 100 ,70 );
        if (!enemySprite.isDead()) {
            enemySprite.draw(sb);
        }
        sb.draw(enemySprite.getEnemyHealthMeterTexture(), ((AnyaGame.WIDTH / 2) + 50) + (350 - ((AnyaGame.WIDTH / 2 - 50) * enemy.getHealth() / 100)),533, ((AnyaGame.WIDTH / 2 - 50) * enemy.getHealth() / 100), 70);


        sb.end();

        controller.draw();
        playerSprite.getStage().draw();
        enemySprite.getStage().draw();

        if (playerTurn) {
            notification.getPlayerStage().draw();
            if (enemySprite.isDead()) {
                notification.getPlayerStage().dispose();
                notification.getStage().draw();
            }
        } else {
            notification.getEnemyStage().draw();
        }




        if (!playerTurn) {
            notification.logTurn(enemy, battleLogic).draw();
        } else {
            notification.logTurn(enemy, battleLogic).dispose();
        }
    }

    private void checkMoveSetCooldown() {
        for (Move move : player.getMoveSet().getMoves().keySet()) {
            int moveCD = player.getMoveSet().getMove(move).getCooldown();
            controller.handleCooldown(moveCD, move);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }


    @Override
    public void dispose() {

    }

}


