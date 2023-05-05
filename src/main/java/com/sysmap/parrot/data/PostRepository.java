package com.sysmap.parrot.data;

import com.sysmap.parrot.entities.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends MongoRepository<Post, UUID> {
}
