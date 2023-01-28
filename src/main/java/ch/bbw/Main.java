package ch.bbw;

import ch.bbw.DbServices.DBService;
import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Statistics;

import java.sql.Timestamp;
import java.util.*;

import static ch.bbw.DbServices.DBService.getQuestion;

public class Main {
    private static final DBService DB_SERVICE = new DBService();
    private static Statistics currentStats = new Statistics();
    private static Scanner input = new Scanner(System.in);
    private static int points = 0;

    public static void main(String[] args) {

        currentStats = generateUser();

        List<Animal> animals = DB_SERVICE.getAnimalsFromDB(askSpecies());
        List<Question> questions = getQuestion();
        Collections.shuffle(questions);
        for (Question question : questions) {
            System.out.println("--------------------------------------------");
            askQuestion(question, animals);
        }

        finishGame();

        List<Statistics> leaderboard = DB_SERVICE.getLeaderboard();
        System.out.println("--------------- Leaderboard ----------------");
        for (Statistics statistics : leaderboard) {
            System.out.printf("%s: %d in %dms%n", statistics.getName(), statistics.getPoints(), statistics.getTime());
        }
    }

    public static void finishGame() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentStats.setPoints(points);
        currentStats.setTime(timestamp.getTime() - currentStats.getTime());
        currentStats = DB_SERVICE.updateUser(currentStats);
        System.out.println("--------------------------------------------");
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
        List<String> animalList = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            animalList.add(animals.get(i).getAnimal());
        }
        String answerID = animals.get(answer - 1).getAnimal();
        if (DB_SERVICE.checkAnswer(question, animalList, answerID)) {
            System.out.println("This is correct");
            points++;
        } else {
            System.out.println("This is wrong");
        }
    }


    public static String askSpecies() {
        List<String> species = DB_SERVICE.getSpecies();
        System.out.println("---------------- Choose ----------------");
        for (int i = 0; i < species.size(); i++) {
            System.out.printf("%d %s%n", i + 1, species.get(i));
        }

        int topicIndex = 0;
        while (topicIndex <= 0 || topicIndex > species.size()) {
            System.out.println(String.format("Please enter a number between 1 to %d", species.size()));
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
        return DB_SERVICE.createUser(currentStats);
    }
}
