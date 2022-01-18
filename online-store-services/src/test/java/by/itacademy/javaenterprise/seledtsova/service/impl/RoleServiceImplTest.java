package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.dto.RoleDTO;
import by.itacademy.javaenterprise.seledtsova.dto.StatusDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
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
class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;
    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void shouldReturnStatusesList() {
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setName(RoleType.ROLE_CUSTOMER_USER);
        Role role=new Role();
        role.setName(RoleType.ROLE_CUSTOMER_USER);
        when(roleDao.findAll()).thenReturn(Collections.singletonList(role));
        List<RoleDTO> statuses = roleService.findAll();
        assertEquals(statuses.get(0).getName(), role.getName());
    }

    @Test
    void shouldFindEmptyListOfRoles() {
        List<Role> roles=roleDao.findAll();
        assertTrue(roles.isEmpty());
    }
 }
