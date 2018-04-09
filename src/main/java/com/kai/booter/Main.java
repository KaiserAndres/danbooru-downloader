package com.kai.booter;

import com.kai.danbooruManager.Configuration;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        Options cliOptions = getOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(cliOptions, args);
        } catch (ParseException e) {
            System.out.println("Make it load the GUI, seriously I'll do it some day. :(");
            return;
        }

        Configuration userCfg = createConfiguration(commandLine);
    }

    private static Configuration createConfiguration(CommandLine commandLine) {
        Configuration userCfg = new Configuration();

        for (String category : commandLine.getOptionValue('d').split(" ")) {
            userCfg.addDesiredCategory(category);
        }

        if (commandLine.hasOption('f')) {
            for (String category : commandLine.getOptionValue('f').split(" ")) {
                userCfg.addForbiddenCategory(category);
            }
        }

        userCfg.setImageTarget(commandLine.getOptionValue('t'));

        return userCfg;
    }

    private static Options getOptions() {
        Option site = Option.builder("b")
                .longOpt("base-site")
                .argName("Base Site")
                .desc("The booru to download the pictures from. If unspecified danbooru will be used.")
                .optionalArg(true)
                .hasArg()
                .build();

        Option pageStart = Option.builder("p")
                .longOpt("page-start")
                .argName("Page start")
                .desc("The page in which the downloading starts. If unspecified 1 will be used.")
                .required(false)
                .type(Integer.class)
                .build();

        Option numberOfPages = Option.builder("n")
                .longOpt("page-number")
                .argName("Number of pages")
                .desc("The amount of pages to be downloaded")
                .hasArg()
                .required()
                .build();

        Option desiredTags = Option.builder("d")
                .longOpt("desired")
                .argName("Desired Categories")
                .desc("The desired categories, they are space separated.")
                .required()
                .hasArgs()
                .build();

        Option forbiddenTags = Option.builder("f")
                .longOpt("forbidden")
                .argName("Forbidden tags")
                .required(false)
                .desc("The tags that will be filtered out")
                .hasArgs()
                .build();

        Option directory = Option.builder("t")
                .longOpt("target")
                .argName("Target directory")
                .required()
                .desc("The location where the pictures will be saved.")
                .hasArg()
                .build();

        Options cliOptions = new Options();
        cliOptions.addOption(site);
        cliOptions.addOption(pageStart);
        cliOptions.addOption(numberOfPages);
        cliOptions.addOption(desiredTags);
        cliOptions.addOption(forbiddenTags);
        cliOptions.addOption(directory);

        return cliOptions;
    }

}
