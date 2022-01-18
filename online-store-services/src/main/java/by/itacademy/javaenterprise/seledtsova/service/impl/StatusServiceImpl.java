package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.dto.StatusDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Status;
import by.itacademy.javaenterprise.seledtsova.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    @Autowired
    private final StatusDao statusDao;

    @Override
    public List<StatusDTO> findAll() {
        List<Status> statuses = statusDao.findAll();
        List<StatusDTO> statusDTOS = new ArrayList<>();
        for (Status status : statuses) {
            statusDTOS.add(convertStatusToDTO(status));
        }
        return statusDTOS;
    }

    private StatusDTO convertStatusToDTO(Status status) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setName(status.getName());
        return statusDTO;
    }
}
