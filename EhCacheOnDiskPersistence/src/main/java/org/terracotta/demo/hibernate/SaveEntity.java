package org.terracotta.demo.hibernate;

import org.terracotta.demo.hibernate.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vinay on 8/14/15.
 */
public class SaveEntity {
    private static Logger LOG = LoggerFactory.getLogger(SaveEntity.class);

    public static void main(String[] args) {
        String accoutNumber = args[0];
        String customerName = args[1];
        Double balance = Double.parseDouble(args[2]);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Account account = new Account(accoutNumber, customerName, balance);
        session.saveOrUpdate(account);
        tx.commit();
        session.close();
        LOG.info("Saved Account entity[" + account + "]");
        HibernateUtil.getSessionFactory().close();

    }


}
