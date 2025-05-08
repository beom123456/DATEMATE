package com.beom.datemate.service;

import com.beom.datemate.dto.WishListDto;
import com.beom.datemate.entity.User;
import com.beom.datemate.entity.WishListEntity;
import com.beom.datemate.naver.*;
import com.beom.datemate.repository.LoginRepository;
import com.beom.datemate.repository.WishListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    private final NaverClient naverClient;
    private final SearchLocalReq searchLocalReq;
    private final WishListRepository wishListRepository;
    private final LoginRepository loginRepository;


    public WishListService(NaverClient naverClient, SearchLocalReq searchLocalReq, WishListRepository wishListRepository, LoginRepository loginRepository) {
        this.naverClient = naverClient;
        this.searchLocalReq = searchLocalReq;
        this.wishListRepository = wishListRepository;
        this.loginRepository = loginRepository;

    }


    public List<WishListDto> search(String query) {

        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);

        List<WishListDto> results = new ArrayList<>();


        if(searchLocalRes.getTotal() >0){
            for(SearchLocalRes.SearchLocalItem localItem : searchLocalRes.getItems()) {

                String imageQuery = localItem.getTitle().replace("<[^>]*>", "");

                SearchImageReq imageReq = new SearchImageReq();
                imageReq.setQuery(imageQuery);
                SearchImageRes imageRes = naverClient.searchImage(imageReq);

                WishListDto dto = WishListDto.toResult(localItem, new ModelMapper());
                if(imageRes.getTotal() >0){
                    SearchImageRes.SearchImageItem imageItem = imageRes.getItems().get(0);
                    dto.setImageLink(imageItem.getLink());
                }
                results.add(dto);
            }
        }
        return results;
    }

    public WishListDto add(WishListDto wishListDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = loginRepository.findByUsername(username);
        if(userOptional.isPresent()){
            wishListDto.setUser(userOptional.get());
        }

        WishListEntity entity =  WishListEntity.wishDtoToEntity(wishListDto, new ModelMapper());
        WishListEntity saveEntity = wishListRepository.save(entity);

        return WishListEntity.wishEntityToDto(saveEntity,new ModelMapper());

    }

}
