package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);



        User userTest1 = new User("userTest1", "lasrUserTest1", "userTest1@mail.com");
        User userTest2 = new User("userTest2", "lasrUserTest2", "userTest2@mail.com");
        Car car1 = new Car("carTest1", 1);
        Car car2 = new Car("carTest2", 2);

        userTest1.setCar(car1);
        userTest2.setCar(car2);

        userService.add(userTest1);
        userService.add(userTest2);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        userService.getUserByModelAndSeries("carTest1",1);

        context.close();
    }
}
