package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Status;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;

public interface StatusDao {

    Status findStatusByName(StatusType statusType);

}
