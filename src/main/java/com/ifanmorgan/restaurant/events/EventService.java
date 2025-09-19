package com.ifanmorgan.restaurant.events;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
import com.ifanmorgan.restaurant.users.customers.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final AuthService authService;
    private final CustomerRepository customerRepository;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public CreateEventResponse create(CreateEventRequest request) {
        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var event = eventMapper.toEntity(request);
        event.setCustomer(customer);
        event.setStatus(EventStatus.PENDING);
        eventRepository.save(event);

        return new CreateEventResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getTime(),
                event.getDescription());

    }

    public void approve(Long id) {
        var event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }
        if (event.getStatus() != EventStatus.PENDING) {
            throw new EventAlreadyApprovedException();
        }

        event.setStatus(EventStatus.APPROVED);
        eventRepository.save(event);
    }

    public void complete(Long id) {
        var event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }
        if (event.getStatus() != EventStatus.APPROVED) {
            throw new EventAlreadyComplete();
        }

        event.setStatus(EventStatus.FINISHED);
        eventRepository.save(event);
    }

    public List<EventDto> getAllEvents() {
        var events = eventRepository.findAllByStatus(EventStatus.APPROVED);
        return events
                .stream()
                .map(eventMapper::toDto)
                .toList();
    }
}
