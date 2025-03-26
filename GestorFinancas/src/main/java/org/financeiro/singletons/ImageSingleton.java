package org.financeiro.singletons;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageSingleton {
    private static ImageSingleton instance = null;
    private final HashMap<String, BufferedImage> imagens;

    private ImageSingleton() {
        this.imagens = new HashMap<>();
    }

    public static synchronized ImageSingleton getInstance() {
        if (instance == null) {
            instance = new ImageSingleton();
        }

        return instance;
    }

    public void addImage(String fileName, BufferedImage image) {
        this.imagens.put(fileName, image);
    }

    public Map<String, BufferedImage> getImagens() {
        return imagens;
    }

    public BufferedImage getImage(String fileName) {
        return imagens.get(fileName);
    }
}
