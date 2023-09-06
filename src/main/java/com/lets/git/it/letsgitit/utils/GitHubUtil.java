package com.lets.git.it.letsgitit.utils;

import org.kohsuke.github.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class GitHubUtil {

    /**
     * [GHRepository] GitHubRepository 조회
     */
    public GHRepository getGitHubRepo() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken("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").build();
        GHRepository ghRepository = gitHub.getRepository("duddn2012/HelloThere");
        Map<String, Long> langList = ghRepository.listLanguages();  //언어 리스트

        return ghRepository;
    }

    /**
     * [List<GHIssue>] GitHubRepository의 Issue List 조회
     */
    public List<GHIssue> getRepoIssues() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken("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").build();
        GHRepository ghRepository = gitHub.getRepository("duddn2012/HelloThere");
        List<GHIssue> issues;
        List<GHIssueComment> comments;

        issues = ghRepository.getIssues(GHIssueState.ALL);
        return issues;
    }

    /**
     * [List<GHIssue>] GitHub - Organization - Repository의 Issue List 조회
     */
    public List<GHIssue> getOrgRepoIssues() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken("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").build();
        GHOrganization ghOrganization = gitHub.getOrganization("UMC-Hello-There");
        GHRepository ghRepository = ghOrganization.getRepository("HelloThere");
        List<GHIssue> issues;
        List<GHIssueComment> comments;

        List<GHCommit> ghCommit =ghRepository.listCommits().toList();
        for (GHCommit commit: ghCommit) {
            System.out.println(ghRepository.getOwnerName()+"-------------------------------");
            if(commit.getAuthor().getLogin().equals(ghRepository.getOwnerName())){
                System.out.println(commit.getAuthor().getLogin());
            }
        }
        issues = ghRepository.getIssues(GHIssueState.ALL);
        return issues;
    }

    /**
     * [List<GHCommit>] GitHubRepository의 Commit List 조회
     * TODO stream collect 시 약 40초 가량의 시간이 소요됨 성능 개선 필요
     */
    public List<GHCommit.ShortInfo> getRepoCommits() throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken("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").build();
        GHRepository ghRepository = gitHub.getRepository("duddn2012/HelloThere");
        List<GHCommit> ghCommit =ghRepository.listCommits().toList();
        List<GHCommit.ShortInfo> sf = ghCommit.stream().map(it-> {
            try {
                return it.getCommitShortInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        return sf;
    }

    /**
    public void getCommitByGitHubRestAPI(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/repos/duddn2012/HelloThere/commits";

        Mono<String> response = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(
                responseBody -> System.out.println(responseBody),
                error -> error.printStackTrace()
        );
    }
     */
}
