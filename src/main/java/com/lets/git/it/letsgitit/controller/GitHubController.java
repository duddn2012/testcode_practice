package com.lets.git.it.letsgitit.controller;

import com.lets.git.it.letsgitit.dto.RepoCommitReq;
import com.lets.git.it.letsgitit.dto.RepoInfoReq;
import com.lets.git.it.letsgitit.dto.RepoIssueReq;
import com.lets.git.it.letsgitit.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHCommit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github")
public class GitHubController {
    private final GitHubService gitHubService;
    @GetMapping("")
    public ResponseEntity<RepoInfoReq> getHouseInfo() throws IOException {
        RepoInfoReq res = new RepoInfoReq(gitHubService.getRepoInfo().toString());
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/repo-issues")
    public ResponseEntity<RepoIssueReq> getRepoIssues() throws IOException {
        RepoIssueReq res = new RepoIssueReq(gitHubService.getRepoIssues().stream().count());
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/org-issues")
    public ResponseEntity<RepoIssueReq> getOrgIssues() throws IOException {
        RepoIssueReq res = new RepoIssueReq(gitHubService.getOrgRepoIssues().stream().count());
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/repo-commits")
    public ResponseEntity<RepoCommitReq> getRepoCommits() throws IOException {
        List<GHCommit.ShortInfo> commitList = gitHubService.getRepoCommits().stream().toList();
        RepoCommitReq res = new RepoCommitReq(commitList);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}

