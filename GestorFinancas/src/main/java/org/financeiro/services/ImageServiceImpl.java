package org.financeiro.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImageServiceImpl implements ImageService {
    @Override
    public BufferedImage getImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public URL getImageUrl(String path) {
        return getClass().getClassLoader().getResource(path);
    }
}
