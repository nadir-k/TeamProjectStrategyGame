package game.teamproject.game.states;
/**
 * Class to help users with upgrades
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.teamproject.game.AnyaGame;

public class HelpUpgradesState extends State {
    private Texture background, title;
    private Image bg, bgTitle, backButton, lavaSlash, lightWave, steelBreak;
    protected BitmapFont font;
    private Label label, label2, label3, label4, label5, label6, label7;


    boolean isBack;
    boolean finished = false;

    Viewport viewport;
    Stage stage;
    Table table;
    Table table2;

    public HelpUpgradesState(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);
        title = new Texture(Gdx.files.internal("Menu/Help/upgradeshelp.png"));
        bgTitle = new Image(title);
        backButton = new Image(new Texture(Gdx.files.internal("Upgrade/backbutton.png")));

        viewport = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, AnyaGame.batch);
        bg.setPosition(0, stage.getHeight() - bg.getImageHeight());
        font = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);


        table = new Table();
        Gdx.input.setInputProcessor(stage);
        backButton.setSize(100, 100);
        backButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isBack = true;
                return isBack;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isBack = false;
            }
        });

        table.setFillParent(true);
        table.center();

        table.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(500,0,0,700);
        stage.addActor(table);

        table2 = new Table();
        lavaSlash = new Image(new Texture(Gdx.files.internal("Upgrade/fire.png")));
        steelBreak = new Image(new Texture(Gdx.files.internal("Upgrade/armorbreak.png")));
        lightWave = new Image(new Texture(Gdx.files.internal("Upgrade/stun.png")));

        label = new Label("Select one icon to apply an Upgrade", new Label.LabelStyle(font, Color.BLACK));
        label.setWrap(true);
        label.setPosition(AnyaGame.WIDTH/12 + 30, AnyaGame.HEIGHT-190);

        label2 = new Label("Lava" + "\n" + "Slash", new Label.LabelStyle(font, Color.BLACK));
        label2.setWrap(true);
        label2.setPosition(AnyaGame.WIDTH/12 + 170, AnyaGame.HEIGHT - 400);

        label3 = new Label("Armor" + "\n" + "Break", new Label.LabelStyle(font, Color.BLACK));
        label3.setWrap(true);
        label3.setPosition(AnyaGame.WIDTH/12 + 280, AnyaGame.HEIGHT - 400);

        label4 = new Label("Light" + "\n" + "Wave", new Label.LabelStyle(font, Color.BLACK));
        label4.setWrap(true);
        label4.setPosition(AnyaGame.WIDTH/12 + 400, AnyaGame.HEIGHT - 400);

        label5 = new Label("Chance: Chance of the debuff applying"
        + "\n" + "Duration: Time length of the debuff"
        + "\n" + "Effect: Strength of the debuff", new Label.LabelStyle(font, Color.WHITE));
        label5.setWrap(true);
        label5.setPosition(AnyaGame.WIDTH/12 + 50, AnyaGame.HEIGHT - 525);

        lavaSlash.setSize(100, 100);
        steelBreak.setSize(100, 100);
        lightWave.setSize(100, 100);

        table2.setFillParent(true);
        table2.center();
        table2.add(lavaSlash).size(lavaSlash.getWidth(), lavaSlash.getHeight()).pad(0, 0, 50, 10);
        table2.add(steelBreak).size(steelBreak.getWidth(), steelBreak.getHeight()).pad(0, 0, 50, 10);
        table2.add(lightWave).size(lightWave.getWidth(), lightWave.getHeight()).pad(0,0,50,10);
        stage.addActor(table2);

        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        bgTitle.getColor().a = 0f;
        bgTitle.addAction(Actions.fadeIn(2f));

        backButton.getColor().a = 0f;
        backButton.addAction(Actions.fadeIn(2f));

        showUpgrades().draw();
        stage.addActor(bg);
        stage.addActor(label);
        stage.addActor(label2);
        stage.addActor(label3);
        stage.addActor(label4);
        stage.addActor(label5);
    }


    public Stage showUpgrades(){
        //chance duration effect
        String chance = "Chance: "  +"\n" + "The likelihood of an attack causing damage";
        String duration = "Duration: "  +"\n" + "The amount of time the attack cannot be used";
        String effect = "Effect: "  +"\n" + "The max amount of damage of an attack";


        Stage stage = new Stage(viewport, AnyaGame.batch);
        Label label = new Label(chance, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label).expandX().pad(50, 0 , 0, 0);
        stage.addActor(table);

        return stage;
    }

    @Override
    protected void handleInput() {
        if(isBack){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new HelpState(gsm));
                    }
                }
            })));
            backButton.addAction(Actions.fadeOut(2f));
            bgTitle.addAction(Actions.fadeOut(2f));
            //gsm.set(new MenuState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage.getBatch().draw(title, (AnyaGame.WIDTH/2)-200, (AnyaGame.HEIGHT/2)+140, AnyaGame.WIDTH/2, AnyaGame.HEIGHT/4);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    protected void dispose() {
        background.dispose();
        title.dispose();

    }
}
