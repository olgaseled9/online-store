package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.dto.StatusDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Status;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    private StatusDao statusDao;
    @InjectMocks
    private StatusServiceImpl statusService;

    @Test
    void shouldFindEmptyListOfStatuses() {
        List<Status> statuses=statusDao.findAll();
        assertTrue(statuses.isEmpty());
    }

    @Test
    void shouldReturnStatusesList() {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setName(StatusType.NEW);
        Status status = new Status();
        status.setName(StatusType.NEW);
        when(statusDao.findAll()).thenReturn(Collections.singletonList(status));
        List<StatusDTO> statuses = statusService.findAll();
        assertEquals(statuses.get(0).getName(), statusDTO.getName());
    }
}