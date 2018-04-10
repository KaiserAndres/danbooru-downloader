package com.kai.booter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class CLIOptionCreator {

    static Options getOptions() {

        Options cliOptions = new Options();

        Option site = Option.builder("b")
                .longOpt("base-site")
                .argName("Base Site")
                .desc("The booru to download the pictures from. If unspecified danbooru will be used.")
                .optionalArg(true)
                .hasArg()
                .build();

        cliOptions.addOption(site);

        Option pageStart = Option.builder("p")
                .longOpt("page-start")
                .argName("Page start")
                .desc("The page in which the downloading starts. If unspecified 1 will be used.")
                .required(false)
                .hasArg()
                .build();

        cliOptions.addOption(pageStart);

        Option numberOfPages = Option.builder("n")
                .longOpt("page-number")
                .argName("Number of pages")
                .desc("The amount of pages to be downloaded")
                .hasArg()
                .required()
                .build();

        cliOptions.addOption(numberOfPages);

        Option desiredTags = Option.builder("d")
                .longOpt("desired")
                .argName("Desired Categories")
                .desc("The desired categories, they are space separated.")
                .required()
                .hasArgs()
                .build();

        cliOptions.addOption(desiredTags);

        Option forbiddenTags = Option.builder("f")
                .longOpt("forbidden")
                .argName("Forbidden tags")
                .required(false)
                .desc("The tags that will be filtered out")
                .hasArgs()
                .build();

        cliOptions.addOption(forbiddenTags);

        Option sfw = Option.builder()
                .longOpt("sfw")
                .argName("Safe for work")
                .required(false)
                .hasArg(false)
                .desc("Block out all non-SWF material")
                .build();

        cliOptions.addOption(sfw);

        Option noComic = Option.builder()
                .longOpt("no-comic")
                .argName("No comics")
                .required(false)
                .hasArg(false)
                .desc("Block out all comic/manga images.")
                .build();

        cliOptions.addOption(noComic);

        Option directory = Option.builder("t")
                .longOpt("target")
                .argName("Target directory")
                .required()
                .desc("The location where the pictures will be saved.")
                .hasArg()
                .build();

        cliOptions.addOption(directory);

        Option help = Option.builder("h")
                .longOpt("help")
                .desc("This message.")
                .required(false)
                .build();

        cliOptions.addOption(help);

        Option version = Option.builder("v")
                .longOpt("version")
                .desc("Version number.")
                .required(false)
                .build();

        cliOptions.addOption(version);

        return cliOptions;
    }

}