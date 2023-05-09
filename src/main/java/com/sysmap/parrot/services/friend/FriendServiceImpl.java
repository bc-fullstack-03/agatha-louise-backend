package com.sysmap.parrot.services.friend;

import com.sysmap.parrot.data.FriendRepository;
import com.sysmap.parrot.entities.friend.Friend;
import com.sysmap.parrot.entities.friend.model.FriendResponse;
import com.sysmap.parrot.entities.friend.model.FriendRequest;
import com.sysmap.parrot.mappers.friend.FriendMapper;
import com.sysmap.parrot.services.exceptions.DataIntegratyViolationException;
import com.sysmap.parrot.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendServiceImpl implements FriendService {

	public static final String SEM_LISTA_DE_SEGUINDO_E_SEGUIDORES = "USUARIO SEM LISTA DE SEGUINDO E SEGUIDORES";

	@Autowired
	FriendRepository repository;

	@Autowired
	UserService userService;

	@Autowired
	FriendMapper mapper;

	@Override
	public FriendResponse followUser(FriendRequest request) {

		findUserById(request);

		Optional<Friend> followerOptional = repository.findById(request.getFollowerId());
		Optional<Friend> followedOptional = repository.findById(request.getFollowedId());

		if(followerOptional.isPresent() && followerOptional.get().getFollowingId().contains(request.getFollowedId())){
			throw new DataIntegratyViolationException("Você já segue esse usuário");
		}

		if(followedOptional.isPresent()){

			followedOptional.get().getFollowersId().add(request.getFollowerId());

			Friend followed = followedOptional.get();
			repository.save(followed);

		} else {
			List<UUID> followersIdList = new ArrayList<>();
			followersIdList.add(request.getFollowerId());

			Friend followed = new Friend(request.getFollowedId(), new ArrayList<>(), followersIdList);
			repository.save(followed);
		}


		if (followerOptional.isPresent()){
			followerOptional.get().getFollowingId().add(request.getFollowedId());

			Friend follower = followerOptional.get();
			repository.save(follower);

			return mapper.mappingFriend(follower);
		} else {
			List<UUID> followingIdList = new ArrayList<>();
			followingIdList.add(request.getFollowedId());

			Friend follower = new Friend(request.getFollowerId(),followingIdList, new ArrayList<>());
			repository.save(follower);

			return mapper.mappingFriend(follower);
		}

	}

	@Override
	public FriendResponse getListFollowerById(UUID id) {
		userService.findById(id);

		Friend friend = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(SEM_LISTA_DE_SEGUINDO_E_SEGUIDORES));

		return mapper.mappingFriend(friend);
	}

	@Override
	public FriendResponse unfollowUser(FriendRequest request) {
		findUserById(request);

		Friend follower = repository.findById(request.getFollowerId())
				.orElseThrow(() -> new NoSuchElementException(SEM_LISTA_DE_SEGUINDO_E_SEGUIDORES));

		Friend followed = repository.findById(request.getFollowedId())
				.orElseThrow(() -> new NoSuchElementException(SEM_LISTA_DE_SEGUINDO_E_SEGUIDORES));;


			followed.getFollowersId().remove(request.getFollowerId());
			repository.save(followed);

			follower.getFollowingId().remove(request.getFollowedId());
			repository.save(follower);

			return mapper.mappingFriend(follower);
	}

	@Override
	public void findUserById(FriendRequest request) {
		userService.findById(request.getFollowerId());
		userService.findById(request.getFollowedId());
	}
}
