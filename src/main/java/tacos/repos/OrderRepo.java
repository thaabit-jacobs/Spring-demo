package tacos.repos;

import tacos.models.Order;

public interface OrderRepo {
    Order save(Order order);
}
