package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AIAPP {


    public void start() throws URISyntaxException, IOException {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager=new ProductManager();
        chatGPTHelper chatGPTHelper = new chatGPTHelper();
        while(true){

            System.out.println("Choose an option:");
            System.out.println("1. Display Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Give me three ideas for breakfast ");
            System.out.println("5. Give me three ideas for Dinner");
            System.out.println("6. Give me recommendation ideas for breakfast");
            System.out.println("7. Exit from the application");

            int choice= Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1 -> productManager.getallProducts().forEach(System.out::println);
                case 2 -> {
                    System.out.println("What product do you want to add?");
                    String userProduct = scanner.nextLine();
                    productManager.addProduct(userProduct);
                }
                case 3 ->{
                    System.out.println("What product do you want to delete?");
                    String userProduct = scanner.nextLine();
                    productManager.deleteProduct(userProduct);
                }
                case 4 -> {
                    System.out.println("Ideas for breakfast from ChatGPT:");
                    String breakfastIdea = chatGPTHelper.getBreakfastIdea(productManager.getallProducts());
                    System.out.println(breakfastIdea);
                }
                case 5 ->{
                    System.out.println("Ideas for dinner from ChatGPT:");
                    String DinnerIdea = chatGPTHelper.getDinnerIdea(productManager.getallProducts());
                    System.out.println(DinnerIdea);
                }
                case 6 -> {
                    System.out.println("Recommendation for healthy shopping");
                    String healthyFood= chatGPTHelper.getHealthyProductsRecommendation(productManager.getallProducts());
                    System.out.println(healthyFood);
                }
                case 7 -> System.exit(0);
            }
        }
    }
}
