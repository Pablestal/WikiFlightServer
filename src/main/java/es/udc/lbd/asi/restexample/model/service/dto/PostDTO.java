package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Post;

public class PostDTO {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotNull
    private UserDTOPublic author;
    private List<TagDTO> tags;

    public PostDTO() {
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = new UserDTOPublic(post.getAuthor());
        this.tags = new ArrayList<>();
        post.getTags().forEach(t -> {
            this.tags.add(new TagDTO(t));
        });
        this.tags.sort(Comparator.comparing(TagDTO::getName));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserDTOPublic getAuthor() {
        return author;
    }

    public void setAuthor(UserDTOPublic author) {
        this.author = author;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }
}
