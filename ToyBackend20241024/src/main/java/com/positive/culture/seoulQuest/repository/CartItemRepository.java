package com.positive.culture.seoulQuest.repository;

import com.positive.culture.seoulQuest.domain.CartItem;
import com.positive.culture.seoulQuest.domain.Product;
import com.positive.culture.seoulQuest.dto.CartItemListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

   //-----카트번호로 특정 카트에 대한 카트아이템들의 리스트를 찾아줌----------
   public List<CartItem> findCartItemByCartCno(Long cartCno);

   //-----새로운 상품을 장바구니에 담고자할때 기존 장바구니에 product가 있는지 확인하기위하여 필요----------
   @Query("select ci from CartItem ci inner join Cart c on ci.cart = c where c.owner.memberId = :memberId and ci.product.pno=:pno")
   public CartItem getItemOfPno(@Param("memberId") String memberId, @Param("pno")Long pno);

   //-----카트아이템번호로 카트아이템을 찾아줌
   public Optional<CartItem> findCartItemByCino(Long cartItemNo);


//
//    //1.-------------로그인 했을때 사용자가 담은 모든 장바구니 아이템 조회시에 사용 -------------------------------
//    @Query("select new com.positive.culture.seoulQuest.dto.CartItemListDTO(ci.cino, p.pname, p.price, ci.pqty, pi.fileName, t.tname, t.tprice, ci.tqty, ti.fileName, td.tourDate)" +
//            "from CartItem ci inner join Cart c on ci.cart = c " +
//            "left join Product p on ci.product = p " +
//            "left join p.imageList pi " +
//            "left join Tour t on ci.tour = t " +
//            "left join t.tDate td " + // 여기서 t.tDate로 TourDate에 접근
//            "left join t.tourImageList ti " +
//            "where c.owner.memberId = :memberId and pi.ord = 0 and ti.ord = 0 order by ci.cino desc")
//    public List<CartItemListDTO> getItemsOfCartDTOByMemberId(@Param("memberId") String memberId);
//
//
////    //member table에서 memberId로 조회하여 product에 대한 카트 아이템 리스트를 반환
////    @Query("select new com.positive.culture.seoulQuest.dto.CartItemProductListDTO(ci.cino, p.pname, p.price, ci.pqty, pi.fileName)" +
////            "from CartItem ci inner join Cart c on ci.cart = c " +
////            "left join Product p on ci.product = p " +
////            "left join p.imageList pi " +
////            "where c.owner.id = :id and pi.ord = 0 order by ci.cino desc")
////    public List<CartItemProductListDTO> getProductItemsOfCartDTOByMemberId(@Param("id") Long id);
////
////    //member table에서 memberId로 조회하여 tour에 대한 카트 아이템 리스트를 반환
////    @Query("select new com.positive.culture.seoulQuest.dto.CartItemTourListDTO(ci.cino, t.tname, t.tprice, ci.tqty, ti.fileName, td.tourDate) " +
////            "from CartItem ci inner join Cart c on ci.cart = c " +
////            "left join Tour t on ci.tour = t " +
////            "left join t.tDate td " + // 여기서 t.tDate로 TourDate에 접근
////            "left join t.tourImageList ti " +
////            "where c.owner.id = :id and ti.ord = 0 " +
////            "order by ci.cino desc")
////    public List<CartItemTourListDTO> getTourItemsOfCartDTOByMemberId(@Param("id") Long id);
//
//

////    @Query("select ci from CartItem ci inner join Cart c on ci.cart = c where c.owner.id = :id and ci.tour.tno=:tno")
////    public CartItem getItemOfTno(@Param("id") Long id, @Param("tno")Long tno);
//
//    //2.-------------새로운 상품을 장바구니에 담고자할때 기존 장바구니에 product나 tour가 있는지 확인하기위하여 필요----------
//    @Query("select ci from CartItem ci inner join Cart c on ci.cart = c where c.owner.id = :id and ci.product.pno=:pno and ci.tour.tno=:tno")
//    public CartItem getItemOfPnoAndTno(@Param("id") Long id, @Param("pno")Long pno, @Param("tno") Long tno);
//
//
//    //3.-------------해당 아이템을 삭제 한 후 , 해당 아이템이 속해있는 장바구니에 모든 아이템을 알아내기위해서 필요----------
//    @Query("select c.cno from Cart c inner join CartItem ci on ci.cart = c where ci.cino= :cino")
//    public Long getCartFromItem(@Param("cino")Long cino);

    //4.-------------특정한 장바구니 아이템을 삭제한 후에 해당 장바구니 아이템이 속해있는 장바구니의 모든 장바구니 아이템을 조회할때 필요-------------

//    @Query("select new com.positive.culture.seoulQuest.dto.CartItemListDTO(ci.cino, p.pname, p.price, ci.pqty, pi.fileName, t.tname, t.tprice, ci.tqty, ti.fileName, td.tourDate)" +
//            "from CartItem ci inner join Cart c on ci.cart = c " +
//            "left join Product p on ci.product = p " +
//            "left join p.imageList pi " +
//            "left join Tour t on ci.tour = t " +
//            "left join t.tDate td " +
//            "left join t.tourImageList ti " +
//            "where c.cno= :cno and pi.ord = 0 and ti.ord = 0 order by ci.cino desc")
//    public List<CartItemListDTO> getItemsOfCartDTOByCart(@Param("cno") Long cno);


//    //product
//    @Query("select new com.positive.culture.seoulQuest.dto.CartItemProductListDTO(ci.cino, p.pname, p.price, ci.pqty, pi.fileName)" +
//            "from CartItem ci inner join Cart c on ci.cart = c " +
//            "left join Product p on ci.product = p " +
//            "left join p.imageList pi " +
//            "where c.cno= :cno and pi.ord = 0 order by ci.cino desc")
//    public List<CartItemProductListDTO> getProductItemsOfCartDTOByCart(@Param("cno") Long cno);
//
//    //tour
//    @Query("select new com.positive.culture.seoulQuest.dto.CartItemTourListDTO(ci.cino, t.tname, t.tprice, ci.tqty, ti.fileName, td.tourDate)" +
//            "from CartItem ci inner join Cart c on ci.cart = c " +
//            "left join Tour t on ci.tour = t " +
//            "left join t.tDate td " +
//            "left join t.tourImageList ti " +
//            "where c.cno = :cno and ti.ord = 0 order by ci.cino desc")
//    public List<CartItemTourListDTO> getTourItemsOfCartDTOByCart(@Param("cno")Long cno);
}
