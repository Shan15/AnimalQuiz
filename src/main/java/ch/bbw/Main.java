package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Statistics currentStats = new Statistics();
    private static UserDBService userDBService = new UserDBService();

    public static void main(String[] args) {
        List<Animal> animals = userDBService.getAnimalsFromDB();
        List<Question> questions = userDBService.getQuestionsFromDB();
        // animals.stream().forEach(s -> System.out.println(s.getAnimal() + " " +
        // s.getMaxAge()));
        System.out.println("What's your name?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        currentStats.setName(name);
        userDBService.createUser(currentStats);
        in.close();
        String[] answers = { "2", "3", "4", "5" };
        String answer = askQuestion("Wie viele Beine hat eine Katze mit 3 Beinen?", answers);
        System.out.println(answer);
    }

    public static String askQuestion(String question, String[] answers) {
        System.out.println(question);
        // Arrays.stream(answers).forEach(System.out.println());
        System.out.println("a: " + answers[0]);
        System.out.println("b: " + answers[1]);
        System.out.println("c: " + answers[2]);
        System.out.println("d: " + answers[3]);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        scanner.close();
        return answer;
    }
}
