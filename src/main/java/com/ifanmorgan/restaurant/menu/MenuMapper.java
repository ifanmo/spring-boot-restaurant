package com.ifanmorgan.restaurant.menu;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuItemDto toDto(MenuItem item);
}
