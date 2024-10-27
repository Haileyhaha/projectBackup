package com.positive.culture.seoulQuest.seoulQuest.repository;

import com.positive.culture.seoulQuest.domain.*;
import com.positive.culture.seoulQuest.dto.*;
import com.positive.culture.seoulQuest.mapper.*;
import com.positive.culture.seoulQuest.repository.*;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@SpringBootTest
public class CartRepositoryTest3 {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourDateRepository tourDateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    //insertCartItem
    @Test
    public void insertCartItem() {

        // Assuming you already have an existing cart, tour, and product in the database.
        Cart cart = cartRepository.findById(1L).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(5L).orElse(null); // Optional, if it's a product item

        CartItem cartItem = CartItem.builder()
                .cart(cart)   // Associate with existing Cart
                .product(product)  // Associate with existing Product if it's a product item
                .pqty(51)      // Set quantity for product (if applicable)
                .build();

        cartItemRepository.save(cartItem);  // Save CartItem to the database
    }


    @Transactional
    @Test
    public void cinoTest(){
        Long cino = 1l;
        Optional<CartItem> cartItem = cartItemRepository.findCartItemByCino(cino);
        log.info(cartItem);
    }

//    @Test
//    @Transactional
//    public void getCartItems() {
//        //유저 아이디를 받아서 유저아이디로 카트 번호를 조회하고, 카트 번호로 카트 아이템들을 조회 하여,
//        //조회한 카트 아이템들을 리스트로 반환
//
//        String member = "user1";
//        Cart cart = cartRepository.getCartOfMember(member).orElseThrow(() -> new RuntimeException("Cart not found"));
//        // Fetch a cart by ID
////        Cart cart = cartRepository.findById(1L).orElseThrow(() -> new RuntimeException("Cart not found"));
//        System.out.println("cart: " + cart);
//        List<CartItem> cartItems = cartItemRepository.findCartItemByCartCno(cart.getCno());
//        System.out.println("cartItemList: " + cartItems);
//
//        //cartItem entity ->  cartItemList DTO
//        List<CartItemListDTO> cartItemDTOs = cartItems.stream().map(cartItem ->
//            CartItemListDTO.builder()
//                    .cino(cartItem.getCino())
//                    .memberId(cartItem.getCart().getOwner().getMemberId())
//                    .pname(cartItem.getProduct().getPname())
//                    .pprice(cartItem.getPprice())
//                    .pqty(cartItem.getPqty())
//                    .pfiles(cartItem.getProduct().getImageList().get(0).getFileName())
//                    .tname(cartItem.getTour().getTname())
//                    .tprice(cartItem.getTprice())
//                    .tqty(cartItem.getTqty())
//                    .tfiles(cartItem.getTour().getTourImageList().get(0).getFileName())
////                    .tDate(cartItem.getTourDate().getTourDate())
//                    .build()
//
//        ).toList();
//
//        System.out.println("Combined cartItemDTO: " + cartItemDTOs);

////
//
//        // Fetch product data by its ID (for example: 2L)
////        List<ProductDTO> productDTOList = productMapper.getProductByPno(2L);
////        ProductDTO productDTO = productDTOList.isEmpty() ? null : productDTOList.get(0);
//
////        List<Product> productList = productRepository.findAllById(Arrays.asList(2l));
////        // Fetch tour data by its ID (for example: 2L)
////        List<Tour> tourList = tourRepository.findAllById(Arrays.asList(2l));
//
//// Converting Product entities to ProductDTO
////        List<ProductDTO> productDTOList = productList.stream().map(product ->
////                ProductDTO.builder()
////                        .pno(product.getPno())
////                        .categoryName(product.getCategory().getCategoryName()) // Assuming category has a name
////                        .pname(product.getPname())
////                        .pdesc(product.getPdesc())
////                        .price(product.getPrice())
////                        .quantity(product.getQuantity())
////                        .shippingCost(product.getShippingCost())
////                        .createAt(product.getCreateAt())
////                        .updateAt(product.getUpdateAt())
////                        .delFlag(product.isDelFlag())
////                        .likesCount(product.getLikesCount())
////                        .uploadFileNames(product.getImageList().stream().map(ProductImage::getFileName).collect(Collectors.toList())) // Collect image filenames
////                        .build()
////        ).collect(Collectors.toList());
////
////// Converting Tour entities to TourDTO
////        List<TourDTO> tourDTOList = tourList.stream().map(tour ->
////                TourDTO.builder()
////                        .tno(tour.getTno())
////                        .tname(tour.getTname())
////                        .categoryName(tour.getCategory().getCategoryName()) // Assuming category has a name
////                        .tdesc(tour.getTdesc())
////                        .tprice(tour.getTprice())
////                        .max_capacity(tour.getMax_capacity())
////                        .tlocation(tour.getTlocation())
////                        .likeCount(tour.getLikeCount())
////                        .tDate(tour.getTDate()) // Assuming this is a List<TourDate>
////                        .createAt(tour.getCreateAt())
////                        .updateAt(tour.getUpdateAt())
////                        .delFlag(tour.isDelFlag())
////                        .uploadFileNames(tour.getTourImageList().stream().map(TourImage::getFileName).collect(Collectors.toList())) // Collect image filenames
////                        .build()
////        ).collect(Collectors.toList());
//
//        // Creating ProductAndTourListDTO and setting the lists
//        ProductAndTourListDTO productAndTourListDTO = new ProductAndTourListDTO();
//        productAndTourListDTO.setCartItemDTOS();
////        productAndTourListDTO.setProductDTOList(list);
////        productAndTourListDTO.setTourDTOList(tourDTOList);
//
//        // Print the combined result
//        System.out.println("Combined cartItemDTO: " + productAndTourListDTO);
//    }
}
