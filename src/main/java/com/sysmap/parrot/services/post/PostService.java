package com.sysmap.parrot.services.post;

import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.PostRequest;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.PostUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface PostService {

	PostResponse createPost(PostRequest request);

	PostResponse getPostbyIdPost(UUID idPost);

	List<PostResponse> getAllPostIFollow(UUID idUser);

	List<PostResponse> getPostsByIdAuthor(UUID idUser);

	PostResponse updatePost(PostUpdateRequest request);

	Post findPostWithoutMapper (UUID id);

	void deletePostByIdPost(UUID id);

}
