package Exercise_43;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Chapter43 {
    record Order(String customer, String category, int total) {}

    public static List<String> premiumCustomers(List<Order> orders, int minimum) {
        if (orders == null || minimum < 0) {
            throw new IllegalArgumentException("orders and a non-negative minimum are required");
        }
        return orders.stream()
                .filter(order -> order.total() >= minimum)
                .map(Order::customer)
                .distinct()
                .sorted()
                .toList();
    }

    public static Map<String, Long> countByCategory(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders are required");
        }
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

    public static IntSummaryStatistics totals(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders are required");
        }
        return orders.stream()
                .mapToInt(Order::total)
                .summaryStatistics();
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Ada", "books", 4200),
                new Order("Grace", "tools", 1800),
                new Order("Ada", "tools", 5200),
                new Order("Linus", "books", 2600),
                new Order("Grace", "books", 6100)
        );

        System.out.println(premiumCustomers(orders, 4000));
        System.out.println(countByCategory(orders));
        IntSummaryStatistics stats = totals(orders);
        System.out.println(stats.getCount() + " orders, total " + stats.getSum()
                + ", average " + stats.getAverage());
    }
}
