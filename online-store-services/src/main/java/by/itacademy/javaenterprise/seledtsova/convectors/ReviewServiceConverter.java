package by.itacademy.javaenterprise.seledtsova.convectors;

import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Review;

public interface ReviewServiceConverter {
    ReviewDTO convertReviewToDTO(Review review);

    Review convertDTOToReview(ReviewDTO reviewDTO, String username);
}
