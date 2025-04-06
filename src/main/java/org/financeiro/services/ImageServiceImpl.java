package org.financeiro.services;

import org.financeiro.enums.PathResources;
import org.financeiro.singletons.ImageSingleton;

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
    public void loadImagens() {
        for (PathResources path : PathResources.values()) {
            ImageSingleton.getInstance().addImage(path.getNome(), getImage(path.getPath()));
        }
    }
}
