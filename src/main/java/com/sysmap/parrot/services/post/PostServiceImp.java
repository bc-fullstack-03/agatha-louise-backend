package com.sysmap.parrot.services.post;

import com.sysmap.parrot.data.PostRepository;
import com.sysmap.parrot.entities.friend.model.FriendResponse;
import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.PostRequest;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.PostUpdateRequest;
import com.sysmap.parrot.entities.user.model.UserResponse;
import com.sysmap.parrot.mappers.post.PostMapper;
import com.sysmap.parrot.services.friend.FriendService;
import com.sysmap.parrot.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PostServiceImp implements PostService {

	public static final String SEM_POSTS_PARA_LISTAR = "Sem posts para listar";
	public static final String AINDA_NAO_CRIOU_UM_POST = "Usuario ainda não criou um post";
	public static final String NAO_ENCONTRADO = "Post não encontrado pelo idPost";

	@Autowired
	PostRepository repository;

	@Autowired
	UserService userService;

	@Autowired
	FriendService friendService;

	@Autowired
	PostMapper mapper;



	@Override
	public PostResponse createPost(PostRequest request) {
		UserResponse user = userService.findById(request.getAuthorId());

		Post post = new Post(request.getAuthorId(), user.getName(), request.getContent(), request.getMedia(), new ArrayList<>(), new ArrayList<>());
		log.info("salvando post com os dados {}", post);

		repository.save(post);

		return mapper.mapToPostResponse(post);

	}

	public PostResponse getPostbyIdPost (UUID idPost) {

		Post post = findPostWithoutMapper(idPost);
		return mapper.mapToPostResponse(post);
	}

	@Override
	public List<PostResponse> getAllPostIFollow(UUID idUser) {

		List<Post> posts = repository.findAll();

		FriendResponse friendResponse = friendService.getListFollowerById(idUser);
		log.info("Post service buscando a lista de seguidos {} ", friendResponse.getFollowingId());


		List<PostResponse> postResponseList = mapper.mapResponseList(posts)
				.stream()
				.filter(post -> friendResponse.getFollowingId().contains(post.getIdAuthor())).toList();


		if (postResponseList.isEmpty()){
			throw new NoSuchElementException(SEM_POSTS_PARA_LISTAR);
		}

		log.info("postResponseList(0) apos o filtro e mapeamento {} ", postResponseList.get(0));

		return postResponseList;
	}

	@Override
	public List<PostResponse> getPostsByIdAuthor(UUID idUser) {

		Optional<List<Post>> posts = repository.findByIdAuthor(idUser);

		if (posts.isEmpty()){
			throw new NoSuchElementException(AINDA_NAO_CRIOU_UM_POST);
		}

		List<Post> postList = posts.get();

		return mapper.mapResponseList(postList);
	}

	@Override
	public PostResponse updatePost(PostUpdateRequest request) {
		Post post = findPostWithoutMapper(request.getIdPost());


		if (request.getMedia() != null) {
			post.setMedia(request.getMedia());
		}

		if (request.getContent() != null) {
			post.setContent(request.getContent());
		}

		repository.save(post);

		return mapper.mapToPostResponse(post);
	}
	@Override
	public void deletePostByIdPost(UUID id) {
		getPostbyIdPost(id);
		repository.deleteById(id);
	}

	@Override
	public Post findPostWithoutMapper (UUID id){
		return repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(NAO_ENCONTRADO));
	}
}
