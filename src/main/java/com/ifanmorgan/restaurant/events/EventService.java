package com.ifanmorgan.restaurant.events;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
import com.ifanmorgan.restaurant.users.customers.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

        return eventMapper.toResponse(event);
    }
}
