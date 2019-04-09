package ru.sydev.webtemplate.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
public class HotelVO
{
    private UUID id;

    @NotNull
    @Size( max = 254 )
    private String name;

    @Size( max = 2048 )
    private String description;
}
