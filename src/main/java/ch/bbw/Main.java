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
    private static final UserDBService userDBService = new UserDBService();
    private static Statistics currentStats = new Statistics();
    private static Scanner input = new Scanner(System.in);
    private static int points = 0;

    public static void main(String[] args) {

        currentStats = generateUser();

        List<Animal> animals = userDBService.getAnimalsFromDB(askSpecies());
        List<Question> questions = getQuestion();
        Collections.shuffle(questions);
        for (Question question : questions) {
            askQuestion(question, animals);
        }

        finishGame();

        List<Statistics> leaderboard = userDBService.getLeaderboard();
        System.out.println("--------------- Leaderboard ----------------");
        for (Statistics statistics : leaderboard) {
            System.out.println(statistics.getName() + ": " + statistics.getPoints());
        }
    }

    private static void finishGame() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentStats.setPoints(points);
        currentStats.setTime(timestamp.getTime() - currentStats.getTime());
        currentStats = userDBService.updateUser(currentStats);
        System.out.println("                   FINISH                   ");
        System.out.println("--------------------------------------------");
        System.out.println("Congrats! You got " + currentStats.getPoints() + " points in " + currentStats.getTime() + " ms");
    }

    public static void askQuestion(Question question, List<Animal> animals) {
        System.out.println(question.getQuestion());
        Collections.shuffle(animals);
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d: %s%n", i + 1, animals.get(i).getAnimal());
        }
        int answer = input.nextInt();
        System.out.println("--------------------------------------------");

        List<Integer> propertyList = Arrays.asList(animals.get(0).getProperty(question.getProperty()), animals.get(1).getProperty(question.getProperty()), animals.get(2).getProperty(question.getProperty()));
        if (question.getCriteria().equals("min")) {
            propertyList = propertyList.stream().sorted().collect(Collectors.toList());
        } else {
            propertyList = propertyList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        if (animals.get(answer - 1).getProperty(question.getProperty()) == propertyList.get(0)) {
            points++;
        }
    }


    public static String askSpecies() {
        List<String> species = userDBService.getSpecies();
        System.out.println("---------------- Choose ----------------");
        for (int i = 0; i < species.size(); i++) {
            System.out.printf("%d %s%n", i + 1, species.get(i));
        }

        int topicIndex = input.nextInt();

        return species.get(topicIndex - 1);
    }

    public static Statistics generateUser() {
        System.out.println("----------------- Name -----------------");
        System.out.println("What's your name?");
        String name = input.nextLine();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentStats.setName(name);
        currentStats.setTime(timestamp.getTime());
        return userDBService.createUser(currentStats);
    }
}
