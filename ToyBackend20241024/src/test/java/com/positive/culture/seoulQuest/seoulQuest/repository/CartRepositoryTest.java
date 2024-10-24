//package com.positive.culture.seoulQuest.seoulQuest.repository;
//
//import com.positive.culture.seoulQuest.domain.*;
//import com.positive.culture.seoulQuest.dto.CartItemListDTO;
//import com.positive.culture.seoulQuest.repository.CartItemRepository;
//import com.positive.culture.seoulQuest.repository.CartRepository;
//import jakarta.transaction.Transactional;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Log4j2
//@SpringBootTest
//public class CartRepositoryTest {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//
////    //memberId로 카트 조회
////    @Test
////    public void testGetCartbyId(){
////        Optional<Cart> cart = cartRepository.getCartOfMember(1l);
////    }
//
//    //cart item repository테스트
////    @Test
////    public void testGetCartItemList1(){
////        List<CartItemListDTO> cartItemListDTOS = cartItemRepository.getItemsOfCartDTOByMemberId("user1");
////     }
//
//
//
//
////    //화면에서 특정한 상품을 선택해서 장바구니에 추가하는 경우 테스트
////    @Transactional
////    @Commit
////    @Test
////    public void testInsertByProduct(){
////        Long id = 2l; //Member id
////        Long pno= 6l;
////        int pqty = 10;
////        Long tno= 4l;
////        int tqty = 1;
////
////        //기존에 장바구니 아이템이 있었다면
////        //기존에 담겨있는 product, tour인지 확인 필요
////        CartItem cartItem = cartItemRepository.getItemOfPnoAndTno(id,pno,tno);
////        if(cartItem!=null){ //기존에 담겨있는 아이템이라면
////            cartItem.changeProductQuantity(pqty);
////            cartItem.changeTourQuantity(tqty);
////            cartItemRepository.save(cartItem);
////
////            return;
////        }
////
////        //장바구니 아이템이 없었다면 장바구니 존재 부터 확인필요
////        //사용자가 장바구니를 만든적이 있는지 확인
////        Optional<Cart> result = cartRepository.getCartOfMember(id);
////        Cart cart = null;
////
////        //사용자의 장바구니가 존재하지 않으면 장바구니 생성
////        if(result.isEmpty()){
////            log.info("memberCart is not exist!");
////            Member member= Member.builder().id(id).build();
////            Cart tempCart = Cart.builder().owner(member).build();
////            cart = cartRepository.save(tempCart);
////        }else {
////            cart = result.get();
////        }
////        log.info(cart);
////        //------------------------------------------------------------------
////        if(cartItem==null){
////            Product product = Product.builder().pno(pno).build();
////            Tour tour = Tour.builder().tno(tno).build();
////            cartItem = CartItem.builder().product(product).tour(tour).cart(cart).pqty(pqty).tqty(tqty).build();
////        }
////
////        //상품 아이템 저장
////        cartItemRepository.save(cartItem);
////    }
//
//    //장바구니 아이템 수정
//    @Test
//    @Commit
//    public void testUPdateByCino(){
//        Long cino = 1l;
//        int pqty = 5;
//        int tqty = 2;
//
//        Optional<CartItem> result = cartItemRepository.findById(cino);
//        CartItem cartItem = result.orElseThrow();
//        cartItem.changeProductQuantity(pqty);
//        cartItem.changeTourQuantity(tqty);
//        cartItemRepository.save(cartItem);
//    }
//
//    //로그인시 장바구니 아이템 목록 테스트
////    @Test
////    public void testListOfMember(){
////        String memberid ="user1";
////
////        List<CartItemListDTO> CartItemList =  cartItemRepository.getItemsOfCartDTOByMemberId(memberid);
//
////        // 결과 확인
////        for(CartItemListDTO dto :CartItemList){
////            log.info(dto.toString());
////        }
//
////        if (CartItemList.isEmpty()) {
////            log.info("No items found for member ID: " + memberid);
////        } else {
////            for (CartItemListDTO dto : CartItemList) {
////                log.info(dto.toString());
////            }
////        }
////
////    }
//
//
////    @Test
////    public void testDeleteThenList(){
////        Long cino = 1l;
////
////        //장바구니 번호
////        Long cno = cartItemRepository.getCartFromItem(cino);
////
////        //목록
////        List<CartItemListDTO> cartItemListDTOs = cartItemRepository.getItemsOfCartDTOByCart(cno);
////
////        for(CartItemListDTO dto :cartItemListDTOs){
////            log.info(dto);
////        }
////
////    }
//
//
//}
