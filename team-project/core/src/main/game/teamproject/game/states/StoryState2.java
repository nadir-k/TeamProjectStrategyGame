package game.teamproject.game.states;
/**
 * Class to display story part 2
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.teamproject.game.AnyaGame;

public class StoryState2 extends State {
    private Texture story2;
    protected StoryState2(GameStateManager gsm) {
        super(gsm);
        story2 = new Texture("part2.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new StoryState3(gsm));
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
        sb.draw(story2, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.end();
    }

    @Override
    protected void dispose() {
        story2.dispose();
    }
}
