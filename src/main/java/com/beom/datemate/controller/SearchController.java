package com.beom.datemate.controller;

import com.beom.datemate.dto.WishListDto;
import com.beom.datemate.service.WishListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SearchController {

    private final WishListService wishListService;

    public SearchController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/search")
    public List<WishListDto> search(@RequestParam String query){
        return wishListService.search(query);
    }

    @PostMapping("/wishlist")
    public WishListDto addWishList(@RequestBody WishListDto wishListDto){
        return wishListService.add(wishListDto);
    }
}
