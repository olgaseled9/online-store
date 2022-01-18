package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ReviewServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Review;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReviewServiceConverterImpl implements ReviewServiceConverter {

    private final UserDao userDao;

    @Override
    public ReviewDTO convertReviewToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewBody(review.getReviewBody());
        reviewDTO.setDate(review.getCreatedBy());
        reviewDTO.setIsVisible(review.getIsVisible());
        if (Objects.nonNull(review.getUser())) {
            User user = userDao.findById(review.getUser().getId());
            reviewDTO.setUserId(user.getId());
            reviewDTO.setFirstName(user.getFirstName());
            reviewDTO.setLastName(user.getLastName());
            reviewDTO.setPatronymic(user.getPatronymic());
        }
        return reviewDTO;
    }

    @Override
    public Review convertDTOToReview(ReviewDTO reviewDTO, String username) {
        Review review = new Review();
        review.setReviewBody(reviewDTO.getReviewBody());
        review.setIsVisible(Boolean.FALSE);
        review.setCreatedBy(LocalDate.now());
        if (Objects.nonNull(userDao.findByUsername(username))) {
            User user = userDao.findByUsername(username);
            review.setUser(user);
        }
        return review;
    }
}
