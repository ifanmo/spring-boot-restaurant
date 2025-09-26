package com.ifanmorgan.restaurant.menu;

import com.ifanmorgan.restaurant.menu.dtos.MenuItemDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuItemRepository menuRepository;
    private final MenuMapper mapper;

    public List<MenuItemDto> getAllItems() {
        var items = menuRepository.findAll();
        return items
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MenuItemDto createOrUpdateSpecial(String name, BigDecimal price, String description) {
        var item = menuRepository.findByCategory(Category.SPECIAL).orElse(null);
        if (item == null) {
            var newItem = new MenuItem();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setDescription(description);
            newItem.setCategory(Category.SPECIAL);
            menuRepository.save(newItem);
            return mapper.toDto(newItem);
        } else {
            item.setName(name);
            item.setPrice(price);
            item.setDescription(description);
            menuRepository.save(item);
            return mapper.toDto(item);
        }

    }
}
