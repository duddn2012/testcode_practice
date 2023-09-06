package com.lets.git.it.letsgitit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kohsuke.github.GHCommit;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RepoCommitReq {
    private List<GHCommit.ShortInfo> shortInfos;
}
