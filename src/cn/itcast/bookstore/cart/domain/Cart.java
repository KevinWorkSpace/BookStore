package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Cart {

    HashMap<String, CartItem> map = new LinkedHashMap<>();

    public double getTotal() {
        BigDecimal total = new BigDecimal("0");
        for (CartItem item : map.values()) {
            BigDecimal d = new BigDecimal(item.getSubtotal() + "");
            total = total.add(d);
        }
        return total.doubleValue();
    }

    public void add(CartItem cartItem) {
        if (map.containsKey(cartItem.getBook().getBid())) {
            CartItem _item = map.get(cartItem.getBook().getBid());
            _item.setCount(_item.getCount() + cartItem.getCount());
        }
        else {
            map.put(cartItem.getBook().getBid(), cartItem);
        }
    }

    public void clear() {
        map.clear();
    }

    public void delete(String bid) {
        map.remove(bid);
    }

    public Collection<CartItem> getCartItems() {
        return map.values();
    }
}
