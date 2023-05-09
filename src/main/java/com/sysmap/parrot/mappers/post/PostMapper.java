package com.sysmap.parrot.mappers.post;


import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.PostResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

	PostResponse mapToPostResponse (Post post);

	List<PostResponse> mapResponseList(List<Post> post);

}
