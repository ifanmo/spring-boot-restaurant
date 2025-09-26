package com.ifanmorgan.restaurant.events;

import com.ifanmorgan.restaurant.events.dtos.CreateEventRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest createEventRequest);
    EventDto toDto(Event event);
}
