package io.github.yeop.jpalearn.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 쓸때는 둘중에 하나만 해줘도 됨. 하지만 두개다 쓰면 직관적임
    private Address address;

    @OneToMany(mappedBy = "member") // order 엔티티에 있는 멤버 엔티티 이름
    private List<Order> orders = new ArrayList<>();
}
