package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ReviewServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.ReviewDao;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewPageDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Review;
import by.itacademy.javaenterprise.seledtsova.exception.ServiceException;
import by.itacademy.javaenterprise.seledtsova.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static by.itacademy.javaenterprise.seledtsova.service.impl.ServiceUtil.getNumbersOfPages;


@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewServiceConverter reviewServiceConverter;

    @Transactional
    @Override
    public ReviewPageDTO findReviewsWithPagination(int pageNumber, int pageSize) {
        ReviewPageDTO reviewPage = new ReviewPageDTO();
        List<Review> reviews = reviewDao.findWithPagination(pageNumber, pageSize);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOS.add(reviewServiceConverter.convertReviewToDTO(review));
        }
        reviewDTOS.sort(Comparator.comparing(ReviewDTO::getDate));
        reviewPage.getReviews().addAll(reviewDTOS);
        Long countOfReviews = reviewDao.getCount();
        reviewPage.setPagesCount(countOfReviews);
        List<Integer> numbersOfPages = getNumbersOfPages(pageSize, countOfReviews);
        reviewPage.getNumbersOfPages().addAll(numbersOfPages);
        return reviewPage;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        Review review = reviewDao.findById(id);
        if (Objects.nonNull(review)) {
            reviewDao.delete(review);
        } else {
            throw new ServiceException(String.format("Review is not found with id= ", id));
        }
    }

    @Override
    @Transactional
    public void changeVisibilityByIds(List<Long> selectedIds) {
        List<Review> reviews = reviewDao.findAll();
        List<Long> visibleIdsFromDB = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getIsVisible()) {
                visibleIdsFromDB.add(review.getId());
            }
        }
        for (Long id : visibleIdsFromDB) {
            if (!selectedIds.contains(id)) {
                log.info("This review id {}, wasn't contain in visibleIdsFromDB list, visibility will change", id);
                findReviewByIdUpdateVisible(id);
            }
        }
        for (Long id : selectedIds) {
            if (!visibleIdsFromDB.contains(id)) {
                log.info("This review id {}, wasn't contain in selectedIds list, visibility will change", id);
                findReviewByIdUpdateVisible(id);
            }
        }
    }

    @Override
    @Transactional
    public ReviewDTO addReview(ReviewDTO reviewDTO, String username) {
        Review review = reviewServiceConverter.convertDTOToReview(reviewDTO, username);
        reviewDao.add(review);
        return reviewServiceConverter.convertReviewToDTO(review);
    }

    private void findReviewByIdUpdateVisible(Long id) {
        Review review = reviewDao.findById(id);
        if (Objects.nonNull(review)) {
            review.setIsVisible(!review.getIsVisible());
        } else {
            throw new ServiceException(String.format("Review is not found with id= ", id));
        }
    }
}
