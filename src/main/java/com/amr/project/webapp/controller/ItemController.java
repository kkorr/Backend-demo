package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
public class ItemController {

    private ItemService itemService;
    private ReviewService reviewService;

    @Autowired
    public ItemController(ItemService itemService, ReviewService reviewService) {
        this.itemService = itemService;
        this.reviewService = reviewService;
    }

    @GetMapping("/product/{id}")
    public String getItemPage(@PathVariable("id") Long id, Model model, @ModelAttribute("review") Review review) {
        model.addAttribute("item", itemService.getByKey(id));
        model.addAttribute("reviews", itemService.getByKey(id).getReviews());
        model.addAttribute("newReview", new Review());
        return "product_page";
    }

    @PostMapping("/newReview")
    public String addReview(@ModelAttribute("review") Review review) {

//        Collection<Review> reviews = itemService.getByKey(id).getReviews();
//        review.setDate(new java.sql.Date(System.currentTimeMillis()));
//        reviews.add(review);
//        itemService.getByKey(id).setReviews(reviews);
        System.out.println(review.toString());
        reviewService.persist(review);

        return "redirect:/home";
    }
}