package org.financeiro;

import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;
import org.financeiro.singletons.SwingSingleton;
import org.financeiro.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    protected static final ImageService imageService = new ImageServiceImpl();

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Hibernate warmup concluído.");
        } catch (Exception e) {
            e.printStackTrace();
        }


        imageService.loadImagens();
        SwingSingleton.getInstance();
    }
}