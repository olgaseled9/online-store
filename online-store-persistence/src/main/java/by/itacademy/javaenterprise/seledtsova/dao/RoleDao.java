package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;

public interface RoleDao extends GenericDao<Long, Role> {
    Role findRoleByName(RoleType roleType);
}


