package jpabook.jpashop.domian;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;    //pk

    @Column(name = "MEMBER_ID")
    private Long memberId; //fk

//    private Member member; // 객체지향적이기 위해 Member추가.

    private LocalDateTime orderDate;    // DBA 분들이 원하는 테이블명 : ORDER_DATE, order_date

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
