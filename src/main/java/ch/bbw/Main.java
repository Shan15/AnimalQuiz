package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;
import ch.bbw.models.Topic;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static ch.bbw.DbServices.UserDBService.getQuestion;
import static java.lang.Integer.parseInt;

public class Main {
    private static Statistics currentStats = new Statistics();
    private static UserDBService userDBService = new UserDBService();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Animal> animals = userDBService.getAnimalsFromDB();
        // animals.stream().forEach(s -> System.out.println(s.getAnimal() + " " +
        // s.getMaxAge()));
        System.out.println("What's your name?");
        String name = generateUser(input.nextLine());

        List<Question> questions = getQuestion(askTopic());
        Collections.shuffle(questions);
        System.out.println(questions.get(0).getQuestion());
        System.out.println("ksldjf");
    }



    public static String askQuestion(String question, String[] answers, String string) {
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

    public static String askTopic() {
        List<Topic> topics = userDBService.getQuestionsFromDB();

        for (Topic topic : topics) {
            System.out.println("1" + topic.getTopic());
        }

        String topicIndex = input.nextLine();

        return topics.get(parseInt(topicIndex) - 1).getTopic();
    }

    public static String generateUser(String name) {
        currentStats.setName(name);
        userDBService.createUser(currentStats);
        return name;
    }
}
