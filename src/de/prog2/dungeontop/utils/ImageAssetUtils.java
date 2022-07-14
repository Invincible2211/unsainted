package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.control.manager.AssetsManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;

public class ImageAssetUtils
{
    /**
     * Gets the image for the given asset id and modifies it to the given size.
     */
    public static ImageView upsertImageFromAssets(int id, double width, double height)
    {
        Image image = null;
        try
        {
            image = new Image(AssetsManager.getAssetById(id).toURI().toURL().toString() , width, height, true, true);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        return new ImageView(image);
    }
}
