package game.teamproject.game.states.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.states.GameStateManager;
import game.teamproject.game.states.State;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

public class IntroState extends State {
    private Viewport viewport;
    private Stage stage;
    protected BitmapFont white;

    private Label intro1;
    private Label intro2;
    private Label intro3;

    private boolean finished = false;


    public IntroState(GameStateManager gsm) {
        super(gsm);

        viewport = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage = new Stage (viewport, AnyaGame.batch);
        Gdx.input.setInputProcessor(stage);
        white = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);

        intro1 = new Label("An ACE production.", new Label.LabelStyle(white, Color.WHITE));
        intro1.setFontScale(1f, 1f);

        intro2 = new Label("Produced by the Team Project", new Label.LabelStyle(white, Color.WHITE));
        intro2.setFontScale(1f, 1f);

        intro3 = new Label("ANYA", new Label.LabelStyle(white, Color.WHITE));
        intro3.setFontScale(1f, 1f);

        intro1.setPosition(AnyaGame.WIDTH/4 + 50, AnyaGame.HEIGHT/2);
        intro2.setPosition(AnyaGame.WIDTH/5 + 30, AnyaGame.HEIGHT/2);
        intro3.setPosition(AnyaGame.WIDTH/2 - 20, AnyaGame.HEIGHT/2);

        intro1.getColor().a = 0f;
        intro1.addAction(Actions.sequence(Actions.delay(2f), Actions.fadeIn(0.01f), Actions.delay(4f), Actions.fadeOut(0.01f)));

        intro2.getColor().a = 0f;
        intro2.addAction(Actions.sequence(Actions.delay(8.02f), Actions.fadeIn(0.01f), Actions.delay(4f), Actions.fadeOut(0.01f)));

        intro3.getColor().a = 0f;
        intro3.addAction(Actions.sequence(Actions.delay(14.04f), Actions.fadeIn(0.01f), Actions.delay(4f), Actions.fadeOut(0.01f), Actions.delay(2f), run(new Runnable() {
            @Override
            public void run() {
                finished = true;
            }
        })));

        stage.addActor(intro1);
        stage.addActor(intro2);
        stage.addActor(intro3);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        if(finished){
            gsm.set(new IntroScene1(gsm));
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    protected void dispose() {

    }
}
