package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.exception.DaoException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Repository
public class ItemDaoImpl implements ItemDao {

    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Item findItemById(Long id) {
        Item item = new Item();
        try {
            item = entityManager.find(Item.class, id);
        } catch (DaoException e) {
            logger.error("Cannot find item by id" + e.getMessage(), e);
        }
        return item;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        try {
            String queryString = "from " + Item.class.getName();
            Query query = entityManager.createQuery(queryString);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Cannot get all items " + e.getMessage(), e);
        }
        return null;
    }
}
