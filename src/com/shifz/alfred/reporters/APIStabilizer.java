package com.shifz.alfred.reporters;


import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shifar Shifz on 8/22/2015.
 */
public class APIStabilizer {

    private static final String API_ROUTES_FINDER_REGEXP = "\\$app->(get|post|put|delete)\\('([\\w\\/:]+)'";
    private static final String SUFFIX_STATISTICS = " - STATISTICS" + System.getProperty("line.separator");
    private static final String METHOD_STATISTICS = "METHOD STATISTICS" + System.getProperty("line.separator");
    private static final String DUAL_LINES = System.getProperty("line.separator") + System.getProperty("line.separator");

    public static void start() {

        try {
            final List<String> methodList = new ArrayList<>();
            final Scanner scanner = new Scanner(System.in);
            File inputFile = null;
            String filePath = null;
            do {
                System.out.println("Enter your PHP Slim index file path: ");
                filePath = scanner.nextLine();
                inputFile = new File(filePath);
            } while (!inputFile.exists());


            final BufferedReader br = new BufferedReader(new FileReader(inputFile));
            final StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }

            br.close();

            final Pattern pattern = Pattern.compile(API_ROUTES_FINDER_REGEXP);
            final Matcher matcher = pattern.matcher(sb.toString());

            //Cooking some simple heading=
            final String heading = filePath + SUFFIX_STATISTICS;
            final String headerBottomHash = getBottomHashDec(heading.length());

            final StringBuilder resultBuilder = new StringBuilder();

            //Appending heading first
            resultBuilder.append(heading);
            //Then Hash decoration
            resultBuilder.append(headerBottomHash).append(DUAL_LINES);

            int count = 1;

            //Then content
            while (matcher.find()) {

                final String methodName = matcher.group(1).toUpperCase();
                final String route = matcher.group(2);

                methodList.add(methodName);

                //Appending result node
                resultBuilder
                        .append(count)
                        .append(") ")
                                //Appending HTTP METHOD
                        .append(methodName)
                        .append(" - ")
                                //Then the route
                        .append(route)
                        .append(System.getProperty("line.separator"));
                count++;
            }

            //Heading - Method statistics
            resultBuilder
                    .append(DUAL_LINES)
                    .append(METHOD_STATISTICS)
                    .append(getBottomHashDec(METHOD_STATISTICS.length()))
                    .append(DUAL_LINES);

            //Calculating method usage
            final Set<String> uniqueMethods = new HashSet<>(methodList);
            final Map<String, Integer> methodsMap = new HashMap<>();
            for (final String method : uniqueMethods) {
                final int methodCount = Collections.frequency(methodList, method);
                methodsMap.put(method, methodCount);
            }

            final int totalMethodsUsed = methodList.size();

            for (final Map.Entry<String, Integer> entry : methodsMap.entrySet()) {
                resultBuilder.append(entry.getKey()).append(" : ").append(entry.getValue());
                final int perc = Math.round((entry.getValue() * 100f) / totalMethodsUsed);
                resultBuilder.append(" - ").append(perc).append("%").append(System.getProperty("line.separator"));
            }

            //finally building report
            int lastIndex = filePath.lastIndexOf('/');
            if (lastIndex == -1) {
                lastIndex = 0;
            }
            final String fileName = filePath.substring(lastIndex, filePath.length());
            final String date = Calendar.getInstance().getTime().toString().replace(":","-");
            System.out.println(date);
            final File reportFile = new File(fileName + "_" + date + "_report.txt");
            if (reportFile.createNewFile()) {
                final BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile));
                bw.write(resultBuilder.toString());
                bw.flush();
                bw.close();
                System.out.println("REPORT GENERATED!");
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(reportFile);
                } else {
                    Runtime.getRuntime().exec("notepad " + reportFile);
                }
            } else {
                System.out.println("Report generation failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getBottomHashDec(final int length) {
        final String headerBottomDecorationFormat = "%" + (length - 2) + "s";
        return String.format(headerBottomDecorationFormat, "#").replace(' ', '#');
    }

}
