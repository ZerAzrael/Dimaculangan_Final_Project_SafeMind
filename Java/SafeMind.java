import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Question {
    protected String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public abstract void askQuestion();
    public abstract String getUserResponse(Scanner scanner);
}

class MultipleChoiceQuestion extends Question {
    private String[] options;

    public MultipleChoiceQuestion(String questionText, String[] options) {
        super(questionText);
        this.options = options;
    }

    @Override
    public void askQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    @Override
    public String getUserResponse(Scanner scanner) {
        String response = "";
        boolean validResponse = false;
        while (!validResponse) {
            System.out.print("Your answer (1-" + options.length + "): ");
            response = scanner.nextLine();
            try {
                int choice = Integer.parseInt(response);
                if (choice >= 1 && choice <= options.length) {
                    validResponse = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid option between 1 and " + options.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + options.length + ".");
            }
        }
        return response;
    }
}

class YesNoQuestion extends Question {

    public YesNoQuestion(String questionText) {
        super(questionText);
    }

    @Override
    public void askQuestion() {
        System.out.println(questionText + " (Yes/No)");
    }

    @Override
    public String getUserResponse(Scanner scanner) {
        String response = "";
        boolean validResponse = false;
        while (!validResponse) {
            System.out.print("Your answer (Yes/No): ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("Yes") || response.equalsIgnoreCase("No")) {
                validResponse = true;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
            }
        }
        return response;
    }
}

class RateQuestion extends Question {

    public RateQuestion(String questionText) {
        super(questionText);
    }

    @Override
    public void askQuestion() {
        System.out.println(questionText + " (Rate from 1 to 5)");
    }

    @Override
    public String getUserResponse(Scanner scanner) {
        String response = "";
        boolean validResponse = false;
        while (!validResponse) {
            System.out.print("Your rating (1-5): ");
            response = scanner.nextLine();
            try {
                int rating = Integer.parseInt(response);
                if (rating >= 1 && rating <= 5) {
                    validResponse = true;
                } else {
                    System.out.println("Invalid rating. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return response;
    }
}

class User {
    private String name;
    private int age;
    private int score;
    private ArrayList<String> responses;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.score = 0;
        this.responses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void addResponse(String response) {
        responses.add(response);
    }

    public ArrayList<String> getResponses() {
        return responses;
    }
}

public class SafeMind {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print(" SSSSSSSSS             AAA             FFFFFFFFFFFF         EEEEEEEEEEE          M       M           IIIIIIIII           N       N           DDDDDDDDD\n");
        System.out.print(" S                    A   A            F                    E                    M M   M M               I               N N     N           D        D\n");
        System.out.print(" SSSSSSSS            AAAAAAA           FFFFF                EEEEEEEE             M   M   M               I               N   N   N           D        D\n");
        System.out.print("        S           A       A          F                    E                    M       M               I               N     N N           D        D\n");
        System.out.print(" SSSSSSSS          A         A         F                    EEEEEEEEEEE          M       M           IIIIIIIII           N       N           DDDDDDDDD\n");
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print("\n");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter your age: ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                validAge = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a valid number.");
            }
        }

        User user = new User(name, age);
        System.out.println("\nHello, " + user.getName() + "! Welcome to SafeMind.\n");

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion("How are you feeling today?", new String[]{"Happy", "Sad", "Anxious", "Tired"}));
        questions.add(new YesNoQuestion("Have you been feeling stressed lately?"));
        questions.add(new RateQuestion("How would you rate your overall mental health in the past week?"));

        while (true) {
            for (Question question : questions) {
                question.askQuestion();
                String response = question.getUserResponse(scanner);
                user.addResponse(response);
                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("1")) {
                    user.addScore(1);
                }
            }

            saveUserData(user);

            System.out.println("\nThank you for taking the self-check, " + user.getName() + "!");
            if (user.getScore() <= 3) {
                System.out.println("You seem to be experiencing some stress. Consider talking to a mental health professional.");
            } else {
                System.out.println("You seem to be doing well. Keep it up and remember to prioritize self-care!");
            }

            showCheerUpMessage(user.getScore()); 

            System.out.println("\nWould you like to review your answers? (Yes/No)");
            String reviewChoice = scanner.nextLine().trim();
            while (!(reviewChoice.equalsIgnoreCase("yes") || reviewChoice.equalsIgnoreCase("no"))) {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
                reviewChoice = scanner.nextLine().trim();
            }
            
            if (reviewChoice.equalsIgnoreCase("yes")) {
                reviewUserResponses(user, questions);
            }
            
            System.out.println("\nWould you like to retake the test? (Yes/No)");
            String retakeChoice = scanner.nextLine().trim();
            while (!(retakeChoice.equalsIgnoreCase("yes") || retakeChoice.equalsIgnoreCase("no"))) {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
                retakeChoice = scanner.nextLine().trim();
            }
            
            if (!retakeChoice.equalsIgnoreCase("yes")) {
                break;
            }            

            user = new User(name, age);
        }

