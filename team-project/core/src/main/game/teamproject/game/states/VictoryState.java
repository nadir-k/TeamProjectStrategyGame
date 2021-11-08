package game.teamproject.game.states;
/**
 * Class to display player won
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
import game.teamproject.game.Characters.LevelSystem;
import game.teamproject.game.Characters.Player;
import game.teamproject.game.states.scenes.Chapter2Scene1;
import game.teamproject.game.states.scenes.Chapter3Scene1;
import game.teamproject.game.states.scenes.Chapter4Scene1;
import game.teamproject.game.states.scenes.Chapter5Scene1;
import game.teamproject.game.states.scenes.EndScene;
import save.Save;

public class VictoryState extends State {
        protected Texture victory;
        protected boolean  playIsPressed;
        private Player player;
        private LevelSystem system;
        private Image bg;
        private boolean finished = false;
        Viewport view;
        Stage stage;
        Table table;
        String currentlevel;

        public VictoryState(GameStateManager gsm, String level) {
            super(gsm);
            currentlevel = level;
            player = Player.getInstance();
            system = new LevelSystem();
            victory = new Texture("victory.jpg");
            bg = new Image(victory);
            view = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
            stage = new Stage(view, AnyaGame.batch);
            bg.setPosition(0f, stage.getHeight() - bg.getImageHeight());
            table = new Table();
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

            stage.addActor(bg);
            stage.addActor(table);

            bg.getColor().a = 0f;
            bg.addAction(Actions.fadeIn(2f));

            playbutton.getColor().a = 0f;
            playbutton.addAction(Actions.fadeIn(2f));

        }

    public void getState(String level) {
        if (level.equals("level1")) {
            gsm.set(new Chapter2Scene1(gsm));
        }
        else if (level.equals("level2")) {
            gsm.set(new Chapter3Scene1(gsm));
        }
        else if (level.equals("level3")) {
            gsm.set(new Chapter4Scene1(gsm));
        }
        else if (level.equals("level4")) {
            gsm.set(new Chapter5Scene1(gsm));
        } else if (level.equals("level5")) {
            gsm.set(new EndScene(gsm));
        }
    }

        @Override
        protected void handleInput() {

            if(playIsPressed){
                if (system.checkLevelUp(player.getLevel(), player.getExperiences())) {
                    System.out.print("Do level");
                    player.incrementLevel();
                    Save.getInstance().save();
                    bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            finished = true;
                            if(finished){
                                gsm.set(new TheUpgradeState(gsm, currentlevel));
                            }
                        }
                    })));

                } else {
                    System.out.println("Don't level");
                    bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            finished = true;
                            if(finished){
                                getState(currentlevel);
                            }
                        }
                    })));

            }

        }
        }


        private void  fadeStateOut() {

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
            stage.getBatch().draw(victory, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
            stage.getBatch().end();
            stage.draw();
        }

        @Override
        protected void dispose() {
            victory.dispose();
        }




    }


