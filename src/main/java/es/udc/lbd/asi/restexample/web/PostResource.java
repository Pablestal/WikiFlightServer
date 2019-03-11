package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.service.PostService;
import es.udc.lbd.asi.restexample.model.service.dto.PostDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostDTO findOne(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public PostDTO save(@RequestBody @Valid PostDTO post, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors);
        return postService.save(post);
    }

    @PutMapping("/{id}")
    public PostDTO update(@PathVariable Long id, @RequestBody @Valid PostDTO post, Errors errors)
            throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
        errorHandler(errors);
        if (id != post.getId()) {
            throw new IdAndBodyNotMatchingOnUpdateException(Post.class);
        }
        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        postService.deleteById(id);
    }

    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
        if (errors.hasErrors()) {
            String errorMsg = errors.getFieldErrors().stream()
                    .map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("; "));
            throw new RequestBodyNotValidException(errorMsg);
        }
    }
}
