package com.kai.booter;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class CLIOptionCreator {

    private static final Option[] defaultOptions = {
            Option.builder("b")
                    .longOpt("base-site")
                    .argName("Base Site")
                    .desc("The booru to download the pictures from. If unspecified danbooru will be used.")
                    .optionalArg(true)
                    .hasArg()
                    .build(),

            Option.builder("d")
                    .longOpt("desired")
                    .argName("Desired Categories")
                    .desc("The desired categories, they are space separated.")
                    .required()
                    .hasArgs()
                    .build(),

            Option.builder("f")
                    .longOpt("forbidden")
                    .argName("Forbidden tags")
                    .required(false)
                    .desc("The tags that will be filtered out")
                    .hasArgs()
                    .build(),

            Option.builder()
                    .longOpt("sfw")
                    .argName("Safe for work")
                    .required(false)
                    .hasArg(false)
                    .desc("Block out all non-SWF material")
                    .build(),

            Option.builder()
                    .longOpt("no-comic")
                    .argName("No comics")
                    .required(false)
                    .hasArg(false)
                    .desc("Block out all comic/manga images.")
                    .build(),

            Option.builder()
                    .longOpt("min-res")
                    .argName("Minimum resolution")
                    .required(false)
                    .hasArgs()
                    .desc("The minimum resolution wanted for a post to be downloaded.")
                    .build(),

            Option.builder()
                    .longOpt("max-res")
                    .argName("Maximum resolution")
                    .required(false)
                    .hasArgs()
                    .desc("The maximum resolution wanted for a post to be downloaded.")
                    .build(),

            Option.builder("t")
                    .longOpt("target")
                    .argName("Target directory")
                    .required()
                    .desc("The location where the pictures will be saved.")
                    .hasArg()
                    .build(),

            Option.builder("h")
                    .longOpt("help")
                    .desc("This message.")
                    .required(false)
                    .build(),

            Option.builder("v")
                    .longOpt("version")
                    .desc("Version number.")
                    .required(false)
                    .build()
    };

    static Options getPageOptions() {

        Options cliOptions = new Options();

        for (Option option : defaultOptions )
            cliOptions.addOption(option);

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

        return cliOptions;
    }

    static Options getPictureOptions() {

        Options cliOptions = new Options();

        for (Option option : defaultOptions )
            cliOptions.addOption(option);

        Option numberOfPictures = Option.builder("n")
                .longOpt("picture-number")
                .argName("Number of pictures")
                .desc("The ammount of pictures to download")
                .hasArg()
                .required()
                .build();

        cliOptions.addOption(numberOfPictures);

        return cliOptions;
    }

}