package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.MenuItemDto;
import com.ifanmorgan.restaurant.entities.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuItemDto toDto(MenuItem item);
}
