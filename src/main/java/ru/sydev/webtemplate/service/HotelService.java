package ru.sydev.webtemplate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sydev.webtemplate.entity.Hotel;
import ru.sydev.webtemplate.repository.HotelRepository;
import ru.sydev.webtemplate.service.mapper.HotelVOMapper;
import ru.sydev.webtemplate.vo.HotelVO;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelVOMapper hotelVOMapper;
    private final HotelRepository repository;

    public ResponseEntity<HotelVO> create(final HotelVO vo) {
        final Hotel hotel = hotelVOMapper.toEntity(vo);

        hotel.setId(UUID.randomUUID());

        final ResponseEntity<HotelVO> response = toResponse(
                hotelVOMapper.toVO(repository.save(hotel)), HttpStatus.CREATED
        );

        return response;
    }

    public ResponseEntity<HotelVO> getHotel(final UUID id) {
        final Hotel hotel = repository.findById(id)
                .orElseThrow( () -> new ErrorResponse( HttpStatus.NOT_FOUND, "hotel not found" ) );

        return toResponse(hotelVOMapper.toVO(hotel), HttpStatus.OK);
    }

    public Void deleteHotel(final UUID id) {
        final Hotel hotel = repository.findById(id)
                .orElseThrow( () -> new ErrorResponse( HttpStatus.NOT_FOUND, "hotel not found" ) );

        repository.delete(hotel);

        return null;
    }

    private ResponseEntity<HotelVO> toResponse(HotelVO vo, HttpStatus status) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status( status );

        return builder.body(vo);
    }
}
