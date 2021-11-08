package game.teamproject.game;
/**
 * Background definition
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Background {
    private String pathway;
    private int width;
    private int height;

    public Background(String path, int w, int h){
        pathway = path;
        height = h;
        width = w;
        Image img = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal(path))));
        img.setWidth(w);
        img.setHeight(h);

    }
}
