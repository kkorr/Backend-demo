package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/shop/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public String getItemPage(@PathVariable("id") Long id, Model model, @ModelAttribute("review") Review review) {
        model.addAttribute("item", itemService.getByKey(id));
        model.addAttribute("reviews", itemService.getByKey(id).getReviews());
        return "product_page";
    }

    @PostMapping("/{id}")
    public String addReview(@PathVariable("id") Long id, @ModelAttribute("review") Review review, @ModelAttribute("rating") int rating) {
        Collection<Review> reviews = itemService.getByKey(id).getReviews();
        review.setRating(rating);
        review.setDate(new java.sql.Date(System.currentTimeMillis()));
        reviews.add(review);
        itemService.getByKey(id).setReviews(reviews);
        return "redirect:/shop/item/{id}";
    }
}
