package com.ifanmorgan.restaurant.menu;

import com.ifanmorgan.restaurant.menu.dtos.MenuItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuItemDto toDto(MenuItem item);
}
