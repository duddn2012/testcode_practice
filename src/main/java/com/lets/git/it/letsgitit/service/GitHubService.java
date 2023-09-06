package com.lets.git.it.letsgitit.service;

import com.lets.git.it.letsgitit.utils.GHCommitSmall;
import com.lets.git.it.letsgitit.utils.GitHubUtil;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubUtil gitHubUtil;


    public GHRepository getRepoInfo() throws IOException {
        GHRepository gitHub = gitHubUtil.getGitHubRepo();
        return gitHub;
    }

    public List<GHIssue> getRepoIssues() throws IOException {
        return gitHubUtil.getRepoIssues();
    }

    public List<GHIssue> getOrgRepoIssues() throws IOException {
        return gitHubUtil.getOrgRepoIssues();
    }

    public List<GHCommit.ShortInfo> getRepoCommits() throws IOException{
        return gitHubUtil.getRepoCommits();
    }
}
