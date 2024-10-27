package com.positive.culture.seoulQuest.controller;

import com.positive.culture.seoulQuest.dto.CartItemDTO;
import com.positive.culture.seoulQuest.dto.CartItemListDTO;
import com.positive.culture.seoulQuest.service.CartService;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

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
//    @GetMapping("/items")
//    public List<CartItemListDTO> getCartItems(Principal principal){
//        String memberId = principal.getName();
//        log.info("memberId:" +memberId);
//        return cartService.getCartItems(memberId);
//    }

    //카트 아이템 삭제
    @DeleteMapping("/{cino}")
    public List<CartItemListDTO> removeFromCart(@PathVariable("cino") Long cino){
        log.info("cart item no : " + cino);
        return cartService.remove(cino);
    }
}
