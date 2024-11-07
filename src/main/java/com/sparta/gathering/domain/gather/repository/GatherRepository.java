package com.sparta.gathering.domain.gather.repository;

import com.sparta.gathering.domain.gather.entity.Gather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GatherRepository extends JpaRepository<Gather, Long>, GatherCustomRepository {

    // 생성일 기준 내림차순 정렬 후 상위 5개 모임 조회
    List<Gather> findTop5ByOrderByCreatedAtDesc();
}
