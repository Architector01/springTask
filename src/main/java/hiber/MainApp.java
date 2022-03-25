package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Yura", "Lastname1", "user1@mail.ru", new Car("BMW", 1)));
      userService.add(new User("Misha", "Lastname2", "user2@mail.ru", new Car ("Mercedes",124)));
      userService.add(new User("Oleg", "Lastname3", "user3@mail.ru", new Car("Audi", 7)));
      userService.add(new User("Alina", "Lastname4", "user4@mail.ru", new Car("Lada",6)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user1 = userService.getUserByCar("BMW", 1);
      User user2 = userService.getUserByCar("Mercedes",124);
      User user3 = userService.getUserByCar("Audi", 7);
      User user4 = userService.getUserByCar("Lada",6);

      System.out.println("Получаем пользователя с автомобилем BMW - " + user1.getFirstName());
      System.out.println("Получаем пользователя с автомобилем Mercedes - " + user2.getFirstName());
      System.out.println("Получаем пользователя с автомобилем Audi - " + user3.getFirstName());
      System.out.println("Получаем пользователя с автомобилем Lada - " + user4.getFirstName());

      context.close();
   }
}
