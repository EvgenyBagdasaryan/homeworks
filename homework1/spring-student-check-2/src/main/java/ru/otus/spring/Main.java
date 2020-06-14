package ru.otus.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.domain.Check;
import ru.otus.spring.service.StudentCheckService;

import java.io.IOException;
import java.util.Scanner;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

   @Bean
   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
       return new PropertySourcesPlaceholderConfigurer();
   }

   @Value("${successfullyNumberValidChecks}")
   int successfullyNumberValidChecks;

   public static void main(String[] args){

       AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(Main.class);

       StudentCheckService service = context.getBean(StudentCheckService.class);

       Check check;
       int numberValidChecks = 0;

       Scanner sc = new Scanner(System.in);;
       do{
           check = service.getNextCheck();
           if(check != null){
               System.out.println(check.getQuestion());
               String phrase = sc.nextLine();
               if(phrase.trim().equals(check.getAnswer().trim())){
                   System.out.println("you are right ) congratulations!");
                   numberValidChecks++;
               }else{
                   System.out.println("you are wrong ( right answer is: " + check.getAnswer());
               }
           }
       }while (check != null);

       System.out.println("Your number of valid checks is: " + numberValidChecks);

       if(numberValidChecks > 2){
           System.out.println("You have been tested successfully!");
       }
       else{
           System.out.println("You have been tested unsuccessfully!");
       }

       context.close();
   }
}
