package ru.sydev.webtemplate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sydev.webtemplate.service.HotelService;
import ru.sydev.webtemplate.vo.HotelVO;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService service;

    @PostMapping
    public ResponseEntity<HotelVO> create(@Valid @RequestBody final HotelVO vo) {
        return service.create(vo);
    }

    @GetMapping( path = "/{id}" )
    public ResponseEntity<HotelVO> getHotel(@PathVariable final UUID id) {
        return service.getHotel(id);
    }

    @DeleteMapping( path = "/{id}" )
    public ResponseEntity deleteHotel(@PathVariable final UUID id) {
        service.deleteHotel(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
