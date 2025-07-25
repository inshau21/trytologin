package mygroup.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ManagementApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ManagementApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
