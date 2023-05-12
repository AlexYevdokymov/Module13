package mainPackage;


import dto.*;
import tasks.Task1;
import tasks.Task2;
import tasks.Task3;

import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        User user = new User("Alex","Cold", "alex.cold@gmail.com",
                new Address("Khreshchatyk","Building 22","Kyiv","01001",
                        new Geo("50.449834","30.523032")),"+380-12-34-56-789", "alex-cold.com",
                new Company("Alex inc.","citius altius fotius","aggregate real-time technologies"));

        System.out.println(Task1.postUser(user));
        System.out.println(Task1.putUser(user, 8));
        System.out.println(Task1.deleteUser(4));
        System.out.println(Task1.getAllUsers());
        System.out.println(Task1.getUserById(3));
        System.out.println(Task1.getUsersByUsername("Delphine"));

        Task2.writeComments(6);

        System.out.println(Task3.getNotFinishedTasks(7));
    }

}
