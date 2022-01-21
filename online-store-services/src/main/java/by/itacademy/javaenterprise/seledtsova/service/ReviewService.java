package by.itacademy.javaenterprise.seledtsova.service;

import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewPageDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO addReview(ReviewDTO reviewDTO, String username);

    ReviewPageDTO findReviewsWithPagination(int pageNumber, int pageSize);

    void removeById(Long id);

    void changeVisibilityByIds(List<Long> selectedIds);

}
