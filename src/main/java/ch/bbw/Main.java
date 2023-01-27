package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;
import org.bson.types.ObjectId;

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
            System.out.printf("%s: %d in %dms%n", statistics.getName(), statistics.getPoints(), statistics.getTime());
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
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d: %s%n", i + 1, animals.get(i).getAnimal());
        }
        int answer = 0;
        while (answer <= 0 || answer > 3) {
            System.out.println("Please enter a number between 1 to 3");
            answer = input.nextInt();
        }


        List<String> isList = Arrays.asList(animals.get(0).getAnimal(), animals.get(1).getAnimal(), animals.get(2).getAnimal());
        String answerID = animals.get(answer - 1).getAnimal();
        if (userDBService.checkAnswer(question, isList, answerID)) {
            System.out.println("This is correct");
            points++;
        } else {
            System.out.println("This is wrong");
        }
    }


    public static String askSpecies() {
        List<String> species = userDBService.getSpecies();
        System.out.println("---------------- Choose ----------------");
        for (int i = 0; i < species.size(); i++) {
            System.out.printf("%d %s%n", i + 1, species.get(i));
        }

        int topicIndex = 0;
        while (topicIndex <= 0 || topicIndex > 3) {
            System.out.println("Please enter a number between 1 to 3");
            topicIndex = input.nextInt();
        }
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
