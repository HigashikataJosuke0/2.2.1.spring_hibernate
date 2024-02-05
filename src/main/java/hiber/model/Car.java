package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "cars")
public class Car {
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @Id
    @GeneratedValue
    @Column(name = "cars_id")
    private Long id;
    @OneToOne(mappedBy="car")
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;

        return series == car.series && Objects.equals(model, car.model) && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series, id);
    }
}
