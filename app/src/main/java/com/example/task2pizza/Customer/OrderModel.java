package com.example.task2pizza.Customer;

public class OrderModel {
    //confirm order
    //name
    //qunty
    //discout
    //date
    //grandtotal
    private  String PizzaName,PizzaQunty,PizzaOrigin,PizzaDiscount,DiscountPrice,DateofBuy,GrandTotal,CustomerEmail,OrderId,PizzaImage;

    public OrderModel() {
    }

    public OrderModel(String pizzaName, String pizzaQunty, String pizzaOrigin, String pizzaDiscount, String discountPrice, String dateofBuy, String grandTotal, String customerEmail, String orderId, String pizzaImage) {
        PizzaName = pizzaName;
        PizzaQunty = pizzaQunty;
        PizzaOrigin = pizzaOrigin;
        PizzaDiscount = pizzaDiscount;
        DiscountPrice = discountPrice;
        DateofBuy = dateofBuy;
        GrandTotal = grandTotal;
        CustomerEmail = customerEmail;
        OrderId = orderId;
        PizzaImage = pizzaImage;
    }

    public String getPizzaName() {
        return PizzaName;
    }

    public void setPizzaName(String pizzaName) {
        PizzaName = pizzaName;
    }

    public String getPizzaQunty() {
        return PizzaQunty;
    }

    public void setPizzaQunty(String pizzaQunty) {
        PizzaQunty = pizzaQunty;
    }

    public String getPizzaOrigin() {
        return PizzaOrigin;
    }

    public void setPizzaOrigin(String pizzaOrigin) {
        PizzaOrigin = pizzaOrigin;
    }

    public String getPizzaDiscount() {
        return PizzaDiscount;
    }

    public void setPizzaDiscount(String pizzaDiscount) {
        PizzaDiscount = pizzaDiscount;
    }

    public String getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        DiscountPrice = discountPrice;
    }

    public String getDateofBuy() {
        return DateofBuy;
    }

    public void setDateofBuy(String dateofBuy) {
        DateofBuy = dateofBuy;
    }

    public String getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        GrandTotal = grandTotal;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getPizzaImage() {
        return PizzaImage;
    }

    public void setPizzaImage(String pizzaImage) {
        PizzaImage = pizzaImage;
    }
}
