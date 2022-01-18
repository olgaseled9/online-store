package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Review;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceConverterImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private ReviewServiceConverterImpl reviewServiceConverter;

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectId() {
        Review review = new Review();
        Long id = 1L;
        review.setId(id);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertEquals(id, reviewDTO.getId());
    }

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectDate() {
        Review review = new Review();
        LocalDate date = LocalDate.now();
        review.setCreatedBy(date);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertEquals(date, reviewDTO.getDate());
    }

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectLastName() {
        User user = new User();
        String lastName = "name";
        Long id = 1L;
        user.setId(id);
        user.setLastName(lastName);
        Review review = new Review();
        review.setUser(user);
        when(userDao.findById(id)).thenReturn(user);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertEquals(lastName, reviewDTO.getLastName());
    }

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectPatronymic() {
        User user = new User();
        String patronymic = "patronymic";
        Long id = 1L;
        user.setId(id);
        user.setPatronymic(patronymic);
        Review review = new Review();
        review.setUser(user);
        when(userDao.findById(id)).thenReturn(user);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertEquals(patronymic, reviewDTO.getPatronymic());
    }

    @Test
    void shouldConvertDTOToReviewAndReturnName() {
        ReviewDTO reviewDTO = new ReviewDTO();
        String reviewBody = "review body";
        reviewDTO.setReviewBody(reviewBody);
        Review review = reviewServiceConverter.convertDTOToReview(reviewDTO, "");
        Assertions.assertEquals(reviewBody, review.getReviewBody());
    }

    @Test
    void shouldConvertDTOToReviewAndReturnIsVisible() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setIsVisible(false);
        Review review = reviewServiceConverter.convertDTOToReview(reviewDTO, "");
        Assertions.assertEquals(false, review.getIsVisible());
    }

    @Test
    void shouldConvertDTOToReviewAndReturnCorrectDate() {
        ReviewDTO reviewDTO = new ReviewDTO();
        LocalDate date = LocalDate.now();
        reviewDTO.setDate(date);
        Review review = reviewServiceConverter.convertDTOToReview(reviewDTO, "");
        Assertions.assertEquals(date, review.getCreatedBy());
    }

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectBooleanVisible() {
        Review review = new Review();
        review.setIsVisible(true);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertTrue(reviewDTO.getIsVisible());
    }

    @Test
    void shouldConvertReviewToDTOAndReturnCorrectUserId() {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        Review review = new Review();
        review.setUser(user);
        when(userDao.findById(id)).thenReturn(user);
        ReviewDTO reviewDTO = reviewServiceConverter.convertReviewToDTO(review);
        Assertions.assertEquals(id, reviewDTO.getUserId());
    }
}