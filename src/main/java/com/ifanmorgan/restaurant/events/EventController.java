package com.ifanmorgan.restaurant.events;

import com.ifanmorgan.restaurant.carts.CartNotFoundException;
import com.ifanmorgan.restaurant.menu.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.misc.ErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        var events = eventService.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    @PostMapping
    public ResponseEntity<CreateEventResponse> create(@RequestBody CreateEventRequest request) {
        var eventResponse = eventService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        eventService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        eventService.complete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({EventAlreadyApprovedException.class, EventAlreadyComplete.class})
    public ResponseEntity<ErrorDto> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }
}