        printReceipt(user, questions);
        displayMentalHealthResources(user);
        scanner.close();
    }

    private static void saveUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt", true))) {
            writer.write("User: " + user.getName() + ", Age: " + user.getAge() + ", Score: " + user.getScore());
            writer.newLine();
            writer.write("Responses: " + user.getResponses());
            writer.newLine();
            writer.write("---------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while saving user data.");
        }
    }

    private static void reviewUserResponses(User user, ArrayList<Question> questions) {
        System.out.println("\nHere are your responses:");
        ArrayList<String> responses = user.getResponses();
        for (int i = 0; i < responses.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.questionText + " - " + responses.get(i));
        }
    }

    private static void printReceipt(User user, ArrayList<Question> questions) {
        clearScreen();

        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print(" SSSSSSSSS             AAA             FFFFFFFFFFFF         EEEEEEEEEEE          M       M           IIIIIIIII           N       N           DDDDDDDDD\n");
        System.out.print(" S                    A   A            F                    E                    M M   M M               I               N N     N           D        D\n");
        System.out.print(" SSSSSSSS            AAAAAAA           FFFFF                EEEEEEEE             M   M   M               I               N   N   N           D        D\n");
        System.out.print("        S           A       A          F                    E                    M       M               I               N     N N           D        D\n");
        System.out.print(" SSSSSSSS          A         A         F                    EEEEEEEEEEE          M       M           IIIIIIIII           N       N           DDDDDDDDD\n");
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print("\n");

        System.out.println("\n---- SafeMind Diagnosis Receipt ----");
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Total Score: " + user.getScore());
        System.out.println("\nYour Responses:");
        ArrayList<String> responses = user.getResponses();
        for (int i = 0; i < responses.size(); i++) {
            String response = responses.get(i);
            String label = getLabelForResponse(i + 1, response);
            System.out.println((i + 1) + ". " + label + " - " + response);
            System.out.println("\n");
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void showCheerUpMessage(int moodRating) {
        System.out.println("Mood rating is: " + moodRating); 
        if (moodRating <= 2) {
            System.out.println("---------------------------------");
            System.out.println("Cheer up! Things will get better.");
            System.out.println("---------------------------------");
        } else if (moodRating == 3) {
            System.out.println("---------------------------------");
            System.out.println("You're doing okay, hang in there!");
            System.out.println("---------------------------------");
        } else {
            System.out.println("---------------------");
            System.out.println("Good job! Keep it up!");
            System.out.println("---------------------");
        }
    }

    private static String getLabelForResponse(int questionNumber, String response) {
        switch (questionNumber) {
            case 1:
                return response.equalsIgnoreCase("Happy") ? "1 - Happy" : "2 - Not Happy";
            case 2:
                return response.equalsIgnoreCase("Yes") ? "3 - Yes" : "4 - No";
            case 3:
                return "5 - Rating: " + response;
            default:
                return "Unknown";
        }
    }

    private static void displayMentalHealthResources(User user) {
        showCheerUpMessage(user.getScore());
        System.out.println("\n--------------------------------------SafeMind--------------------------------------");
        System.out.println("\nHere are some mental health resources you can reach out to:");
        System.out.println("1. National Mental Health Helpline: 123-456-7890");
        System.out.println("2. Crisis Text Line: Text 'HELLO' to 741741");
        System.out.println("3. World Health Organization (WHO) Mental Health Resources: www.who.int/mental_health");
        System.out.println("\n------------------------------------------------------------------------------------");
    }
}
