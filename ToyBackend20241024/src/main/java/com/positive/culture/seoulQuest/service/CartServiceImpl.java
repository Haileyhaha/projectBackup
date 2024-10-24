//package com.positive.culture.seoulQuest.service;
//
//import com.positive.culture.seoulQuest.domain.*;
//import com.positive.culture.seoulQuest.dto.CartItemDTO;
//import com.positive.culture.seoulQuest.dto.CartItemListDTO;
//import com.positive.culture.seoulQuest.repository.CartItemRepository;
//import com.positive.culture.seoulQuest.repository.CartRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//@Log4j2
//public class CartServiceImpl implements CartService{
//
//    @Autowired
//    private final CartRepository cartRepository;
//
//    @Autowired
//    private final CartItemRepository cartItemRepository;
//
//
//    //1.카트아이템 정보를 받아와서 카트가 있는지 확인하고, 있으면 수량만 변경 or 없으면 카트 생성 후 수량변경
//    @Override
//    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO) {
//        //프론트엔드에서 카트 아이템 정보를 받아옴
//
//        Long cino = cartItemDTO.getCino(); //카트 아이템 정보를 받아서 카트 아이템 번호 추출
//        String memberId = cartItemDTO.getMemberId(); //카트 아이템 정보를 받아서 멤버 아이디 추출
//
//        Long pno = cartItemDTO.getPno(); //카트 아이템 정보를 받아서 상품 번호를 추출
//        int pqty = cartItemDTO.getPqty(); //카트 아이템 정보를 받아서 상품 수량을 추출
////        int pprice = cartItemDTO.getPprice(); //수량 변경하므로 가격은 필요없음
//
//        Long tno = cartItemDTO.getTno(); //카트 아이템 정보를 받아서 투어 번호를 추출
//        int tqty = cartItemDTO.getTqty(); //카트 아이템 정보를 받아서 투어 수량을 추출
////        int tprice = cartItemDTO.getTprice(); //수량 변경하므로 가격은 필요없음
//
//        LocalDate tourDate = cartItemDTO.getTourDate(); //카트 아이템 정보를 받아서 투어 날짜를 추출
//
//        //카트에 담긴 아이템인 경우
//        if(cino!=null){
//            Optional<CartItem> cartItemResult = cartItemRepository.findById(cino);
//            CartItem cartItem =  cartItemResult.orElseThrow();
//            cartItem.changeProductQuantity(pqty);
//            cartItem.changeTourQuantity(tqty);
//            cartItemRepository.save(cartItem); //상품, 투어 수량 변경후 db에 저장
//            //해당 카트에 있는 카트 아이템리스트를 멤버아이디로 조회하여 반환
//            return getCartItems(memberId);
//        }
//
//        //======================================================================
//        //카트에 담긴 아이템이 없는 경우
//
//        //사용자의 카트 가져옴 (이때 카트가 없으면 다시 생성)
//        Cart cart = getCart(memberId);
//
//        CartItem cartItem = null;
//
//        //이미 동일한 상품이 담긴 적이 있을 수 있으므로
//        //카트번호로 카트 아이템을 조회하고, 카트아이템이 있으면
//        cartItem = cartItemRepository.findCartItemByCartCno(cart.getCno())
//                //2024-10-24 여기서 중단하고 toy project로 다시 해보고 적용하기로함.
//        //---------------------------------------------------------------------------
//
//        if(cartItem == null){
//            Product product = Product.builder().pno(pno).build();
//            Tour tour = Tour.builder().tno(tno).build();
//            cartItem = CartItem.builder().product(product).tour(tour).cart(cart).pqty(pqty).tqty(tqty).build();
//        }else {
//            cartItem.changeProductQuantity(pqty);
//            cartItem.changeTourQuantity(tqty);
//        }
//
//        //상품 아이템 저장
//        cartItemRepository.save(cartItem);
//        return getCartItems(memberId);
//    }
//
//
//
//    //2. 사용자의 장바구니가 없었다면 새로운 장바구니를 생성하고 반환
//    private Cart getCart(String memberId){
//        Cart cart = null;
//        Optional<Cart> result = cartRepository.getCartOfMember(memberId);
//        if(result.isEmpty()){
//            log.info("Cart of the member is not exist!");
//            Member member = Member.builder().memberId(memberId).build();
//            Cart tempCart = Cart.builder().owner(member).build();
//            cart=  cartRepository.save(tempCart);
//        }else {
//            cart = result.get();
//        }
//        return cart;
//    }
//
//
//
//    //3.멤버 아이디로 카트를 조회하여 해당 카트의 카트아이템리스트를 반환
//    @Override
//    public List<CartItemListDTO> getCartItems(String memberId) {
//        List<Cart> cartlist =  cartRepository.findAll();
//        Cart cart = cartlist.stream()
//                .filter(i -> i.getOwner().equals(memberId)) // 조건에 맞는 Cart를 필터링
//                .findFirst() // 첫 번째 요소를 반환
//                .orElse(null); // 조건에 맞는 요소가 없으면 null 반환
//
//        //해당 카트 번호에 맞는 카트 아이템들을 조회하여 리스트로 변환
//        List<CartItem> cartItemList = cartItemRepository.findCartItemByCartCno(cart.getCno());
//
//        //카트아이템 리스트 엔티티를 카트 아이템 리스트 dto로 변환
//        List<CartItemListDTO> cartItemListdtos = cartItemList.stream().map(i->{
//                CartItemListDTO cartItemListDTO = CartItemListDTO.builder()
//                        .pname(i.getProduct().getPname())
//                        .pprice(i.getPprice())
//                        .pqty(i.getPqty())
//                        .pfiles(i.getProduct().getImageList().get(0).getFileName())
//                        .tname(i.getTour().getTname())
//                        .tprice(i.getTprice())
//                        .tqty(i.getTqty())
//                        .tfiles(i.getTour().getTourImageList().get(0).getFileName())
//                        .tDate(i.getTourDate().getTourDate())
//                .build();
//                return cartItemListDTO;
//        }).toList();
//
//        return cartItemListdtos;
//    }
//
//    //4. 아직 안함
//    @Override
//    public List<CartItemListDTO> remove(Long cino) {
//        return List.of();
//    }
//}
