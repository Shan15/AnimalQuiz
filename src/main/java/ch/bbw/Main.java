package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;

import java.sql.Timestamp;
import java.util.*;

import java.util.stream.Collectors;

import static ch.bbw.DbServices.UserDBService.getQuestion;

public class Main {
    private static Statistics currentStats = new Statistics();
    private static final UserDBService userDBService = new UserDBService();
    private static Scanner input = new Scanner(System.in);
    private static int points = 0;

    public static void main(String[] args) {
        System.out.println("What's your name?");
        currentStats = generateUser(input.nextLine());

        List<Animal> animals = userDBService.getAnimalsFromDB(askSpecies());
        List<Question> questions = getQuestion();
        Collections.shuffle(questions);
        for (Question question : questions) {
            askQuestion(question, animals);
        }

        finishGame();

        List<Statistics> leaderboard = userDBService.getLeaderboard();
        for (Statistics statistics : leaderboard) {
            System.out.println(statistics.getName() + ": " + statistics.getPoints());
        }
    }

    private static void finishGame() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentStats.setPoints(points);
        currentStats.setTime(timestamp.getTime() - currentStats.getTime());
        currentStats = userDBService.updateUser(currentStats);
        System.out.println("Congrats! You got " + currentStats.getPoints() + " points in " + currentStats.getTime() + " ms");
    }

    public static void askQuestion(Question question, List<Animal> animals) {
        System.out.println(question.getQuestion());
        Collections.shuffle(animals);
        System.out.println("0: " + animals.get(0).getAnimal());
        System.out.println("1: " + animals.get(1).getAnimal());
        System.out.println("2: " + animals.get(2).getAnimal());
        System.out.println("Welches Tier trifft zu? ");
        int answer = input.nextInt();
        List<Integer> propertyList = Arrays.asList(animals.get(0).getProperty(question.getProperty()), animals.get(1).getProperty(question.getProperty()), animals.get(2).getProperty(question.getProperty()));
        if (question.getCriteria().equals("min")) {
            propertyList = propertyList.stream().sorted().collect(Collectors.toList());
        } else {
            propertyList = propertyList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        if (animals.get(answer).getProperty(question.getProperty()) == propertyList.get(0)) {
            points++;
        }
    }


    public static String askSpecies() {
        List<String> species = userDBService.getSpecies();

        for (int i = 0; i < species.size(); i++) {
            System.out.printf("%d %s%n", i + 1, species.get(i));
        }

        int topicIndex = input.nextInt();

        return species.get(topicIndex - 1);
    }

    public static Statistics generateUser(String name) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentStats.setName(name);
        currentStats.setTime(timestamp.getTime());
        return userDBService.createUser(currentStats);
    }
}
