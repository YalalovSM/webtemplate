package ru.sydev.webtemplate.service.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sydev.webtemplate.entity.Hotel;
import ru.sydev.webtemplate.vo.HotelVO;

@Slf4j
@Component
public class HotelVOMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public HotelVO toVO(Hotel entity) {
        final HotelVO vo = modelMapper.map(entity, HotelVO.class);

        return vo;
    }

    public Hotel toEntity(HotelVO vo) {
        final Hotel entity = modelMapper.map(vo, Hotel.class);

        return entity;
    }
}
