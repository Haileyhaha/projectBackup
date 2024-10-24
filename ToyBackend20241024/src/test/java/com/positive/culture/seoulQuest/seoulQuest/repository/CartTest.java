package com.positive.culture.seoulQuest.seoulQuest.repository;

import com.positive.culture.seoulQuest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.positive.culture.seoulQuest.repository.CartRepository;
import com.positive.culture.seoulQuest.repository.MemberRepository;
import com.positive.culture.seoulQuest.domain.Cart;
import com.positive.culture.seoulQuest.domain.Member;

@SpringBootTest
public class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberRepository memberRepository;

    //insert cart
    @Test
    public void insertCart() {
        // Fetch the existing Member entity by memberId
        Member member = memberRepository.findByMemberId("user1")
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Create a new Cart and associate it with the fetched member
        Cart cart = Cart.builder()
                .owner(member)  // Assign the fetched Member object to the owner
                .status("active")  // Set the cart status
                .build();

        // Save the cart to the database
        cartRepository.save(cart);
    }

}
