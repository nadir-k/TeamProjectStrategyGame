package game.teamproject.game.states.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.states.BattleState;
import game.teamproject.game.states.GameStateManager;
import game.teamproject.game.states.State;

public class Chapter1Scene3 extends State {
    private Viewport viewport;
    private Stage stage;
    protected BitmapFont white;

    private Texture bgTex;
    private Image background;
    private Label label, label2, label3, label4;

    private boolean finished = false;

    public Chapter1Scene3(GameStateManager gsm) {
        super(gsm);
        viewport = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage = new Stage (viewport, AnyaGame.batch);
        Gdx.input.setInputProcessor(stage);

        bgTex = new Texture(Gdx.files.internal("Scenes/enemies.jpeg"));
        background = new Image(bgTex);
        white = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);

        label = new Label("They are here.\n" + "A formidable enemy in her presence.", new Label.LabelStyle(white, Color.WHITE));
        label2 = new Label("The commander of the bandits. ", new Label.LabelStyle(white, Color.WHITE));
        label3 = new Label("This enemy fights like \n" + "a bull and hits swiftly like the wind. ", new Label.LabelStyle(white, Color.WHITE));
        label4 = new Label("Anya will need to take great care for this fight.", new Label.LabelStyle(white, Color.WHITE));

        label.setWrap(true);
        label2.setWrap(true);
        label3.setWrap(true);
        label4.setWrap(true);

        background.setPosition(0f, stage.getHeight() - background.getImageHeight());
        label.setPosition(AnyaGame.WIDTH/12 - 30, AnyaGame.HEIGHT-200);
        label2.setPosition(AnyaGame.WIDTH/12 - 30, AnyaGame.HEIGHT-275);
        label3.setPosition(AnyaGame.WIDTH/12 - 30, AnyaGame.HEIGHT-387);
        label4.setPosition(AnyaGame.WIDTH/12 - 30, AnyaGame.HEIGHT-450);

        background.getColor().a = 0f;
        background.addAction(Actions.sequence(Actions.fadeIn(2f), Actions.delay(14f), Actions.fadeOut(2f), Actions.run(new Runnable() {
            @Override
            public void run() {
                finished = true;
            }
        })));
        label.getColor().a = 0f;
        label.addAction(Actions.sequence(Actions.delay(2f), Actions.fadeIn(2f), Actions.delay(4f), Actions.fadeOut(2f)));

        label2.getColor().a = 0f;
        label2.addAction(Actions.sequence(Actions.delay(4f), Actions.fadeIn(2f), Actions.delay(4f), Actions.fadeOut(2f)));

        label3.getColor().a = 0f;
        label3.addAction(Actions.sequence(Actions.delay(6f), Actions.fadeIn(2f), Actions.delay(4f), Actions.fadeOut(2f)));

        label4.getColor().a = 0f;
        label4.addAction(Actions.sequence(Actions.delay(8f), Actions.fadeIn(2f), Actions.delay(4f), Actions.fadeOut(2f)));

        stage.addActor(background);
        stage.addActor(label);
        stage.addActor(label2);
        stage.addActor(label3);
        stage.addActor(label4);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        if(finished){
            gsm.set(new BattleState(gsm,"bandit","level1"));
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(bgTex, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    protected void dispose() {
        bgTex.dispose();
    }
}
