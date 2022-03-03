package jpabook.jpashop.domian;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
    // 셀프로 설계한 필드로 부모 ID에 여러 child가 있을테니 이렇게 설계한다.

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
                joinColumns = @JoinColumn(name = "CATEGORY_ID"),
                inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    // 중간 테이블을 만들어 주는 과정이다.
    // 내가 Join 해주는 부분이 joinColumns , 반대쪽이 Join하는 컬럼이 inverseJoinColumns 이다.
    private List<Item> items = new ArrayList<>();
}
