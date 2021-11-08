package game.teamproject.game.states;
/**
 * Class to display player lost
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.teamproject.game.AnyaGame;
import save.Save;

public class GameOverState extends State {
    protected Texture gameOver;
    protected boolean  playIsPressed;
    Viewport view;
    Stage stage;
    Table table;
    private String level;
    private String type;
    private int score;
    public GameOverState(GameStateManager gsm, String level, String type, int score) {
        super(gsm);
        gameOver = new Texture("gameover.jpg");
        view = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(view, AnyaGame.batch);
        table = new Table();
        this.level = level;
        this.type = type;
        Gdx.input.setInputProcessor(stage);
        final Image playbutton = new Image(new Texture("play.png"));
        playbutton.setSize(70, 70);
        playbutton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playIsPressed = true;
                return playIsPressed;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playIsPressed =  false;
            }
        });
        table.setFillParent(true);
        table.center();
        table.add(playbutton).size(playbutton.getWidth(), playbutton.getHeight()).padTop(AnyaGame.HEIGHT/4+70);
        stage.addActor(table);
    }

    private void restartLevel() {
            gsm.set(new MenuState(gsm));
            //gsm.set(new BattleState(gsm, type , level));

    }

    @Override
    protected void handleInput() {
        if(playIsPressed){
            Save.getInstance().save();
            restartLevel();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(gameOver,0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.end();
        stage.draw();
    }

    @Override
    protected void dispose() {

    }
}
