package game.teamproject.game.controller;
/**
 * This class stores all the buttons the player can press during the battle
 */

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;

import game.teamproject.game.AnyaGame;
import game.teamproject.game.Characters.moves.Move;

public class Controller {
    private Viewport viewport;
    private Stage stage;
    private boolean regularAttack, fireAttack, stunAttack, armorShreadAttack, inCooldown;
    private Image fireAttackImage, regularAttackImage, stunAttackImage, armorShreadImage;
    private OrthographicCamera cam;
    private HashMap<Move, Texture> moveTexture;
    private HashMap<Move, Texture> disableMoveTexture;
    private HashMap<Move, Image> moveImage;


    /**
     * Create a table that contains all the button with the set size and image
     */

    public Controller() {
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT);
        stage = new Stage(viewport, AnyaGame.batch);
        Gdx.input.setInputProcessor(stage);
        moveTexture = new HashMap<>();
        disableMoveTexture = new HashMap<>();
        moveImage = new HashMap<>();
        /* create a table that displays the buttons*/
        Table table = new Table();
        /* instantiate the buttons of images that will be inserted in the table*/

        //final Image jumpAttackImage = new Image(new Texture(Gdx.files.internal("buttons/normal.png")));

        moveTexture.put(Move.ARMORBREAK, new Texture(Gdx.files.internal("buttons/steelbreak.png")));
        moveTexture.put(Move.FIRE, new Texture(Gdx.files.internal("buttons/lavaslash.png")));
        moveTexture.put(Move.STUN, new Texture(Gdx.files.internal("buttons/lightwave.png")));
        regularAttackImage = new Image(new Texture(Gdx.files.internal("buttons/purestrike.png")));

        armorShreadImage = new Image(getMoveTexture(Move.ARMORBREAK));
        stunAttackImage = new Image(getMoveTexture(Move.STUN));
        fireAttackImage = new Image(getMoveTexture(Move.FIRE));
        moveImage.put(Move.ARMORBREAK, armorShreadImage);
        moveImage.put(Move.STUN, stunAttackImage);
        moveImage.put(Move.FIRE, fireAttackImage);


        disableMoveTexture.put(Move.ARMORBREAK,  new Texture(Gdx.files.internal("buttons/steelbreakdisable.png")));
        disableMoveTexture.put(Move.FIRE,  new Texture(Gdx.files.internal("buttons/lavaslashdisable.png")));
        disableMoveTexture.put(Move.STUN, new Texture(Gdx.files.internal("buttons/lightwavedisable.png")));

        regularAttackImage.setSize(180, 90);
        regularAttackImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                regularAttack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                regularAttack = !regularAttack;
            }

        });

        //Image nonMeleeAttackImage = new Image(new Texture(Gdx.files.internal("buttons/armorbreak.png")));


        armorShreadImage.setSize(180, 90);
        armorShreadImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                armorShreadAttack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                armorShreadAttack = !armorShreadAttack;
            }
        });

        //Image meleeAttackImage = new Image(new Texture(Gdx.files.internal("buttons/stun.png")));


        stunAttackImage.setSize(180, 90);
        stunAttackImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stunAttack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stunAttack = !stunAttack;
            }
        });

        //Image rangedAttackImage = new Image(new Texture(Gdx.files.internal("buttons/fire.png")));

        fireAttackImage.setSize(180, 90);
        fireAttackImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fireAttack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                fireAttack = !fireAttack;
            }
        });




        /*arranging and aligning the buttons in the table and then adding them in*/
        table.moveBy((AnyaGame.WIDTH / 4) + 190, 0);
        table.left().bottom();
        //table.top().left();
        table.add(regularAttackImage).size(regularAttackImage.getWidth(), regularAttackImage.getHeight()).pad(0, 10, 0, 5);
        table.add(armorShreadImage).size(armorShreadImage.getWidth(), armorShreadImage.getHeight()).pad(0, 10, 0, 5);
        table.row();
        table.add(stunAttackImage).size(stunAttackImage.getWidth(), stunAttackImage.getHeight()).pad(-10, 10, 10, 5);
        table.add(fireAttackImage).size(fireAttackImage.getWidth(), fireAttackImage.getHeight()).pad(-10, 10, 10, 5);
        stage.addActor(table);

    }

    /**
     * Draw the button
     */
    public void draw() {
        stage.draw();
    }

    public boolean isRegularAttack() {
        return regularAttack;
    }

    public boolean isArmorShreadAttack() {
        return armorShreadAttack;
    }

    public boolean isStunAttack() {
        return stunAttack;
    }

    public boolean isFireAttack() {
        return fireAttack;
    }


    public void handleCooldown(int cooldown, Move move){
        Image moveImage = getMoveImage(move);
        if (move != Move.NORMAL) {
            if (cooldown != 0) {
                moveImage.setDrawable(new TextureRegionDrawable(getDisableMoveTexture(move)));
                //moveImage.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Upgrade/next.png")))));
                disableTouch(moveImage);
            } else {
                moveImage.setDrawable(new TextureRegionDrawable(getMoveTexture(move)));
                enableTouch(moveImage);

            }
        }
    }

    public Texture getMoveTexture(Move move) {
        return moveTexture.get(move);

    }

    public Texture getDisableMoveTexture(Move move) {
        return disableMoveTexture.get(move);
    }

    public Image getMoveImage(Move move) {
        return moveImage.get(move);
    }

    public void enableTouch(Image image){
            image.setTouchable(Touchable.enabled);
    }

    public void disableTouch(Image image) {
        image.setTouchable(Touchable.disabled);
    }


    public boolean isInCooldown() {
        return inCooldown;
    }

    public void setInCooldown(boolean inCooldown) {
        this.inCooldown = inCooldown;
    }
}
