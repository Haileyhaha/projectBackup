package com.positive.culture.seoulQuest.mapper;

import com.positive.culture.seoulQuest.dto.TourDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<TourDTO> getTourByTno(Long  tourId);
}
