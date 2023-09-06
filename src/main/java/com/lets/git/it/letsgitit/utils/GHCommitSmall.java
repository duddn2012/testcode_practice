package com.lets.git.it.letsgitit.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.Date;

/**
 * [GHCommit]의 stream 사용 시 성능 문제 해결을 위해 만든 필요한 데이터 만을 담는 객체
 * 또한, stream 내에 Exception 처리를 해야하는 문제도 해결
 */
@RequiredArgsConstructor
@Getter
public class GHCommitSmall {
    GHUser ghUser;
    Date date;
    String message;
    public GHCommitSmall(GHCommit ghCommit) throws IOException {
        this.ghUser = ghCommit.getAuthor();
        this.date = ghCommit.getAuthoredDate();
        this.message = ghCommit.getCommitShortInfo().getMessage();
    }
}
