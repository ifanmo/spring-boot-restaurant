package com.ifanmorgan.restaurant.events;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest createEventRequest);
    EventDto toDto(Event event);
}
