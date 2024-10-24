package com.positive.culture.seoulQuest.mapper;

import com.positive.culture.seoulQuest.domain.Cart;
import com.positive.culture.seoulQuest.domain.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartItemMapper {
    List<CartItem> getCartItemsByCartId(Long cartId);

}
