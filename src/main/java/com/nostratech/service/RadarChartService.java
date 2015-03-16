package com.nostratech.service;

import com.nostratech.model.QuarterlySales;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

/**
 * Created by fani on 2/5/15.
 */
public class RadarChartService {

    private SessionFactory factory;
    private Session session;

    public RadarChartService() {
        factory = new AnnotationConfiguration()
                .configure()
                .addPackage("com.nostratech.model")
                .addAnnotatedClass(QuarterlySales.class)
                .buildSessionFactory();
        session = factory.openSession();
    }

    public List<QuarterlySales> getData() {
        return session.createCriteria(QuarterlySales.class).list();
    }

}
