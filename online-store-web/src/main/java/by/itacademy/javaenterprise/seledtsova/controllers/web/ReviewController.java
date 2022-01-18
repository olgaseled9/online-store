package by.itacademy.javaenterprise.seledtsova.controllers.web;

import by.itacademy.javaenterprise.seledtsova.dto.ReviewDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ReviewPageDTO;
import by.itacademy.javaenterprise.seledtsova.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/add")
    public String addReviewPage(ReviewDTO reviewDTO, Model model) {
        model.addAttribute("localDate", LocalDate.now());
        return "add_review";
    }

    @PostMapping("/add")
    public String addReview(@Valid ReviewDTO reviewDTO,
                            BindingResult bindingResult,
                            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "add_review";
        } else {
            reviewService.addReview(reviewDTO, principal.getName());
            return "redirect:/reviews/get";
        }
    }
    @GetMapping("/get")
    public String getReviews(Model model,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam(value = "page", defaultValue ="1") int pageNumber
    ) {
        ReviewPageDTO reviewPage = reviewService.findReviewsWithPagination(pageNumber, pageSize);
        model.addAttribute("reviewPage", reviewPage);
        return "get_all_reviews";
    }

    @GetMapping("/remove/{id}")
    public String removeReview(@PathVariable Long id) {
        reviewService.removeById(id);
        return "redirect:/reviews/get";
    }

    @PostMapping("/change")
    public String changeVisibilityById(@RequestParam(value = "selectedIds", required = false) List<Long> selectedIds) {
        reviewService.changeVisibilityByIds(Objects.requireNonNullElse(selectedIds, Collections.emptyList()));
        return "redirect:/reviews/get";
    }
}
