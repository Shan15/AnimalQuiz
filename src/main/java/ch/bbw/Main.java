package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;
import ch.bbw.models.Topic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ch.bbw.DbServices.UserDBService.getQuestion;
import static java.lang.Integer.parseInt;

public class Main {
    private static Statistics currentStats = new Statistics();
    private static UserDBService userDBService = new UserDBService();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Animal> animals = userDBService.getAnimalsFromDB();
        List<Topic> topics = userDBService.getQuestionsFromDB();
        // animals.stream().forEach(s -> System.out.println(s.getAnimal() + " " +
        // s.getMaxAge()));
        System.out.println("What's your name?");
        String name = generateUser(input.nextLine());
        // String[] answers = { "2", "3", "4", "5" };
        // System.out.println("Wie viele Beine hat eine Katze mit 3 Beinen?");
        // String answer = askQuestion("Wie viele Beine hat eine Katze mit 3 Beinen?", answers, input.nextLine());



        List<Question> questions = getQuestion(askTopic());
        Collections.shuffle(questions);
        System.out.println();
        System.out.println("ksldjf");
        boolean correct = askQuestion2(questions.get(0), animals);
        System.out.println(correct);

    }

    public static boolean askQuestion2(Question question, List<Animal> animals) {
        System.out.println(question.getQuestion());
        Collections.shuffle(animals);
        System.out.println("0: " + animals.get(0).getAnimal());
        System.out.println("1: " + animals.get(1).getAnimal());
        System.out.println("2: " + animals.get(2).getAnimal());
        System.out.println("Welches Tier trifft zu? ");
        String answer = input.nextLine();
        List<Integer> propertyList = Arrays.asList(animals.get(0).getProperty(question.getProperty()),
                animals.get(1).getProperty(question.getProperty()), animals.get(2).getProperty(question.getProperty()));
        if (question.getCriteria().equals("min")) {
            propertyList = propertyList.stream().sorted().collect(Collectors.toList());
        } else{
            propertyList = propertyList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        if (animals.get(Integer.parseInt(answer)).getProperty(question.getProperty()) == propertyList.get(0)) {
            return true;
        }
        return false;
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
