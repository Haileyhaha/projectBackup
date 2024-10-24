//package com.positive.culture.seoulQuest.seoulQuest.repository;
//
//import com.positive.culture.seoulQuest.domain.Cart;
//import com.positive.culture.seoulQuest.domain.CartItem;
//import com.positive.culture.seoulQuest.dto.MemberDTO;
//import com.positive.culture.seoulQuest.dto.ProductDTO;
//import com.positive.culture.seoulQuest.dto.TourDTO;
//import com.positive.culture.seoulQuest.dto.UserDTO;
//import com.positive.culture.seoulQuest.mapper.*;
//import lombok.extern.log4j.Log4j2;
//import org.hibernate.type.descriptor.java.CalendarTimeJavaType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@Log4j2
//@SpringBootTest
//public class CartRepositoryTest2 {
//
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private ProductMapper productMapper;
//
//    @Autowired
//    private TourMapper tourMapper;
//
//    @Autowired
//    private CartMapper cartMapper;
//
//    @Autowired
//    private CartItemMapper cartItemMapper;
//
//
//    @Test
//    public void testUser(){
//        UserDTO user = userMapper.getUserByMemberId("user1");
//        System.out.println(user);
//    }
//
//    @Test
//    public void testProduct(){
//        List<ProductDTO> product = productMapper.getProductByPno(1l);
//        System.out.println("product"+product);
//    }
//
//    @Test
//    public void testTour() {
//        List<TourDTO> tourDTOList = tourMapper.getTourByTno(1l);
//        tourDTOList.forEach(i-> System.out.println(i));
//    }
//
//    @Test
//    public void testCart(){
//        Cart cart = cartMapper.getCartByUserId("1");
//        System.out.println("cart"+cart);
//
//    }
//
////    @Test
////    public void test(){
////        String MemberId = "user1";
////        UserDTO userDTO = userMapper.getUserByMemberId(MemberId);
////        Cart cart = cartMapper.getCartByUserId(userDTO.getMemberId());
////        log.info(cart);
////        List<CartItem> cartItems = cartItemMapper.getCartItemsByCartId(cart.getCno());
////        cartItems.forEach(i->{
////            List<ProductDTO> productDTOS = productMapper.getProductByPno(i.getProduct().getPno());
////            List<TourDTO> tourDTOS = tourMapper.getTourByTno(i.getTour().getTno());
////
////            for (ProductDTO productDTO : productDTOS) {
////                log.info(productDTO);
////            }
////
////            for(TourDTO tourDTO : tourDTOS){
////                log.info(tourDTO);
////            }
////        });
//
//
////                productMapper.getProductByPno();
////                        tourMapper.getTourByTno();
////    }
//
//}
