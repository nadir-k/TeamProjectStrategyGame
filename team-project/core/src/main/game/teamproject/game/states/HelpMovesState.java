package game.teamproject.game.states;
/**
 * Class to help user with move selection
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
import game.teamproject.game.Characters.Player;

public class HelpMovesState extends State {
    private Texture background, title;

    private Image bg, bgTitle, backButton, fireAttackImage, regularAttackImage, stunAttackImage, armorShreadImage;;
    private Player player;
    private LevelSystem levelSystem;
    protected BitmapFont font;

    boolean isBack;
    boolean finished = false;

    Viewport viewport;
    Stage stage;
    Table table, table2;


    public HelpMovesState(GameStateManager gsm) {
        super(gsm);
        levelSystem = new LevelSystem();
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);
        title = new Texture(Gdx.files.internal("Menu/Help/moveshelp.png"));
        bgTitle = new Image(title);

        this.player = player;

        backButton = new Image(new Texture(Gdx.files.internal("Upgrade/backbutton.png")));
        viewport = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, AnyaGame.batch);
        bg.setPosition(0, stage.getHeight() - bg.getImageHeight());
        font = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);



        table = new Table();
        Gdx.input.setInputProcessor(stage);

        regularAttackImage = new Image(new Texture(Gdx.files.internal("buttons/purestrike.png")));
        armorShreadImage = new Image(new Texture(Gdx.files.internal("buttons/steelbreak.png")));
        fireAttackImage = new Image(new Texture(Gdx.files.internal("buttons/lavaslash.png")));
        stunAttackImage = new Image(new Texture(Gdx.files.internal("buttons/lightwave.png")));

        regularAttackImage.setSize(180, 90);
        armorShreadImage.setSize(180, 90);
        fireAttackImage.setSize(180, 90);
        stunAttackImage.setSize(180, 90);

        //table.setFillParent(true);
        //table.center();
        //table.moveBy((AnyaGame.WIDTH / 4) + -20, -30);
        //table.left().bottom();
        table.moveBy(0, -40);

        table.setFillParent(true);
        table.center();
        table.add(regularAttackImage).size(regularAttackImage.getWidth(), regularAttackImage.getHeight()).pad(-20,0,0,600);
        table.row();
        table.add(armorShreadImage).size(armorShreadImage.getWidth(), armorShreadImage.getHeight()).pad(-20,0,0,600);
        table.row();
        table.add(fireAttackImage).size(fireAttackImage.getWidth(), fireAttackImage.getHeight()).pad(-20,0,0,600);
        table.row();
        table.add(stunAttackImage).size(fireAttackImage.getWidth(), fireAttackImage.getHeight()).pad(-20,0,0,600);
        //stage.addActor(table);

        showMoves().draw();

        //table.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(0,0,0,700);
        stage.addActor(table);

        table2 = new Table();
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
        table2.setFillParent(true);
        table2.center();
        table2.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(500, 0, 0, 700);
        stage.addActor(table2);




        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        bgTitle.getColor().a = 0f;
        bgTitle.addAction(Actions.fadeIn(2f));

        backButton.getColor().a = 0f;
        backButton.addAction(Actions.fadeIn(2f));

        stage.addActor(bg);
    }


    public Stage showMoves(){
        //pure-strike steel-break light-wave lava-slash

        String PureStrike = "Attack to hit Enemy";
        String SteelBreak = "Attack to hit Enemy with Axe";
        String LightWave = "Attack to stun Enemy";
        String LavaSlash = "Attack to hit Enemy with Fire Sword";

        Stage stage = new Stage(viewport, AnyaGame.batch);
        Label label = new Label(PureStrike, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30,30);
        Label label2 = new Label(SteelBreak, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30,30);
        Label label3 = new Label(LightWave, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30,30);
        Label label4 = new Label(LavaSlash, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(30,30);

        label.setWrap(true);
        label2.setWrap(true);
        label3.setWrap(true);
        label4.setWrap(true);


        label.setPosition(AnyaGame.WIDTH/12 +125, AnyaGame.HEIGHT-240);
        label2.setPosition(AnyaGame.WIDTH/12 +125 , AnyaGame.HEIGHT-310);
        label3.setPosition(AnyaGame.WIDTH/12 +125, AnyaGame.HEIGHT-380);
        label4.setPosition(AnyaGame.WIDTH/12 +125 , AnyaGame.HEIGHT-450);

        stage.addActor(label);
        stage.addActor(label2);
        stage.addActor(label3);
        stage.addActor(label4);


        //Table table = new Table();
        //table.setFillParent(true);
        //table.center();
        //table.left();
        //table.add(label).expandX().pad(50, 0 , 0, 0);
        //table.row();
        //table.add(label2).expandX().pad(50, 0 , 0, 0);
        //table.row();
        //table.add(label3).expandX().pad(50, 0 , 0, 0);
        //table.row();
        //table.add(label4).expandX().pad(50, 0 , 0, 0);
        //stage.addActor(table);

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

        showMoves().draw();
    }

    @Override
    protected void dispose() {
        background.dispose();
        title.dispose();

    }
}
