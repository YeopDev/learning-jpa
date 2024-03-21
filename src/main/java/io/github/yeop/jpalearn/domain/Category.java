package io.github.yeop.jpalearn.domain;

import io.github.yeop.jpalearn.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // 중간테이블 매핑 필요/ 객체는 컬렉션이 있어서 다대다 관계가 가능한데 DB는 일대다 다대일 관계로 풀어야함 / 실무에서는 절대 다대다로 설계하지 말것
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 셀프로 양방향 연관관계 설정 / 카테고리 연관관계 설정 방법
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
