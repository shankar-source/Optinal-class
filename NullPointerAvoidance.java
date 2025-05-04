
package NullPointerExceptions;

import java.util.Optional;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class NullPointerAvoidance {

    // Method to demonstrate null checks
    public void checkNull() {
        String message = null;

        if (message != null) {
            System.out.println("Message length: " + message.length());
        } else {
            System.out.println("Message is null");
        }
    }

    // Method to demonstrate Optional
    public void useOptional() {
        Optional<String> message = Optional.ofNullable(null);

        String defaultMessage = message.orElse("Default Message");
        System.out.println("Message: " + defaultMessage);

        message.ifPresent(msg -> System.out.println("Message length: " + msg.length()));

        Optional<Integer> length = message.map(String::length);
        System.out.println("Message length (using map): " + length.orElse(0));
    }

    // Method to demonstrate Objects.requireNonNull
    public void useRequireNonNull(String name) {
        try {
            String safeName = Objects.requireNonNull(name, "Name cannot be null");
            System.out.println("Name: " + safeName);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }
    }

    // Method to handle null in Arrays
    public void handleNullArray() {
        int[] numbers = new int[5]; // Initialized with default values
        System.out.println("First element: " + numbers[0]);
    }

    // Method for nested property access
    public void nestedPropertyAccess(User user) {
        if (user != null && user.getName() != null) {
            System.out.println("User's name: " + user.getName());
        } else {
            System.out.println("User or name is null");
        }
    }

    // Using Optional for nested properties
    public void optionalNestedPropertyAccess(Optional<User> user) {
        String name = user.map(User::getName).orElse("Default User");
        System.out.println("User's name: " + name);
    }

    // Using Optional with custom objects
    public void processUser(Optional<User> user) {
        user.ifPresentOrElse(
            u -> System.out.println("Processing user: " + u.getName()),
            () -> System.out.println("No user provided")
        );
    }

    // Demonstrating safe collection handling
    public void handleNullCollection(List<String> items) {
        if (items != null) {
            items.forEach(item -> System.out.println("Item: " + item));
        } else {
            System.out.println("Collection is null");
        }
    }

    // Using Optional for collection handling
    public void safeOptionalCollection(Optional<List<String>> items) {
        items.orElseGet(ArrayList::new).forEach(item -> System.out.println("Item: " + item));
    }

    // Method Chaining with Optional
    public void methodChainingWithOptional(String input) {
        Optional<String> result = Optional.ofNullable(input)
            .filter(str -> str.length() > 5)
            .map(String::toUpperCase)
            .map(str -> "Processed: " + str);

        System.out.println(result.orElse("Input was invalid or null"));
    }

    // Demonstrate null-safe equality check
    public void safeEqualityCheck(String str1, String str2) {
        boolean isEqual = Objects.equals(str1, str2);
        System.out.println("Are the strings equal? " + isEqual);
    }

    public static void main(String[] args) {
        NullPointerAvoidance example = new NullPointerAvoidance();

        System.out.println("1. Null Check Example:");
        example.checkNull();

        System.out.println("\n2. Optional Example:");
        example.useOptional();

        System.out.println("\n3. Objects.requireNonNull Example:");
        example.useRequireNonNull(null); // Demonstrates exception handling

        System.out.println("\n4. Handle Null Array Example:");
        example.handleNullArray();

        System.out.println("\n5. Nested Property Access Example:");
        User user = new User("Shankar");
        example.nestedPropertyAccess(user);
        example.nestedPropertyAccess(null); // Null scenario

        System.out.println("\n6. Optional Nested Property Access Example:");
        Optional<User> optionalUser = Optional.ofNullable(new User("Shankar Kumar"));
        example.optionalNestedPropertyAccess(optionalUser);
        example.optionalNestedPropertyAccess(Optional.empty()); // No user scenario

        System.out.println("\n7. Process User Example:");
        example.processUser(optionalUser);
        example.processUser(Optional.empty());

        System.out.println("\n8. Safe Collection Handling:");
        List<String> items = null;
        example.handleNullCollection(items); // Null collection
        example.handleNullCollection(List.of("Apple", "Banana", "Cherry"));

        System.out.println("\n9. Optional Collection Handling:");
        Optional<List<String>> optionalItems = Optional.ofNullable(null);
        example.safeOptionalCollection(optionalItems); // Handles null collection safely

        System.out.println("\n10. Method Chaining with Optional:");
        example.methodChainingWithOptional("HelloWorld");
        example.methodChainingWithOptional("Hi");

        System.out.println("\n11. Null-safe Equality Check:");
        example.safeEqualityCheck("Shankar", "Shankar");
        example.safeEqualityCheck("Shankar", null);
        example.safeEqualityCheck(null, null);
    }
}

// User class to demonstrate nested property access
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}