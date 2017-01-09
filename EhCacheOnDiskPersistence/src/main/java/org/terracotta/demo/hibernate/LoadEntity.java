package org.terracotta.demo.hibernate;

import org.terracotta.demo.hibernate.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vinay on 8/14/15.
 */
public class LoadEntity {
    private static Logger LOG = LoggerFactory.getLogger(LoadEntity.class);

    public static void main(String[] args) {
        String accountNumber = args[0];

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Account account = (Account) session.load(Account.class, accountNumber);
        LOG.info("Got Account entity from DB [" + account + "]");
        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

    }


}
