package com.positive.culture.seoulQuest.service;

import com.positive.culture.seoulQuest.domain.*;
import com.positive.culture.seoulQuest.dto.CartItemDTO;
import com.positive.culture.seoulQuest.dto.CartItemListDTO;
import com.positive.culture.seoulQuest.repository.CartItemRepository;
import com.positive.culture.seoulQuest.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class CartServiceImpl implements CartService{

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final CartItemRepository cartItemRepository;

    //1.카트아이템 정보를 받아와서 카트가 있는지 확인하고, 있으면 수량만 변경 or 없으면 카트 생성 후 수량변경
    // CartItemDTO의 사용 -> 1. cart에 product 추가시 사용 , 2. cartItemList에서 상품 수량을 조정하는 경우 사용
    @Override
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO) {

        //카트에 카트 아이템을 추가 할때 또는 카트아이템을 수정할때

        Long cino = cartItemDTO.getCino(); //카트 아이템 정보를 받아서 카트 아이템 번호 추출
        String memberId = cartItemDTO.getMemberId(); //카트 아이템 정보를 받아서 멤버 아이디 추출

        Long pno = cartItemDTO.getPno(); //카트 아이템 정보를 받아서 상품 번호를 추출
        int pqty = cartItemDTO.getPqty(); //카트 아이템 정보를 받아서 상품 수량을 추출

        //=====================================================================
        if(cino!=null){ // 이미 카트에 담겨있는 카트 아이템일경우.
            Optional<CartItem> cartItemResult = cartItemRepository.findById(cino);
            CartItem cartItem =  cartItemResult.orElseThrow();
            cartItem.changeProductQuantity(pqty);
            cartItemRepository.save(cartItem); //상품 수량 변경후 db에 저장
            //해당 카트에 있는 카트 아이템리스트를 멤버아이디로 조회하여 반환
            return getCartItems(memberId);
        }

        //======================================================================
        //카트에 담겨있지 않은 카트 아이템인 경우

        //사용자의 카트 가져옴 (이때 카트가 없으면 다시 생성)
        Cart cart = getCart(memberId);

        CartItem cartItem = null;

        //이미 동일한 상품이 담긴 적이 있을 수 있으므로 확인
        cartItem = cartItemRepository.getItemOfPno(memberId,pno);

        if(cartItem == null){ //동일한 상품이 담긴적이 없다면
            Product product = Product.builder().pno(pno).build();
            cartItem = CartItem.builder().product(product).cart(cart).pqty(pqty).build();
        }else {
            cartItem.changeProductQuantity(pqty);
        }

        //상품 아이템 저장
        cartItemRepository.save(cartItem);
        return getCartItems(memberId);
    }



    //2. 사용자의 장바구니가 없었다면 새로운 장바구니를 생성하고 반환
    private Cart getCart(String memberId){
        Cart cart = null;
        Optional<Cart> result = cartRepository.getCartOfMember(memberId);
        if(result.isEmpty()){
            log.info("Cart of the member is not exist!");
            Member member = Member.builder().memberId(memberId).build();
            Cart tempCart = Cart.builder().owner(member).build();
            cart=  cartRepository.save(tempCart);
        }else {
            cart = result.get();
        }
        return cart;
    }



    //3.멤버 아이디로 카트를 조회하여 해당 카트의 카트아이템리스트를 반환
    @Override
    public List<CartItemListDTO> getCartItems(String memberId) {

        //멤버아이디에 일치하는 카트 찾기
        List<Cart> cartlist =  cartRepository.findAll();
        Cart cart = cartlist.stream()
                .filter(i -> i.getOwner().equals(memberId)) // 조건에 맞는 Cart를 필터링
                .findFirst() // 첫 번째 요소를 반환 (memberId 에 일치하는 cart는 하나밖에 없으므로 )
                .orElse(null); // 조건에 맞는 요소가 없으면 null 반환

        //해당 카트 번호에 맞는 카트 아이템들을 조회하여 리스트로 변환
        List<CartItem> cartItemList = cartItemRepository.findCartItemByCartCno(cart.getCno());

        //리스트 안의 cartItem entity를 cartItem dto로 변환
        List<CartItemListDTO> cartItemListdtos = cartItemList.stream().map(i->{
                CartItemListDTO cartItemListDTO = CartItemListDTO.builder()
                        .cino(i.getCino())
                        .memberId(i.getCart().getOwner().getMemberId())
                        .pno(i.getProduct().getPno())
                        .pfiles(i.getProduct().getImageList().get(0).getFileName())
                        .pprice(i.getProduct().getPrice())
                        .pqty(i.getPqty())
                        .build();
                return cartItemListDTO;
        }).toList();

        return cartItemListdtos;
    }


    //4. 특정 카트 아이템을 삭제하고 나머지 cartItemList를 반환
    @Override
    public List<CartItemListDTO> remove(Long cino) {
        //cartItem 번호로  cartItem을 조회하여 cart 번호를 반환하여 저장해둠
        Optional<CartItem> cartItem = cartItemRepository.findCartItemByCino(cino);
        Long cno = cartItem.orElseThrow().getCart().getCno();

        log.info("cno:"+ cno);

        //카트 삭제
        cartRepository.deleteById(cno);

        //카트 아이템 리스트 반환
        List<CartItem> cartItemList = cartItemRepository.findCartItemByCartCno(cno);

        //List안의 CartItemEntity-> CartItemDTO로 변경
        List<CartItemListDTO> cartItemListdtos = cartItemList.stream().map(i->{
            CartItemListDTO cartItemListDTO = CartItemListDTO.builder()
                    .cino(i.getCino())
                    .memberId(i.getCart().getOwner().getMemberId())
                    .pno(i.getProduct().getPno())
                    .pfiles(i.getProduct().getImageList().get(0).getFileName())
                    .pprice(i.getProduct().getPrice())
                    .pqty(i.getPqty())
                    .build();
            return cartItemListDTO;
        }).toList();

        return cartItemListdtos;
    }
}
