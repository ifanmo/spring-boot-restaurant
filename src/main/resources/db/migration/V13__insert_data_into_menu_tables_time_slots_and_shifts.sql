
INSERT INTO restaurant_tables (capacity) VALUES
                                             (2),(2), (2), (2), (4),(4),(4), (8),(8),(10);

-- Bulk insert time slots (8 hourly slots)
INSERT INTO time_slots (start_time) VALUES
                                        ('17:00:00'),
                                        ('18:00:00'),
                                        ('19:00:00'),
                                        ('20:00:00'),
                                        ('21:00:00'),
                                        ('22:00:00'),
                                        ('23:00:00'),
                                        ('00:00:00');

-- Bulk insert shifts (3 shifts)
INSERT INTO shifts (start_time, end_time) VALUES

                                          ('17:00:00', '18:00:00'),
                                          ('18:00:00', '19:00:00'),
                                          ('20:00:00', '21:00:00'),
                                          ('21:00:00', '22:00:00'),
                                          ('22:00:00', '23:00:00'),
                                          ('23:00:00', '00:00:00'),
                                          ('00:00:00', '01:00:00');


INSERT INTO menu_items (name, price, description, category) VALUES
                                                                ('Margherita', 8.99, 'Classic pizza with tomato sauce and mozzarella cheese.', 'MAIN'),
                                                                ('Pepperoni Feast', 10.49, 'Loaded with spicy pepperoni and extra cheese.', 'MAIN'),
                                                                ('Veggie Supreme', 9.25, 'Topped with fresh vegetables and herbs.', 'MAIN'),
                                                                ('BBQ Chicken', 11.50, 'Grilled chicken with smoky BBQ sauce and red onions.', 'MAIN'),
                                                                ('Hawaiian Delight', 9.75, 'Ham and pineapple on a mozzarella base.', 'MAIN'),
                                                                ('Greek Salad', 6.50, 'Tomatoes, cucumbers, olives, and feta cheese.', 'STARTER'),
                                                                ('Caesar Salad', 6.95, 'Romaine lettuce with Caesar dressing and croutons.', 'STARTER'),
                                                                ('Garden Mix', 5.99, 'A mix of greens, tomatoes, cucumbers, and carrots.', 'STARTER'),
                                                                ('Coca-Cola', 2.00, 'Classic chilled Coca-Cola can (330ml).', 'DRINKS'),
                                                                ('Orange Juice', 2.50, 'Fresh squeezed orange juice with no added sugar.', 'DRINKS'),
                                                                ('Sparkling Water', 1.75, 'Carbonated mineral water (bottle).', 'DRINKS'),
                                                                ('Lemonade', 2.20, 'Freshly made lemonade with a hint of mint.', 'DRINKS'),
                                                                ('Tiramisu', 4.99, 'Traditional Italian dessert with mascarpone and coffee.', 'DESSERT'),
                                                                ('Chocolate Brownie', 3.75, 'Rich and fudgy brownie with chocolate chips.', 'DESSERT'),
                                                                ('Cheesecake', 4.50, 'Creamy vanilla cheesecake with a graham crust.', 'DESSERT'),
                                                                ('Fruit Salad', 3.99, 'Seasonal fruits served chilled.', 'DESSERT'),
                                                                ('Meat Lovers', 11.99, 'Pepperoni, sausage, bacon, and ham.', 'MAIN'),
                                                                ('Mushroom Melt', 9.25, 'Mozzarella and sautéed mushrooms on a garlic crust.', 'MAIN'),
                                                                ('Iced Tea', 2.10, 'Sweetened iced black tea with lemon.', 'DESSERT'),
                                                                ('Panna Cotta', 4.75, 'Silky Italian dessert topped with berry sauce.', 'DESSERT');