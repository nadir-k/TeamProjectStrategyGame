package game.teamproject.game.states;
/**
 * Class to explain debuff effects
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
import game.teamproject.game.Characters.LevelSystem;

public class HelpDebuffState extends State {
    private Texture background, title;
    private Image bg, bgTitle, backButton;
    private LevelSystem levelSystem;
    protected BitmapFont font;

    boolean isBack;
    boolean finished = false;

    Viewport viewport;
    Stage stage;
    Table table;

    public HelpDebuffState(GameStateManager gsm) {
        super(gsm);
        levelSystem = new LevelSystem();
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);
        title = new Texture(Gdx.files.internal("Menu/Help/debuffhelp.png"));
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

        table.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(490,0,0,700);
        stage.addActor(table);

        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        bgTitle.getColor().a = 0f;
        bgTitle.addAction(Actions.fadeIn(2f));

        backButton.getColor().a = 0f;
        backButton.addAction(Actions.fadeIn(2f));

        showStats().draw();
        stage.addActor(bg);
    }

    public Stage showStats(){


        String debufftext = "The Debuff effect weakens enemies for a specific \n" +
                "duration. Attack enemy to apply. 3 effects: \n" +
                "                Armorshread: Attacks deal \n" + "                critical damage." + " \n" +
                "                Stun: Enemy cannot attack.\n" +
                "                Burn: Enemy takes bonus damage.";

        Stage stage = new Stage(viewport, AnyaGame.batch);
        Label label = new Label(debufftext, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label).expandX().pad(0, 30 , 0, 0);
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
        showStats().draw();
    }

    @Override
    protected void dispose() {
        background.dispose();
        title.dispose();

    }
}
