package com.kai.booter;

import com.kai.danbooruManager.Configuration;
import com.kai.danbooruManager.DanbooruPage;
import com.kai.danbooruManager.DownloadManager;
import com.kai.danbooruManager.Url;
import org.apache.commons.cli.*;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        String programName = "Danbooru downloader";
        String versionNumber = "1.1";


        Options cliOptions = CLIOptionCreator.getOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(cliOptions, args);
        } catch (ParseException e) {
            displayHelp(cliOptions);
            return;
        }

        if (commandLine.hasOption("v")) {
            System.out.println(programName + " v" + versionNumber);
            return;
        }

        if (commandLine.hasOption("h")) {
            displayHelp(cliOptions);
            return;
        }

        Configuration userCfg = createConfiguration(commandLine);
        Url workerUrl = createWorkerUrl(commandLine, userCfg);

        int pageCount = Integer.parseInt(commandLine.getOptionValue('n'));

        DownloadManager dm = new DownloadManager(userCfg);

        downloadPictures(workerUrl, pageCount, dm);

    }

    private static void downloadPictures(Url workerUrl, int pageAmount, DownloadManager downloadManager) {

        for (int p = 0; p< pageAmount; p++) {
            System.out.println("Downloading page Nº" + workerUrl.getPageNumber());
            DanbooruPage page = new DanbooruPage(workerUrl);
            page.downloadPage();
            page.populatePosts();

            downloadManager.addPosts(page.getPosts());
            int totalPosts = downloadManager.getPostCount();

            while (!downloadManager.isEmpty()) {
                downloadManager.download();
                System.out.println(p+1 + "/" + totalPosts);
            }

            workerUrl.incrementPageNumber();
        }

    }

    private static void displayHelp(Options cliOptions) {
        String header = "I'll it load the GUI, seriously I'll do it some day. :(";
        HelpFormatter hp = new HelpFormatter();
        hp.printHelp("Danbooru-downloader", header, cliOptions, "", true);
    }

    private static Url createWorkerUrl(CommandLine commandLine, Configuration userCfg) {
        Url workerUrl = new Url();

        if (commandLine.hasOption('b'))
            workerUrl.setBaseUrl(commandLine.getOptionValue('b'));
        else
            workerUrl.setBaseUrl("https://hijiribe.donmai.us");

        if (commandLine.hasOption('p'))
            workerUrl.setPageNumber(Integer.parseInt(commandLine.getOptionValue('p')));
        else
            workerUrl.setPageNumber(1);

        workerUrl.setConfig(userCfg);

        return workerUrl;
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

        if (commandLine.hasOption("sfw")) {
            userCfg.addForbiddenCategories(
                Arrays.asList("nude", "sex", "nipples", "penis", "pussy", "underwear", "topless", "gore",
                        "panties")
            );
        }

        if (commandLine.hasOption("no-comic")) {
            userCfg.addForbiddenCategory("comic");
        }

        userCfg.setImageTarget(commandLine.getOptionValue('t'));

        return userCfg;
    }

}
