package com.positive.culture.seoulQuest.mapper;

import com.positive.culture.seoulQuest.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> getProductByPno(Long  pno);
}
