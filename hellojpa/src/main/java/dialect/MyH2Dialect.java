package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect extends H2Dialect {
    public MyH2Dialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
// H2Dialect 안에 들어가 소스코드를 보면
// 등록후 persistence.xml 들어가자.
// org.hibernate.dialect.H2Dialect -> <property name="hibernate.dialect" value="dialect.MyH2Dialect"/>
