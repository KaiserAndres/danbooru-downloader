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
        String versionNumber = "1.2";

        Options cliOptions = CLIOptionCreator.getPageOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(cliOptions, args);
        } catch (ParseException e) {

            if (Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help"))
                displayHelp(cliOptions);

            else if (Arrays.asList(args).contains("-v") || Arrays.asList(args).contains("--version"))
                System.out.println(programName + " v" + versionNumber);

            else
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
            page.processPage();

            downloadManager.addPosts(page.getPosts());
            int totalPosts = downloadManager.getPostCount();
            int downloaded = 1;

            while (downloadManager.hasElements()) {
                downloadManager.download();
                System.out.println(downloaded + "/" + totalPosts);
                downloaded++;
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

        for (String category : commandLine.getOptionValues('d')) {
            userCfg.addDesiredCategory(category);
        }

        if (commandLine.hasOption('f')) {
            for (String category : commandLine.getOptionValues('f')) {
                userCfg.addForbiddenCategory(category);
            }
        }

        if (commandLine.hasOption("sfw")) {
            userCfg.addForbiddenCategories(
                Arrays.asList("nude", "sex", "nipples", "penis", "pussy", "underwear", "topless", "gore",
                        "panties", "cum", "naked apron", "ass", "no panties", "micro bikini")
            );
        }

        if (commandLine.hasOption("no-comic")) {
            userCfg.addForbiddenCategory("comic");
        }

        if (commandLine.hasOption("min-res")) {
            String[] min_res = commandLine.getOptionValues("min-res");
            if (min_res.length == 2) {
                userCfg.setMin_res(userCfg.WIDTH, Integer.parseInt(min_res[0]));
                userCfg.setMin_res(userCfg.HEIGHT, Integer.parseInt(min_res[1]));
            }
        }

        if (commandLine.hasOption("max-res")) {
            String[] max_res = commandLine.getOptionValues("max-res");
            if (max_res.length == 2) {
                userCfg.setMin_res(userCfg.WIDTH, Integer.parseInt(max_res[0]));
                userCfg.setMin_res(userCfg.HEIGHT, Integer.parseInt(max_res[1]));
            }
        }

        userCfg.setImageTarget(commandLine.getOptionValue('t'));

        return userCfg;
    }

}
