package Blatt06L.KevinSolution;

import Templates.InputValidation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Anwendung {

    private static final String PROPER_USAGE_MESSAGE = "Proper Usage: [[Interval or Lateness], FilePath]";

    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {
        ArrayList<Interval> resultList = new ArrayList<>();
        resultList.add(intervals.get(0));
        int j = 0;

        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).getStart() >= intervals.get(j).getEnd()) {
                resultList.add(intervals.get(i));
                j = i;
            }
        }

        return resultList;
    }

    // Returns Startzeiten der Jobs
    public static int[] latenessScheduling(ArrayList<Job> jobList) {
        int[] result = new int[jobList.size()];
        int jobStartTime = 0;

        int jobListSize = jobList.size();
        for (int i = 0; i < jobListSize; ++i) {
            result[i] = jobStartTime;
            jobStartTime += jobList.get(i).getDauer();
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] validStrs = new String[][] {
                {"Interval", "Lateness"},
                null
        };

        InputValidation validator = new InputValidation(PROPER_USAGE_MESSAGE);
        validator.setAcceptedStrings(validStrs);
        validator.setPattern(String.class, String.class);

        if (!validator.validate(args))
            return;

        String path = System.getProperty("user.dir") + "/src/Blatt06L/Vorlagen/" + args[1];
        System.out.println("Bearbeite Datei \"" + args[1] + "\".\n");

        if (args[0].equals("Interval")) {
            ArrayList<Interval> intervalList = getClassArrayList(path, Interval.class);
            if (intervalList == null)
                return;

            messagesWithList(intervalList, "Lines");

            intervalList = intervalList.stream()
                                       .sorted(Comparator.comparingInt(Interval::getEnd))
                                       .collect(Collectors.toCollection(ArrayList::new));

            messagesWithList(intervalList, "sorted");

            intervalList = intervalScheduling(intervalList);
            System.out.println("Berechnetes Intervallscheduling:\n" + intervalList);
        }
        else { // Lateness
            ArrayList<Job> jobList = getClassArrayList(path, Job.class);
            if (jobList == null)
                return;

            messagesWithList(jobList, "Lines");

            ArrayList<Job> sortedJobs = jobList.stream()
                                               .sorted(Comparator.comparingInt(Job::getDeadline))
                                               .collect(Collectors.toCollection(ArrayList::new));

            messagesWithList(sortedJobs, "sorted");
            System.out.println("Berechnetes Latenessscheduling:");

            int[] scheduled = latenessScheduling(sortedJobs);
            System.out.println("[" +
              Arrays.stream(scheduled)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", "))
            + "]\n");

            int maxDelay = IntStream.range(0, scheduled.length)
                                    .map(e -> scheduled[e] + sortedJobs.get(e).getDauer() - sortedJobs.get(e).getDeadline())
                                    .filter(e -> e >= 0)
                                    .max().orElse(0);

            System.out.println("Berechnete maximale Verspätung: " + maxDelay);
        }
    }

    private static <T> void messagesWithList(ArrayList<T> list, String cmd) {
        switch (cmd.toLowerCase()) {
            case "lines":
                System.out.println("Es wurden " + list.size() + " Zeilen mit folgendem Inhalt gelesen:\n" + list + "\n");
                break;
            case "sorted":
                System.out.println("Sortiert:\n" + list + "\n");
                break;
            default:
                System.out.println("messagesWithList: Wrong command provided");
        }

    }

    private static <T> ArrayList<T> getClassArrayList(String path, Class<T> clazz) {
        try {
            return Files.lines(Paths.get(path))
                        .map(e -> e.split(","))
                        .map(e -> new int[]{Integer.parseInt(e[0]), Integer.parseInt(e[1])})
                        .filter(e -> e[0] >= 0 && e[1] >= 0)
                        .map(e -> returnInstance(e[0], e[1], clazz))
                        .collect(Collectors.toCollection(ArrayList::new));

        }
        catch (FileNotFoundException e) {
            System.out.println("Your given path doesnt belong to a file");
        }
        catch (IOException e) {
            System.out.println("Your specified path doesnt't belong to a file. Program aborting... " + PROPER_USAGE_MESSAGE);
        }
        catch (NumberFormatException e) {
            System.out.println("Your specified file contains strings at unexpected positions. Program aborting... " + PROPER_USAGE_MESSAGE);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your given file contains errors. Please follow this format: Integer,Integer");
        }
        return null;
    }

    private static <T> T returnInstance(int i, int j, Class<T> clazz) {
        try {
            return clazz.getConstructor(int.class, int.class).newInstance(i, j);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
