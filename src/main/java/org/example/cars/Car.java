package org.example.cars;

public class Car implements Comparable<Car> {

    private String brand;

    private String model;

    private int power;

    public Car(String brand, String model, int power) {
        this.brand = brand;
        this.model = model;
        this.power = power;
    }

    @Override
    public int compareTo(Car o) {
        if (!this.brand.equalsIgnoreCase(o.brand)) {
            return this.brand.compareTo(o.brand);
        } else if (!this.model.equalsIgnoreCase(o.model)) {
            return this.model.compareTo(o.model);
        } else {
            return Integer.compare(this.power, o.power);
        }
    }

    @Override
    public String toString() {
        return brand + " " + model + ", " + power + " hp";
    }
}
