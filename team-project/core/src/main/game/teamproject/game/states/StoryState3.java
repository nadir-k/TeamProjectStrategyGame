package game.teamproject.game.states;
/**
 * Class to display story part 3
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.teamproject.game.AnyaGame;

public class StoryState3 extends State {
    private Texture story3;
    protected StoryState3(GameStateManager gsm) {
        super(gsm);
        story3 = new Texture("part3.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new BattleState(gsm));
            dispose();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(story3, 0,0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.end();
    }

    @Override
    protected void dispose() {
        story3.dispose();
    }
}
