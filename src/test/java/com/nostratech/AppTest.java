package com.nostratech;

import com.nostratech.model.QuarterlySales;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    private static SessionFactory factory;
    private static Session session;

    private static double generateRandomAmount() {
        return ((double)new Random().nextInt(500))/
                (new Random().nextInt(1) == 0 ? 2 : 3);
    }

    public void testApp() {
        try {
            factory = new AnnotationConfiguration()
                    .configure()
                    .addPackage("com.nostratech.model")
                    .addAnnotatedClass(QuarterlySales.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();

            QuarterlySales[] sales = new QuarterlySales[] {
                new QuarterlySales("Rice"),
                new QuarterlySales("Vegetable"),
                new QuarterlySales("Fruit"),
                new QuarterlySales("Snack"),
                new QuarterlySales("Ketchup"),
                new QuarterlySales("Milk")
            };

            for (QuarterlySales sale : sales) {
                sale.setAmountQ1(generateRandomAmount());
                sale.setAmountQ2(generateRandomAmount());
                sale.setAmountQ3(generateRandomAmount());
                sale.setAmountQ4(generateRandomAmount());

                session.save(sale);
            }

            assert session.createCriteria(QuarterlySales.class).list().size() > 0;

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
