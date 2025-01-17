package com.sparta.gathering.domain.like.repository;

import com.sparta.gathering.domain.gather.entity.Gather;
import com.sparta.gathering.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long>,LikeCustomRepository {

    int countByGather(Gather gather);
}