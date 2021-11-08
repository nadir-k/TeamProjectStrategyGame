package game.teamproject.game.states;
/**
 * Class to upgrade player stats
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.Characters.Player;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.DebufferDamageMove;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.states.scenes.Chapter2Scene1;
import game.teamproject.game.states.scenes.Chapter3Scene1;
import game.teamproject.game.states.scenes.Chapter4Scene1;
import game.teamproject.game.states.scenes.Chapter5Scene1;
import game.teamproject.game.states.scenes.EndScene;
import game.teamproject.game.upgrades.MoveDatabase;
import game.teamproject.game.upgrades.Upgrade;
import game.teamproject.game.upgrades.UpgradeDatabase;
import game.teamproject.game.upgrades.UpgradeDetails;
import game.teamproject.game.upgrades.UpgradeSystem;
import save.Save;

public class TheUpgradeState extends State {
    private Texture background, title;
    private Image bg, bgtitle, upgrade1Button, upgrade2Button, upgrade3Button, selectedUpgradeButton, nextButton;

    boolean isUpgrade1, isUpgrade2, isUpgrade3, isBack;
    protected BitmapFont font;
    Viewport view;
    Stage stage;
    Table table, table2, table3;
    private HashMap<Move, Texture> upgradeTextures;
    private ArrayList<Image> upgradeImages;
    private DebuffDatabase debuffDatabase;
    private UpgradeSystem upgradeSystem;
    private ArrayList<UpgradeDetails> upgradeChoice;
    private ArrayList<UpgradeDetails> allUpgrade;
    private HashSet<Integer> upgradeIDs;
    private UpgradeDetails playerChoice;
    private boolean playerTurn, chosenUpgrade, isNext;
    private boolean finished = false;
    private Player player;
    private String currentlevel;

    public TheUpgradeState(GameStateManager gsm, String level) {
        super(gsm);
        currentlevel = level;
        System.out.println(level);
        playerTurn = true;
        this.player = Player.getInstance();
        upgradeChoice = new ArrayList<>();
        UpgradeDatabase upgradeDatabase = new UpgradeDatabase();
        MoveDatabase moveDatabase = new MoveDatabase();
        allUpgrade = upgradeDatabase.getUpgradesList();
        debuffDatabase = DebuffDatabase.getInstance();
        upgradeSystem = new UpgradeSystem(moveDatabase, debuffDatabase);
        upgradeIDs = new HashSet<>();
        background = new Texture(Gdx.files.internal("Menu/mainmenu.jpeg"));
        bg = new Image(background);

        upgradeTextures = new HashMap<>();
        upgradeImages = new ArrayList<>();
        upgradeTextures.put(Move.ARMORBREAK, new Texture(Gdx.files.internal("Upgrade/armorbreak.png")));
        upgradeTextures.put(Move.FIRE, new Texture(Gdx.files.internal("Upgrade/fire.png")));
        upgradeTextures.put(Move.STUN, new Texture(Gdx.files.internal("Upgrade/stun.png")));

        font = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);

        title = new Texture(Gdx.files.internal("Upgrade/selectupgrade.png"));
        bgtitle = new Image(title);

        view = new StretchViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(view, AnyaGame.batch);
        bg.setPosition(0f, stage.getHeight() - bg.getImageHeight());
      //  upgrade1Button = new Image(new Texture(Gdx.files.internal("Upgrade/chooseupgrade.png")));
        playerTurn = true;
        createChoices();
        System.out.println(upgradeImages.size());
        upgrade1Button = upgradeImages.get(0);
        upgrade2Button = upgradeImages.get(1);
        upgrade3Button = upgradeImages.get(2);

        table = new Table();
        Gdx.input.setInputProcessor(stage);
        upgrade1Button.setSize(100, 100);
        upgrade1Button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade1 = true;
                return isUpgrade1;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade1 = false;
            }
        });
        upgrade2Button.setSize(100, 100);
        upgrade2Button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade2 = true;
                return isUpgrade2;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade2 = false;
            }
        });
        upgrade3Button.setSize(100, 100);
        upgrade3Button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade3 = true;
                return isUpgrade3;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isUpgrade3 = false;
            }
        });

        table.setFillParent(true);
        table.center();
        table.add(upgrade1Button).size(upgrade1Button.getWidth(), upgrade1Button.getHeight()).pad(0,0,150,10);
        table.add(upgrade2Button).size(upgrade2Button.getWidth(), upgrade2Button.getHeight()).pad(0,0,150,10);
        table.add(upgrade3Button).size(upgrade3Button.getWidth(), upgrade3Button.getHeight()).pad(0,0,150,10);
        stage.addActor(table);


        table2 = new Table();
        selectedUpgradeButton = new Image(new Texture(Gdx.files.internal("Upgrade/chooseupgrade.png")));
        selectedUpgradeButton.setSize(100, 100);
        table2.setFillParent(true);
        table2.center();
        table2.add(selectedUpgradeButton).size(selectedUpgradeButton.getWidth(), selectedUpgradeButton.getHeight()).pad(AnyaGame.HEIGHT/2 + 50, 0, 0, 10);
        stage.addActor(table2);

        table3 = new Table();
        nextButton = new Image(new Texture(Gdx.files.internal("Upgrade/nextbutton.png")));
        nextButton.setSize(100, 100);
        nextButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                isNext = true;
                return isNext;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isNext = false;
            }
        });
        table3.setFillParent(true);
        table3.center();
        table3.add(nextButton).size(nextButton.getWidth(), nextButton.getHeight()).pad(500, 700, 0, 0);
        stage.addActor(table3);

        bg.getColor().a = 0f;
        bg.addAction(Actions.fadeIn(2f));

        bgtitle.getColor().a = 0f;
        bgtitle.addAction(Actions.fadeOut(2f));

        upgrade1Button.getColor().a = 0f;
        upgrade1Button.addAction(Actions.fadeIn(2f));

        upgrade2Button.getColor().a = 0f;
        upgrade2Button.addAction(Actions.fadeIn(2f));

        upgrade3Button.getColor().a = 0f;
        upgrade3Button.addAction(Actions.fadeIn(2f));

        selectedUpgradeButton.getColor().a = 0f;
        selectedUpgradeButton.addAction(Actions.fadeIn(2f));

        nextButton.getColor().a = 0f;
        nextButton.addAction(Actions.fadeIn(2f));

        stage.addActor(bg);
        allUpgrade = upgradeDatabase.getUpgradesList();
        upgradeSystem = new UpgradeSystem(moveDatabase, debuffDatabase);
        chosenUpgrade = false;
    }


    public void getState(String level) {

        if (level.equals("level1")) {
            gsm.set(new Chapter2Scene1(gsm));
        } else if (level.equals("level2")) {
            gsm.set(new Chapter3Scene1(gsm));
        } else if (level.equals("level3")) {
            gsm.set(new Chapter4Scene1(gsm));
        } else if (level.equals("level4")) {
            gsm.set(new Chapter5Scene1(gsm));
        } else if (level.equals("level5")) {
            gsm.set(new EndScene(gsm));
        } else if (level.equals("endless")) {
            gsm.set(new MenuState(gsm));

        }
    }

    public void createChoices() {
        while (upgradeChoice.size() < 3) {
            UpgradeDetails upgradeDetails = getRandomUpgrade();
            if (!(upgradeDetails.isUpgraded()) && !(isDuplicateUpgrade(upgradeDetails))) {
                upgradeChoice.add(upgradeDetails);
                upgradeIDs.add(upgradeDetails.getID());
                upgradeImages.add(new Image(getMoveTexture(upgradeDetails.getMove())));
            }
        }
    }


    public void upgradePlayerMove() {
        DebufferDamageMove move = (DebufferDamageMove) player.getMoveSet().getMove(playerChoice.getMove());
        upgradeSystem.upgradeMove(playerChoice);
    }

    public boolean isDuplicateUpgrade(UpgradeDetails upgradeDetails) {
        return upgradeIDs.contains(upgradeDetails.getID());
    }


    public UpgradeDetails getRandomUpgrade() {
        Random randomizer = new Random();
        return allUpgrade.get(randomizer.nextInt(allUpgrade.size()));
    }

    @Override
    protected void handleInput() {
        if (playerTurn) {
            if (isUpgrade1) {
                playerChoice = upgradeChoice.get(0);
                selectedUpgradeButton.setDrawable(new TextureRegionDrawable(upgradeTextures.get(playerChoice.getMove())));
                upgrade1Button.addAction(Actions.fadeOut(2f));
                upgrade2Button.addAction(Actions.fadeOut(2f));
                upgrade3Button.addAction(Actions.fadeOut(2f));
                playerTurn = false;
                chosenUpgrade = true;


            }
            if (isUpgrade2) {
                playerChoice = upgradeChoice.get(1);
                selectedUpgradeButton.setDrawable(new TextureRegionDrawable(upgradeTextures.get(playerChoice.getMove())));
                upgrade1Button.addAction(Actions.fadeOut(2f));
                upgrade2Button.addAction(Actions.fadeOut(2f));
                upgrade3Button.addAction(Actions.fadeOut(2f));
                playerTurn = false;
                chosenUpgrade = true;
            }
            if (isUpgrade3) {
                playerChoice = upgradeChoice.get(2);
                selectedUpgradeButton.setDrawable(new TextureRegionDrawable(upgradeTextures.get(playerChoice.getMove())));
                upgrade1Button.addAction(Actions.fadeOut(2f));
                upgrade2Button.addAction(Actions.fadeOut(2f));
                upgrade3Button.addAction(Actions.fadeOut(2f));
                playerTurn = false;
                chosenUpgrade = true;
            }
        }
        if(isNext && chosenUpgrade){
            bg.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    finished = true;
                    if(finished){
                        upgradePlayerMove();
                        Save.getInstance().save();
                        getState(currentlevel);
                    }
                }
            })));
            nextButton.addAction(Actions.fadeOut(2f));
            selectedUpgradeButton.addAction(Actions.fadeOut(2f));
            bgtitle.addAction(Actions.fadeOut(2f));
        }

    }

    private void showUpgrade() {
        Debuff debuff = playerChoice.getDebuff();
        DebuffDetail debuffDetail = debuffDatabase.getDebuffDetails(debuff);
        String upgradeName = playerChoice.getUpgrade().upgradeToString();
        int current = getCurrentValue(playerChoice.getUpgrade(), debuffDetail);
        int upgrade = playerChoice.getValue();
    }

    public Stage getUpgradeDetails(){
        Debuff debuff = playerChoice.getDebuff();
        DebuffDetail debuffDetail = debuffDatabase.getDebuffDetails(debuff);
        String debuffName = debuff.debuffToString();
        String upgradeName = playerChoice.getUpgrade().upgradeToString();
        int current = getCurrentValue(playerChoice.getUpgrade(), debuffDetail);
        int upgrade = playerChoice.getValue();
        String move = playerChoice.getMove().moveToString();
        String details = upgradeName + ": " + current + " -> " + upgrade;
        Stage stage = new Stage(view, AnyaGame.batch);
        Label label = new Label(move + "\n" + details, new Label.LabelStyle(font, Color.BLACK));
        label.setScale(40);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label).expandX().pad(75, 0, 0, 0);

        //label.getColor().a = 0f;
        label.addAction(Actions.fadeIn(4f));
        stage.addActor(table);

        return stage;
    }


    private int getCurrentValue(Upgrade upgrade, DebuffDetail debuff) {
        if (upgrade == Upgrade.DURATION) {
            return debuff.getDuration();
        } else if (upgrade == Upgrade.EFFECT) {
            return debuff.getValue();
        }
            return debuff.getChanceToApply();
    }

        @Override
        protected void update ( float dt){
        Save.getInstance().save();
            handleInput();

            if (!playerTurn) {
                showUpgrade();
            }

        }

        @Override
        protected void render (SpriteBatch sb){
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Gdx.graphics.getDeltaTime());
            stage.getBatch().begin();
            stage.getBatch().draw(background, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
            stage.getBatch().draw(title, (AnyaGame.WIDTH / 2) - 100, (AnyaGame.HEIGHT / 2) + 140, (AnyaGame.WIDTH / 4) + 30, (AnyaGame.HEIGHT / 4) + 30);
            stage.getBatch().end();
            stage.draw();

            if(chosenUpgrade){
                getUpgradeDetails().draw();
            }
        }

        @Override
        protected void dispose () {
            background.dispose();
            title.dispose();
        }



        public Texture getMoveTexture(Move move){
            return upgradeTextures.get(move);
        }

}
