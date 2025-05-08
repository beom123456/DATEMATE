package com.beom.datemate.entity;

import com.beom.datemate.constant.Role;
import com.beom.datemate.dto.SignupDto;
import com.beom.datemate.dto.WishListDto;
import com.beom.datemate.naver.SearchLocalRes;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name="wishlist")
public class WishListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_seq_gen")
    @SequenceGenerator(name = "wishlist_seq_gen", sequenceName = "wishlist_seq", allocationSize = 1)
    @Column(name="idx")
    private Long idx;

    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String roadAddress;             // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문여부
    private int visitCount;                 // 방문 카운트
    private LocalDateTime lastVisitDate;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_idx")
    private User user;

    public static WishListEntity wishDtoToEntity(WishListDto wishListDto, ModelMapper modelMapper) {
        WishListEntity wishListEntity = modelMapper.map(wishListDto, WishListEntity.class);
        return wishListEntity;
    }

    public static WishListDto wishEntityToDto(WishListEntity wishDtoToEntity, ModelMapper modelMapper) {
        WishListDto wishListDto = modelMapper.map(wishDtoToEntity, WishListDto.class);
        return wishListDto;
    }

}
