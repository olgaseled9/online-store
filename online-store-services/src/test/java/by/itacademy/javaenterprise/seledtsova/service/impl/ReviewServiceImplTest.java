package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ReviewServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.ReviewDao;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    ReviewDao reviewDao;
    @Mock
    ReviewServiceConverter converter;
    @InjectMocks
    ReviewServiceImpl reviewService;

    @Test
    void shouldSaveReviewAndReturnCorrectId() {
        Long id = 10L;
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(10L);
        Review review = new Review();
        when(converter.convertDTOToReview(reviewDTO, "")).thenReturn(review);
        reviewDao.add(review);
        when(converter.convertReviewToDTO(review)).thenReturn(reviewDTO);
        ReviewDTO savedReview = reviewService.addReview(reviewDTO, "");
        assertEquals(id, savedReview.getId());
    }
}