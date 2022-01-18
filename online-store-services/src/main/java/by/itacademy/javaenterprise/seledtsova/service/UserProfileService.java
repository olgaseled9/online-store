package by.itacademy.javaenterprise.seledtsova.service;

import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;


public interface UserProfileService {
    UserProfileDTO getById(Long id);

    UserProfileDTO findUserProfileByUsername(String name);

    void updateUserProfile(UserProfileDTO userProfileDTO);
}
