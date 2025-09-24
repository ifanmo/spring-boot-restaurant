package com.ifanmorgan.restaurant.events;

import com.ifanmorgan.restaurant.misc.ErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllUpcomingEvents() {
        var events = eventService.getAllUpcomingEvents();
        return ResponseEntity.ok().body(events);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<CreateEventResponse> create(@RequestBody CreateEventRequest request) {
        var eventResponse = eventService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        eventService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        eventService.complete(id);
        return ResponseEntity.ok().build();
    }

}
