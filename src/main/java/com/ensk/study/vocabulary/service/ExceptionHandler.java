package com.ensk.study.vocabulary.service;

import com.ensk.study.vocabulary.ui.FrameContainer;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        FrameContainer.noticeAndQuit(throwable.getMessage());
    }
}