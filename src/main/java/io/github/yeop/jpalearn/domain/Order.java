package io.github.yeop.jpalearn.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 연관관계의 주인은 FK가 있는 테이블이 연관관계 주인으로 설정. 성능이슈, 직관적
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1대 1 관계에서는 어디든 FK를 두어도 됨. 주로 액세스를 많이하는 곳에 FK를 둠. 주문 , 배송일 경우 FK를 주문에 둔다.
    @OneToOne
    @JoinColumn(name = "delivery_id") // 연관관계 주인
    private Delivery delivery;

    //자바 8부터 LocalDateTime이거 쓰면 알아서 하이버네이트가 매핑해줌
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Enum 타입은 무조건 @Enumerated(EnumType.STRING) ORDINAL절대 쓰지말것
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
