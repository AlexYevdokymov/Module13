package mainPackage;


import dto.Address;
import dto.Company;
import dto.Geo;
import dto.User;

import java.io.IOException;

import static tasks.Task1.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        User user = new User("Alex","Cold", "alex.cold@gmail.com",
                new Address("Khreshchatyk","Building 22","Kyiv","01001",
                        new Geo("50.449834","30.523032")),"+380-12-34-56-789", "alex-cold.com",
                new Company("Alex inc.","citius altius fotius","aggregate real-time technologies"));

        System.out.println(postUser(user));
        System.out.println(putUser(user, 8));
        System.out.println(deleteUser(4));
        System.out.println(getAllUsers());
        System.out.println(getUserById(3));
        System.out.println(getUsersByUsername("Delphine"));

    }

}
