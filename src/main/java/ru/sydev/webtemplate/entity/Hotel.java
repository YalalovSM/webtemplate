package ru.sydev.webtemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Hotel
{
    @Id
    private UUID id;

    @NotNull
    @Size( max = 254 )
    private String name;

    @Size( max = 2048 )
    private String description;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @CreatedDate
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modified;
}
