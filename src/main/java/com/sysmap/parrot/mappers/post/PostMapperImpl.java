package com.sysmap.parrot.mappers.post;

import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.PostResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapperImpl implements PostMapper {
	@Override
	public PostResponse mapToPostResponse(Post post) {

		PostResponse response = new PostResponse();
		response.setId(post.getId());
		response.setIdAuthor(post.getIdAuthor());
		response.setNameUser(post.getNameAuthor());
		response.setContent(post.getContent());
		response.setMedia(post.getMedia());
		response.setLikes(post.getLikes());
		response.setCommentList(post.getCommentList());
		response.setNumberLikes(post.getLikes());
		response.setNumberComments(post.getCommentList());

		return response;
	}

	@Override
	public List<PostResponse> mapResponseList(List<Post> post) {

		return post.stream()
			   .map(posts -> new PostResponse(
					   posts.getId(),
					   posts.getIdAuthor(),
					   posts.getNameAuthor(),
					   posts.getContent(),
					   posts.getMedia(),
					   posts.getLikes(),
					   posts.getCommentList()
			   ))
			   .collect(Collectors.toList());
	}
}
