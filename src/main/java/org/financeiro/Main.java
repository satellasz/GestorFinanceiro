package org.financeiro;

import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;
import org.financeiro.singletons.SwingSingleton;

public class Main {
    protected static final ImageService imageService = new ImageServiceImpl();

    public static void main(String[] args) {
        imageService.loadImagens();
        SwingSingleton.getInstance();
    }
}