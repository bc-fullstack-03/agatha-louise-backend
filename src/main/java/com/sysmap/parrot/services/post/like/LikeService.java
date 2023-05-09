package com.sysmap.parrot.services.post.like;

import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.like.LikeRequest;

import java.util.UUID;

public interface LikeService {

	PostResponse likePost(LikeRequest request, UUID idPost);

	PostResponse likeComment(LikeRequest request, UUID idPost);

	PostResponse deslikePost(LikeRequest request, UUID idPost);

	PostResponse deslikeComment(LikeRequest request, UUID idPost);

}
