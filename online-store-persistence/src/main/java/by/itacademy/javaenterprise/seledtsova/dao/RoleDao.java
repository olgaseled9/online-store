package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;

public interface RoleDao {

    Role findRoleById(Long id);

    Role findRoleByName(RoleType roleType);
}

