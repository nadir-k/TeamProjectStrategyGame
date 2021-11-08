package game.teamproject.game.states;
/**
 * Class to display menu
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.GL20;

import game.teamproject.game.AnyaGame;


public class MenuState extends State{

    private Texture background;
    private Image bg, storyModeImage, endlessModeImage, bgTitle, profileImage, helpButton;
    private Texture title;

    boolean isStory, isEndless, isSettings, isProfile, isHelp;
    boolean finished = false;

    Viewport view;
    Stage stage;
    Table table;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);

        title = new Texture("title.png");
        bgTitle = new Image(title);

        view = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(view, AnyaGame.batch);
        bg.setPosition(0f, stage.getHeight() - bg.getImageHeight());

        table = new Table();
        Gdx.input.setInputProcessor(stage);
        storyModeImage = new Image(new Texture(Gdx.files.internal("Menu/STORYBUTTON.png")));
        storyModeImage.setSize(180,90);
        storyModeImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isStory = true;
                return isStory;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isStory = false;
            }
        });

        endlessModeImage = new Image(new Texture(Gdx.files.internal("Menu/ENDLESSBUTTON.png")));
        endlessModeImage.setSize(180, 90);
        endlessModeImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isEndless = true;
                return isEndless;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isEndless = false;
            }
        });

        table.setFillParent(true);
        table.center();
        table.add(storyModeImage).size(storyModeImage.getWidth(), storyModeImage.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(endlessModeImage).size(endlessModeImage.getWidth(), endlessModeImage.getHeight()).pad(0,0,0,0);
        stage.addActor(table);

        Table table2 = new Table();
        profileImage = new Image(new Texture(Gdx.files.internal("Menu/profileicon.png")));
        profileImage.setSize(100, 100);
        profileImage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isProfile = true;
                return isProfile;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isProfile = false;
            }
        });

        table2.setFillParent(true);
        table2.center();
        table2.add(profileImage).size(profileImage.getWidth(), profileImage.getHeight()).pad(0, 700, 500, 0);
        stage.addActor(table2);

        Table table3 = new Table();
        helpButton = new Image(new Texture(Gdx.files.internal("Menu/help.png")));
        helpButton.setSize(100, 100);
        helpButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isHelp = true;
                return isHelp;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isHelp = false;
            }
        });

        table3.setFillParent(true);
        table3.center();
        table3.add(helpButton).size(helpButton.getWidth(), helpButton.getHeight()).pad(0,0,500,700);
        stage.addActor(table3);

        helpButton.getColor().a = 0f;
        helpButton.addAction(Actions.fadeIn(2f));

        profileImage.getColor().a = 0f;
        profileImage.addAction(Actions.fadeIn(2f));

        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        storyModeImage.getColor().a = 0f;
        storyModeImage.addAction(Actions.fadeIn(2f));

        endlessModeImage.getColor().a = 0f;
        endlessModeImage.addAction(Actions.fadeIn(2f));


        stage.addActor(bg);
    }

    @Override
    public void handleInput() {
        if(isStory){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new ChapterSelectionState(gsm));
                    }
                }
            })));
            storyModeImage.addAction((Actions.fadeOut(2f)));
            endlessModeImage.addAction((Actions.fadeOut(2f)));
            profileImage.addAction(Actions.fadeOut(2f));
            helpButton.addAction(Actions.fadeOut(2f));
        }

        if(isEndless){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new BattleState(gsm));
                    }
                }
            })));
            storyModeImage.addAction((Actions.fadeOut(2f)));
            endlessModeImage.addAction((Actions.fadeOut(2f)));
            profileImage.addAction(Actions.fadeOut(2f));
            helpButton.addAction(Actions.fadeOut(2f));
        }

        if(isProfile){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new TheStatsState(gsm));
                    }
                }
            })));
            storyModeImage.addAction((Actions.fadeOut(2f)));
            endlessModeImage.addAction((Actions.fadeOut(2f)));
            helpButton.addAction(Actions.fadeOut(2f));
            profileImage.addAction(Actions.fadeOut(2f));
        }

        if(isHelp){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new HelpState(gsm));
                    }
                }
            })));
            storyModeImage.addAction((Actions.fadeOut(2f)));
            endlessModeImage.addAction((Actions.fadeOut(2f)));
            profileImage.addAction(Actions.fadeOut(2f));
            helpButton.addAction(Actions.fadeOut(2f));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        /*sb.begin();
        sb.draw(background, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.draw(title, (AnyaGame.WIDTH/2) - 100, (AnyaGame.HEIGHT/2) + 80, AnyaGame.WIDTH/4, AnyaGame.HEIGHT/4);
        sb.end();
        stage.draw();*/
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage.getBatch().draw(title, (AnyaGame.WIDTH/2)-100, (AnyaGame.HEIGHT/2)+140, AnyaGame.WIDTH/4, AnyaGame.HEIGHT/4);
        stage.getBatch().end();
        stage.draw();
    }
    @Override
    public void dispose(){
        background.dispose();
        title.dispose();

    }
}
