package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.entity.Status;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository
public class StatusDaoImpl extends GenericDaoImpl<Long, Status> implements StatusDao {

    private static final String FIND_STATUS_BY_NAME_QUERY = "from Status as s where s.name=:name";

    @Override
    public Status findStatusByName(StatusType statusType) {
        Query query = entityManager.createQuery(FIND_STATUS_BY_NAME_QUERY);
        query.setParameter("name", statusType);
        return (Status) query.getSingleResult();
    }
}
