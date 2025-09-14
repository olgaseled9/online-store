package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Long, Item> implements ItemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] findImageBlobById(Long id) {
        try {
            return em.createQuery(
                            "select i.imageBlob from Item i where i.id = :id", byte[].class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String findImageContentTypeById(Long id) {
        return em.createQuery(
                        "select i.imageContentType from Item i where i.id = :id", String.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateImage(Long id, byte[] image, String contentType) {
        em.createQuery(
                        "update Item i set i.imageBlob = :img, i.imageContentType = :ct where i.id = :id")
                .setParameter("img", image)
                .setParameter("ct", contentType)
                .setParameter("id", id)
                .executeUpdate();
    }
}
