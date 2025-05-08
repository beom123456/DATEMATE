package com.beom.datemate.dto;

import com.beom.datemate.entity.User;
import com.beom.datemate.naver.SearchLocalRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {

    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String roadAddress;             // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문여부
    private int visitCount;                 // 방문 카운트
    private LocalDateTime lastVisitDate;    // 마지막 방문일자

    private User user;

    private static ModelMapper modelMapper = new ModelMapper();

    public static  WishListDto toResult(SearchLocalRes.SearchLocalItem searchLocalItem, ModelMapper modelMapper){

        WishListDto wishListDto = modelMapper.map(searchLocalItem, WishListDto.class);

        return wishListDto;

    }
}
