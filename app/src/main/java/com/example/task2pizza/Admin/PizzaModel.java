package com.example.task2pizza.Admin;

public class PizzaModel {
    private  String Pizzaname,Pizzaprice,PizzaId,PizzaImg;

    public PizzaModel(String pizzaname, String pizzaprice, String pizzaId, String pizzaImg) {
        Pizzaname = pizzaname;
        Pizzaprice = pizzaprice;
        PizzaId = pizzaId;
        PizzaImg = pizzaImg;
    }

    public PizzaModel() {
    }

    public String getPizzaname() {
        return Pizzaname;
    }

    public void setPizzaname(String pizzaname) {
        Pizzaname = pizzaname;
    }

    public String getPizzaprice() {
        return Pizzaprice;
    }

    public void setPizzaprice(String pizzaprice) {
        Pizzaprice = pizzaprice;
    }

    public String getPizzaId() {
        return PizzaId;
    }

    public void setPizzaId(String pizzaId) {
        PizzaId = pizzaId;
    }

    public String getPizzaImg() {
        return PizzaImg;
    }

    public void setPizzaImg(String pizzaImg) {
        PizzaImg = pizzaImg;
    }

    @Override
    public String toString() {
        return "PizzaModel{" +
                "Pizzaname='" + Pizzaname + '\'' +
                ", Pizzaprice='" + Pizzaprice + '\'' +
                ", PizzaId='" + PizzaId + '\'' +
                ", PizzaImg='" + PizzaImg + '\'' +
                '}';
    }
}
