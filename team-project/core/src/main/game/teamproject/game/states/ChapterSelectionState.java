package game.teamproject.game.states;
/**
 * Class to display chapter selection options
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import game.teamproject.game.states.scenes.Chapter1Scene1;
import game.teamproject.game.states.scenes.Chapter2Scene1;
import game.teamproject.game.states.scenes.Chapter3Scene1;
import game.teamproject.game.states.scenes.Chapter4Scene1;
import game.teamproject.game.states.scenes.Chapter5Scene1;


public class ChapterSelectionState extends State {
    private Texture background, title;
    private Image bg, theTitle, level1, level2, level3, level4, level5, backButton;

    boolean isLevel1, isLevel2, isLevel3, isLevel4, isLevel5, isBack;
    boolean finished = false;

    Viewport view;
    Stage stage;
    Table table;

    protected ChapterSelectionState(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("Chapters/test.png"));
        bg = new Image(background);


        title = new Texture(Gdx.files.internal("Chapters/chapterselection.png"));
        theTitle = new Image(title);

        view = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(view, AnyaGame.batch);
        bg.setPosition(0f, stage.getHeight() - bg.getImageHeight());

        table = new Table();
        Table table1 = new Table();
        Gdx.input.setInputProcessor(stage);
        level1 = new Image(new Texture(Gdx.files.internal("Chapters/chapter1.png")));
        level1.setSize(180, 90);
        level1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isLevel1 = true;
                return isLevel1;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isLevel1 = false;
            }
        });

        level2 = new Image(new Texture(Gdx.files.internal("Chapters/chapter2.png")));
        level2.setSize(180, 90);
        level2.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isLevel2 = true;
                return isLevel2;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isLevel2 = false;
            }
        });

        level3 = new Image(new Texture(Gdx.files.internal("Chapters/chapter3.png")));
        level3.setSize(180, 90);
        level3.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isLevel3 = true;
                return isLevel3;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isLevel3 = false;
            }
        });

        level4 = new Image(new Texture(Gdx.files.internal("Chapters/chapter4.png")));
        level4.setSize(180, 90);
        level4.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isLevel4 = true;
                return isLevel4;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isLevel4 = false;
            }
        });

        level5 = new Image(new Texture(Gdx.files.internal("Chapters/chapter5.png")));
        level5.setSize(180, 90);
        level5.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isLevel5 = true;
                return isLevel5;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isLevel5 = false;
            }
        });

        backButton = new Image(new Texture(Gdx.files.internal("Upgrade/backbutton.png")));
        backButton.setSize(25,25);
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

        table.add(level1).size(level1.getWidth(), level1.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(level2).size(level2.getWidth(), level2.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(level3).size(level3.getWidth(), level3.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(level4).size(level4.getWidth(), level4.getHeight()).pad(0,0,0,0);
        table.row();
        table.add(level5).size(level5.getWidth(), level5.getHeight()).pad(0,0,0,0);
        table1.setFillParent(true);
        table1.center();
        table1.add(backButton).size(100,100).pad(500,0,0,700);
        table.setPosition(table.getX(), table.getY()-50);
        stage.addActor(table);
        stage.addActor(table1);
        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        theTitle.getColor().a = 0f;
        theTitle.addAction(Actions.fadeIn(2f));

        level1.getColor().a = 0f;
        level1.addAction(Actions.fadeIn(2f));

        level2.getColor().a = 0f;
        level2.addAction(Actions.fadeIn(2f));

        level3.getColor().a = 0f;
        level3.addAction(Actions.fadeIn(2f));

        level4.getColor().a = 0f;
        level4.addAction(Actions.fadeIn(2f));

        level5.getColor().a = 0f;
        level5.addAction(Actions.fadeIn(2f));

        backButton.getColor().a = 0f;
        backButton.addAction(Actions.fadeIn(2f));

        stage.addActor(bg);
    }

    @Override
    protected void handleInput() {
        if(isLevel1){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            finished = true;
                            if(finished){
                                gsm.set(new Chapter1Scene1(gsm));
                            }
                        }
                    })));
            fadeOut();
        }

        if(isLevel2){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new Chapter2Scene1(gsm));
                    }
                }
            })));
            fadeOut();
        }

        if(isLevel3){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new Chapter3Scene1(gsm));
                    }
                }
            })));
            fadeOut();
        }

        if(isLevel4){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new Chapter4Scene1(gsm));
                    }
                }
            })));
            fadeOut();
        }

        if(isLevel5){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        gsm.set(new Chapter5Scene1(gsm));
                    }
                }
            })));
            fadeOut();
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
            fadeOut();
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
        stage.getBatch().draw(title, (AnyaGame.WIDTH/2)-100, (AnyaGame.HEIGHT/2)+140, AnyaGame.WIDTH/4, AnyaGame.HEIGHT/4);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    protected void dispose() {
        background.dispose();
        title.dispose();
    }

    public void fadeOut(){
        theTitle.addAction(Actions.fadeOut(2f));
        level1.addAction(Actions.fadeOut(2f));
        level2.addAction(Actions.fadeOut(2f));
        level3.addAction(Actions.fadeOut(2f));
        level4.addAction(Actions.fadeOut(2f));
        level5.addAction(Actions.fadeOut(2f));
        backButton.addAction(Actions.fadeOut(2f));
    }
}
