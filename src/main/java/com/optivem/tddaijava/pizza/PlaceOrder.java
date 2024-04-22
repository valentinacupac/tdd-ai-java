package com.optivem.tddaijava.pizza;

public class PlaceOrder {
    private static final double US_DISCOUNT_RATE = 0.9;
    private OrderRepository orderRepository;

    public PlaceOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Pizza pizza, int quantity, String country) {
        validateQuantity(quantity);
        double price = calculatePrice(pizza, quantity);
        price = applyDiscount(price, country);
        return createAndSaveOrder(pizza, quantity, price);
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("You must order at least one pizza");
        }
    }

    private double calculatePrice(Pizza pizza, int quantity) {
        return pizza.getPrice() * quantity;
    }

    private double applyDiscount(double price, String country) {
        if ("US".equals(country)) {
            return price * US_DISCOUNT_RATE;
        }
        return price;
    }

    private Order createAndSaveOrder(Pizza pizza, int quantity, double price) {
        Order order = new Order(pizza, quantity, price);
        orderRepository.save(order);
        return order;
    }
}