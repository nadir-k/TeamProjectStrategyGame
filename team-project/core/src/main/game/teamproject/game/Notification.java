package game.teamproject.game;
/**
 * Notifications to user
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.teamproject.game.Characters.Enemy;


public class Notification {
    protected Stage stage;
    protected BitmapFont font;
    protected Label label1;
    protected OrthographicCamera cam;
    protected Viewport viewport;

    private int turnCount;

    //construct notification
    public Notification(){
        cam = new OrthographicCamera();
        viewport = new FitViewport(AnyaGame.WIDTH, AnyaGame.HEIGHT, cam);
        stage = new Stage(viewport, AnyaGame.batch);

        font = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);
        label1 = new Label("Enemy is dead, new enemy appearing ",new Label.LabelStyle(font, Color.WHITE));

        Table notificationTable = new Table();
        notificationTable.setFillParent(true);
        notificationTable.center();
        notificationTable.add(label1).expandX().pad(0,0,0,20);
        stage.addActor(notificationTable);

        turnCount = 0;
    }

    public Stage getStage() {
        return stage;
    }

    //display no of turns
    public Stage getNumberTurns(int count){
        Stage stage = new Stage(viewport, AnyaGame.batch);
        String text = "Turns played: " + count;
        Label label = new Label(text, new Label.LabelStyle(font, Color.WHITE));
        label.setScale(10);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label).expandX().pad(20,0,0,20);
        table.setPosition(0, 50);

        stage.addActor(table);

        return stage;
    }

    //display player turn
    public Stage getPlayerStage(){
        Stage stage1 = new Stage(viewport, AnyaGame.batch);
        Label label2 = new Label("Players turn! ", new Label.LabelStyle(font, Color.WHITE));
        label2.setScale(20);
        Table notificationTable = new Table();
        notificationTable.setFillParent(true);
        notificationTable.center();
        notificationTable.add(label2).expandX().pad(275, 0, 0, 550);
        stage1.addActor(notificationTable);

        return stage1;
    }

    //display enemy turn
    public Stage getEnemyStage(){
        Stage stage2 = new Stage(viewport, AnyaGame.batch);
        Label label3 = new Label("Enemies turn! ", new Label.LabelStyle(font, Color.WHITE));
        label3.setScale(20);
        Table notificationTable = new Table();
        notificationTable.setFillParent(true);
        notificationTable.center();
        notificationTable.add(label3).expandX().pad(275, 0, 0, 550);
        stage2.addActor(notificationTable);

        boolean visible = label3.isVisible();

        if(visible && label1.isVisible()){
            visible = false;
        }

        return stage2;
    }

    //Enemy armorshread damage logic
    public Stage logTurn(Enemy enemy, BattleLogic battleLogic){
        Stage stage = new Stage(viewport, AnyaGame.batch);
        BattleLogic logic = battleLogic;
        Boolean isArmorshread = enemy.isArmorshread();


        String text = playerLog(logic, isArmorshread);

        Label log = new Label(text, new Label.LabelStyle(font, Color.WHITE));
        log.setScale(10);

        Table notificationTable = new Table();
        notificationTable.setFillParent(true);
        notificationTable.add(log).expandX().pad(0, 20, 40, 0);
        notificationTable.setPosition((AnyaGame.WIDTH/-4) + 5, (AnyaGame.HEIGHT/-3) -20);
        stage.addActor(notificationTable);


        return stage;
    }

    //player debuff armorshread logic
    public String playerLog(BattleLogic logic,Boolean isArmorshread) {
        String applied;
        String criticalDamage;
        int damage = logic.getDamageDealt();
        String move = logic.getMove();
        boolean isApplied = logic.isApplied();

        if (isApplied) {
            applied = "Debuff applied";
        } else {
            applied = "Debuff not applied";
        }
        if (isArmorshread) {
            criticalDamage = "Critical damage! \n";
        } else {
            criticalDamage = " damage! \n";
        }

        return "Player chose " + move + "! \n" + "Player dealt " + damage + " " + criticalDamage + applied;
    }

    //player debuff stun logic
    public String enemyLog(BattleLogic logic, Enemy enemy) {
        int damage = logic.getDamageDealt();
        String move = logic.getMove();
        String log;
        if (enemy.isStunned()) {
            log = "Enemy is stun!\n" + "Enemy skip his turn";
        } else {
            log = "Enemy chose " + move + "! \n" + "Enemy dealt " + damage + " damage! \n";
        }

        return log;
    }

    //player debuff burn logic
    public String postLog(BattleLogic logic, Enemy enemy)  {
        if (enemy.isBurned()) {
            return  "Enemy is Burned!\n" + "Enemy take" + logic.getDebuffDetail().getValue() + "damage! ";
        }
        return "";

    }











}
