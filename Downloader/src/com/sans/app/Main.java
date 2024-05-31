package com.sans.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static void handleCommand(String[] args) {
        if (args != null && args.length > 0) {
            switch (args[0]) {
                case "i":
                    handleInfo(args);
                    break;
                case "a":
                    handleAdd(args);
                    break;
                case "d":
                    handleDownload(args);
                    break;
                case "r":
                    handleResume(args);
                    break;
                default:
                    System.out.println("Please enter a valid command.");
            }
        } else {
            System.out.println("Sans Downloader.");
        }
    }

    private static void handleResume(String[] args) {
        try {
            Downloader.DlRequest dlRequest = Logger.readLog(args[1], new Logger.DefaultReader()).get(Logger.DefaultReader.DL_REQ);
            startDownload(dlRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static volatile boolean finished = false;

    private static void handleDownload(String[] args) {
        try {
            Downloader.DlRequest dlRequest = getDlreq(args);
            if (dlRequest != null)
                startDownload(dlRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static void startDownload(Downloader.DlRequest dlRequest) {
        try {
            final Downloader downloader = new Downloader(dlRequest, new Downloader.DownloadCallback() {

                @Override
                public void onProgress(float progress, int totalDownloaded, int speed) {
                    if (!finished) {
                        ConsoleUtils.clearLastLine();
                        ConsoleUtils.print(String.format("%s %% - %d MB - %s MB/Sec", Math.round(progress * 10000.0) / 100.0,
                                totalDownloaded / Downloader.MEG_BYTE, Math.round((speed / (float) Downloader.MEG_BYTE) * 100) / 100f));
                    }
                }

                @Override
                public void onFinish() {
                    finished = true;
                    System.out.println("\nDownload finished successfully!");
                    deleteResumeFile(dlRequest);
                }

                @Override
                public void onError(Exception e) {

                }
            });
            downloader.start();

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    downloader.stop(true);
                    if (!finished) {
                        dlRequest.setThreads(downloader.getThreadInfo());
                        try {
                            writeResumeFile(dlRequest);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleAdd(String[] args) {
        try {
            Downloader.DlRequest dlRequest = getDlreq(args);
            if (dlRequest != null)
                writeResumeFile(dlRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static Downloader.DlRequest getDlreq(String[] args) throws Exception {
        Downloader.DlRequest dlRequest = new Downloader.DlRequest();
        int threadCount = 4;
        InfoCatcher.DL_info dl_info;
        String dlPath = null;
        String url = null;
        String currentArg;
        for (int i = 1; i < args.length; i++) {
            currentArg = args[i];
            if (currentArg.equals("-t")) {
                threadCount = Integer.parseInt(args[++i]);
            } else if (currentArg.equals("-d")) {
                dlPath = args[++i];
            } else {
                url = currentArg;
            }
        }

        if (url == null) {
            throw new Exception("No URL provided.");
        }

        dlRequest.setThreadCount(threadCount);
        dlRequest.setDlInfo(dl_info = InfoCatcher.catchInfo(url));
        if (dl_info.hadErr) {
            System.out.printf("There was a problem:%s%s%n", System.lineSeparator(), dl_info.exception == null ? ("Status: " + dl_info.statusCode) : dl_info.exception.getMessage());
            return null;
        }
        dlRequest.setDownloadPath(dlPath == null ? dl_info.remoteFileName : dlPath);
        dlRequest.setThreads(generateThreadInfo(dl_info, threadCount));

        return dlRequest;
    }

    private static void handleInfo(String[] args) {
        InfoCatcher.DL_info dl_info = InfoCatcher.catchInfo(args[1]);
        if (!dl_info.hadErr) {
            System.out.println("URL:");
            System.out.println(dl_info.url);
            System.out.println("Status:");
            System.out.println(dl_info.statusCode);
            System.out.println("Length:");
            System.out.printf("%f MB%n", ((float) dl_info.contentLength) / Downloader.MEG_BYTE);
            System.out.println("Remote Name:");
            System.out.println(dl_info.remoteFileName);
            System.out.println("Resumeable:");
            System.out.println(dl_info.acceptsRanges);
        } else {
            System.out.printf("There was a problem:%s%s%n", System.lineSeparator(), dl_info.exception == null ? ("Status: " + dl_info.statusCode) : dl_info.exception.getMessage());
        }
    }

    public static void main(String[] args) {

        handleCommand(args);

        if (true)
            return;


        System.out.println(Arrays.toString(args));
        InfoCatcher.DL_info dl_info = InfoCatcher.catchInfo(args[0]);
        System.out.println(dl_info);

        Downloader.DlRequest dlRequest = new Downloader.DlRequest(generateThreadInfo(dl_info, 4), dl_info, 4
                , dl_info.remoteFileName);

        try {
            Downloader downloader = new Downloader(dlRequest, new Downloader.DownloadCallback() {
                private volatile boolean finished = false;

                @Override
                public void onProgress(float progress, int totalDownloaded, int speed) {
                    if (!finished) {
                        System.out.printf("%s %% - %d MB - %s MB/Sec%n", Math.round(progress * 10000.0) / 100.0,
                                totalDownloaded / Downloader.MEG_BYTE, Math.round((speed / (float) Downloader.MEG_BYTE) * 100) / 100f);
                    }
                }

                @Override
                public void onFinish() {
                    finished = true;
                    System.out.println("Download finished successfully!");
                }

                @Override
                public void onError(Exception e) {

                }
            });
            downloader.start();

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    downloader.stop(true);
                }
            }));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeResumeFile(Downloader.DlRequest dlRequest) throws IOException {
        Logger logger = new Logger(dlRequest.getDownloadPath() + "_resumefile",
                new Logger.DefaultHeaderWriter(dlRequest),
                new Logger.DefaultBodyWriter(dlRequest));
        logger.writeLog();
    }

    private static void deleteResumeFile(Downloader.DlRequest dlRequest) {
        File file = new File(dlRequest.getDownloadPath() + "_resumefile");
        file.delete();
    }


    private static Downloader.ThreadInfo[] generateThreadInfo(InfoCatcher.DL_info info, int threadCount) {
        Downloader.ThreadInfo[] threads = new Downloader.ThreadInfo[threadCount];
        int th_Len = info.contentLength / threadCount;
        int i = 0;
        for (; i < threadCount - 1; i++) {
            threads[i] = new Downloader.ThreadInfo(i * th_Len, th_Len, 0);
        }
        threads[i] = new Downloader.ThreadInfo(th_Len * i, info.contentLength - (th_Len * i), 0);
        return threads;
    }
}