package org.financeiro.services;

import java.awt.image.BufferedImage;
import java.net.URL;

public interface ImageService {
    BufferedImage getImage(String path);
    void loadImagens();
}
