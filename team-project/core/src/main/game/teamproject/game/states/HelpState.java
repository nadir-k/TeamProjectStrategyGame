package game.teamproject.game.states;
/**
 * Class to display available help
 */
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.Characters.LevelSystem;
import game.teamproject.game.Characters.Player;
import game.teamproject.game.sprites.EnemySprite;

public class HelpState extends State {
    private Texture background, title;

    private Image bg, bgTitle, backButton, movesHelpImage, upgradesHelpImage, upgradesDebuffImage;
    private Player player;
    private LevelSystem levelSystem;
    protected BitmapFont font;

    boolean isBack;
    boolean isMovesHelp;
    boolean isUpgradesHelp;
    boolean isDebuffHelp;
    boolean finished = false;

    Viewport viewport;
    Stage stage;
    Table table;

    public HelpState(GameStateManager gsm) {
        super(gsm);
        levelSystem = new LevelSystem();
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);
        title = new Texture(Gdx.files.internal("Menu/Help/help.png"));
        bgTitle = new Image(title);

        this.player = player;

        backButton = new Image(new Texture(Gdx.files.internal("Upgrade/backbutton.png")));
        viewport = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, AnyaGame.batch);
        bg.setPosition(0, stage.getHeight() - bg.getImageHeight());
        font = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);



        table = new Table();
        Gdx.input.setInputProcessor(stage);
        movesHelpImage = new Image(new Texture(Gdx.files.internal("Menu/Help/movesbutton.png")));
        movesHelpImage.setSize(180,90);
        movesHelpImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isMovesHelp = true;
                return isMovesHelp;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isMovesHelp = false;
            }
        });

        upgradesHelpImage = new Image(new Texture(Gdx.files.internal("Menu/Help/upgradesbutton.png")));
        upgradesHelpImage.setSize(180, 90);
        upgradesHelpImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isUpgradesHelp = true;
                return isUpgradesHelp;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isUpgradesHelp = false;
            }
        });

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

        upgradesDebuffImage = new Image(new Texture(Gdx.files.internal("Menu/Help/debuff.png")));
        upgradesDebuffImage.setSize(180, 90);
        upgradesDebuffImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isDebuffHelp = true;
                return isDebuffHelp;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isDebuffHelp = false;
            }
        });

        table.setFillParent(true);
        table.center();


        table.add(movesHelpImage).size(movesHelpImage.getWidth(), movesHelpImage.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(upgradesHelpImage).size(upgradesHelpImage.getWidth(), upgradesHelpImage.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(upgradesDebuffImage).size(upgradesDebuffImage.getWidth(), upgradesDebuffImage.getHeight()).pad(0,0,0,0);


        stage.addActor(table);

        Table table2 = new Table();

        table2.setFillParent(true);
        table2.center();
        table2.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(500,0,0,700);
        stage.addActor(table2);

        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        bgTitle.getColor().a = 0f;
        bgTitle.addAction(Actions.fadeIn(2f));

        movesHelpImage.getColor().a = 0f;
        movesHelpImage.addAction(Actions.fadeIn(2f));

        upgradesDebuffImage.getColor().a = 0f;
        upgradesDebuffImage.addAction(Actions.fadeIn(2f));

        upgradesHelpImage.getColor().a = 0f;
        upgradesHelpImage.addAction(Actions.fadeIn(2f));

        backButton.getColor().a = 0f;
        backButton.addAction(Actions.fadeIn(2f));

        stage.addActor(bg);
    }


    @Override
    protected void handleInput() {
        if(isUpgradesHelp){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new HelpUpgradesState(gsm));
                    }
                }
            })));
            upgradesHelpImage.addAction((Actions.fadeOut(2f)));
            movesHelpImage.addAction((Actions.fadeOut(2f)));
            backButton.addAction((Actions.fadeOut(2f)));
            bgTitle.addAction(Actions.fadeOut(2f));
            upgradesDebuffImage.addAction(Actions.fadeOut(2f));
        }

        if(isMovesHelp){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new HelpMovesState(gsm));
                    }
                }
            })));
            movesHelpImage.addAction((Actions.fadeOut(2f)));
            upgradesHelpImage.addAction((Actions.fadeOut(2f)));
            backButton.addAction((Actions.fadeOut(2f)));
            bgTitle.addAction(Actions.fadeOut(2f));
            upgradesDebuffImage.addAction(Actions.fadeOut(2f));
        }

        if(isDebuffHelp){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new HelpDebuffState(gsm));
                    }
                }
            })));
            backButton.addAction(Actions.fadeOut(2f));
            bgTitle.addAction(Actions.fadeOut(2f));
            upgradesHelpImage.addAction(Actions.fadeOut(2f));
            upgradesDebuffImage.addAction(Actions.fadeOut(2f));
            movesHelpImage.addAction(Actions.fadeOut(2f));
        }

        if(isBack){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new MenuState(gsm));
                    }
                }
            })));
            backButton.addAction(Actions.fadeOut(2f));
            bgTitle.addAction(Actions.fadeOut(2f));
            upgradesHelpImage.addAction(Actions.fadeOut(2f));
            upgradesDebuffImage.addAction(Actions.fadeOut(2f));
            movesHelpImage.addAction(Actions.fadeOut(2f));
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
