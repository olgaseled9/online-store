package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository
public class RoleDaoImpl extends GenericDaoImpl<Long, Role> implements RoleDao {

    private static final String FIND_ROLE_BY_NAME_QUERY = "from Role as r where r.name=:name";

    @Override
    public Role findRoleByName(RoleType roleType) {
        Query query = entityManager.createQuery(FIND_ROLE_BY_NAME_QUERY);
        query.setParameter("name", roleType);
        return (Role) query.getSingleResult();
    }
}
