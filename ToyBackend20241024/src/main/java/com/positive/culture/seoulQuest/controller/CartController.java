package com.positive.culture.seoulQuest.controller;

import com.positive.culture.seoulQuest.dto.CartItemDTO;
import com.positive.culture.seoulQuest.dto.CartItemListDTO;
import com.positive.culture.seoulQuest.service.CartService;
//import com.positive.culture.seoulQuest.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CompositeType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    //카트 아이템 추가, 수정
    @PostMapping("/change")
    public List<CartItemListDTO> changeCart(@RequestBody CartItemDTO itemDTO){
        log.info(itemDTO);

        if(itemDTO.getPqty()<=0){
            return cartService.remove(itemDTO.getCino());
        }
        return  cartService.addOrModify(itemDTO);
    }

    @GetMapping("/items")
    public List<CartItemListDTO> getCartItems(Principal principal){
        String id = principal.getName();
        log.info("id:" +id );

        return cartService.getCartItems(id);
    }
}
