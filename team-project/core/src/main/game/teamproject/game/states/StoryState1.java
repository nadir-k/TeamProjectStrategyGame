package game.teamproject.game.states;
/**
 * Class to display story part 1
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.teamproject.game.AnyaGame;

public class StoryState1 extends State {
    private Texture story1;

    public StoryState1(GameStateManager gsm){
        super(gsm);
        story1 = new Texture("part1.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new StoryState2(gsm));
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
        sb.draw(story1, 0, 0, AnyaGame.WIDTH, AnyaGame.HEIGHT);
        sb.end();
    }

    @Override
    protected void dispose() {
        story1.dispose();
    }
}
