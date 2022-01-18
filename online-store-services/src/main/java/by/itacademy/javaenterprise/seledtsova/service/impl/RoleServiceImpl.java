package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.dto.RoleDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    @Transactional
    public List<RoleDTO> findAll() {
        List<Role> roles = roleDao.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            roleDTOS.add(convertRoleToDTO(role));
        }
        return roleDTOS;
    }

    private RoleDTO convertRoleToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
