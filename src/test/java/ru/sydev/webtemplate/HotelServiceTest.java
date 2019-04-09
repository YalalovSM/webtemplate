package ru.sydev.webtemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sydev.webtemplate.service.ErrorResponse;
import ru.sydev.webtemplate.service.HotelService;
import ru.sydev.webtemplate.vo.HotelVO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HotelServiceTest {
    @Autowired
    HotelService hotelService;

    @Test
    void testCreate() {
        final HotelVO vo = createContent();

        final ResponseEntity<HotelVO> responseEntity = hotelService.create(vo);
        final HotelVO created = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "Status code should be CREATED");
        assertNotNull(created, "Should not be null");
        assertEquals(vo.getName(), responseEntity.getBody().getName(), "Hotel name should be the same as passed");
    }

    @Test
    void testGet_Ok() {
        final HotelVO vo = createContent();

        final HotelVO created = hotelService.create(vo).getBody();
        assertNotNull(created, "Should not be null");

        ResponseEntity<HotelVO> responseEntity = hotelService.getHotel(created.getId());
        HotelVO received = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(received.getId());
        assertEquals(vo.getName(), received.getName());
        assertEquals(vo.getDescription(), received.getDescription());
    }

    @Test
    void testGet_Not_Found() {
        ErrorResponse exception = assertThrows(ErrorResponse.class, () -> {
            hotelService.getHotel(UUID.randomUUID());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Status code should be NOT_FOUND");
    }

    @Test
    void testDelete_Ok() {
        final HotelVO vo = createContent();

        final HotelVO created = hotelService.create(vo).getBody();

        assertNotNull(created, "Should not be null");
        hotelService.deleteHotel(created.getId());

        ErrorResponse exception = assertThrows(ErrorResponse.class, () -> {
            hotelService.getHotel(created.getId());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Status code should be NOT_FOUND");
    }

    @Test
    void testDelete_Not_Found() {
        ErrorResponse exception = assertThrows(ErrorResponse.class, () -> {
            hotelService.deleteHotel(UUID.randomUUID());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Status code should be NOT_FOUND");
    }

    private HotelVO createContent() {
        final HotelVO vo = new HotelVO();

        vo.setName("Nugush Resort");
        vo.setDescription("Bashkirian resort");

        return vo;
    }
}
