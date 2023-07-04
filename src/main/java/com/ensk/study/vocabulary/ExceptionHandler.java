package com.ensk.study.vocabulary;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        FrameContainer.noticeAndQuit(throwable.getMessage());
    }
}