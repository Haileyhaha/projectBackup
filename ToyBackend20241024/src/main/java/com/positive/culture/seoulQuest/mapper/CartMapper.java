package com.positive.culture.seoulQuest.mapper;

import com.positive.culture.seoulQuest.domain.Cart;
import com.positive.culture.seoulQuest.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    Cart getCartByUserId(String userId);

}
