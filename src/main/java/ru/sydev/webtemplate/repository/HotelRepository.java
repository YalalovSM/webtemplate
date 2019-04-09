package ru.sydev.webtemplate.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sydev.webtemplate.entity.Hotel;

import java.util.UUID;

public interface HotelRepository extends CrudRepository<Hotel, UUID>
{
}
