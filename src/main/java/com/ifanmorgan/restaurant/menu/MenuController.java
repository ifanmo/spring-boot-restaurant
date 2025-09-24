package com.ifanmorgan.restaurant.menu;


import com.ifanmorgan.restaurant.misc.ErrorDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllItems() {
        return ResponseEntity.ok(menuService.getAllItems());
    }

    @PreAuthorize("hasRole('CHEF')")
    @PostMapping("/special")
    public ResponseEntity<MenuItemDto> createOrUpdateSpecial(
            @Valid @RequestBody UpdateSpecialRequest request
    ) {
        var dto = menuService.createOrUpdateSpecial(request.getName(), request.getPrice(), request.getDescription());
        return ResponseEntity.ok(dto);
    }

}
