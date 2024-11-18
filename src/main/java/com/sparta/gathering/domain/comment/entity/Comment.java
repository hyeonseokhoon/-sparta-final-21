package com.sparta.gathering.domain.comment.entity;

import com.sparta.gathering.common.entity.Timestamped;
import com.sparta.gathering.domain.gather.entity.Gather;
import com.sparta.gathering.domain.member.entity.Member;
import com.sparta.gathering.domain.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String comment;//댓글

    @Column
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "schedule_Id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "member_Id")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "gather_id")
//    private Gather gather;

    public Comment(String comment,Schedule schedule,Member member){
        this.nickName = member.getUser().getNickName();
        this.comment = comment;
        this.schedule = schedule;
        this.member = member;
    }
    public void update(String comment){
        this.comment = comment ;
    }
    public void delete(){
        this.deleteAt = LocalDateTime.now();
    }
}

