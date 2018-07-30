# common-mapper

### Example
```java
CommonMapper commonMapper = context.getBean(CommonMapper.class);

//保存
User user = new User();
user.setUserName("test");
user.setPassWord("test");
user.setSex("男");
user.setUserMark("test");
commonMapper.insert(User.class, user);

//查询
User user = new User();
user.setUserName("test");
user.setPassWord("test");
User result = commonMapper.selectOne(User.class, user);

//更新
User user = new User();
user.setId(1L);
user.setUserName("test");
user.setPassWord("test");
user.setSex("男");
user.setUserMark("test");
commonMapper.updateNotNullById(User.class, user);
```

### entity定义
```java
/**
 * @Cacheable 是否缓存(不缓存不加)
 */
@Cacheable
/**
 * 表名映射
 */
@Entity(name = "t_user")
public class User {

    /**
     * 主键生成
     */
    @GeneratedValue(generator = "select nextval('seq_user')")
    /**
     * 主键
     */
    @Id
    /**
     * 字段映射
     */
    @Column
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public User() {
    }
}

```

### 通用方法
```java
/**
 * 保存
 *
 * @param clazz
 * @param obj
 */
void insert(Class<?> clazz, @Param(PARAM) Object obj);

/**
 * 根据主键更新所有的属性(包括空值)
 *
 * @param clazz
 * @param obj
 */
void updateAllById(Class<?> clazz, @Param(PARAM) Object obj);

/**
 * 根据主键更新不为空的属性
 *
 * @param clazz
 * @param obj
 */
void updateNotNullById(Class<?> clazz, @Param(PARAM) Object obj);

/**
 * 根据主键删除
 *
 * @param clazz
 * @param id
 */
void deleteById(Class<?> clazz, @Param(PARAM) Long id);

/**
 * 根据不为空的属性删除
 *
 * @param clazz
 * @param obj
 */
void delete(Class<?> clazz, @Param(PARAM) Object obj);

/**
 * 根据主键查询
 *
 * @param clazz
 * @param id
 * @param <T>
 * @return
 */
<T> T selectOneById(Class<T> clazz, @Param(PARAM) Long id);

/**
 * 根据不为空的属性查询一个
 *
 * @param clazz
 * @param obj
 * @param <T>
 * @return
 */
<T> T selectOne(Class<T> clazz, @Param(PARAM) Object obj);

/**
 * 根据不为空的属性查询集合
 *
 * @param clazz
 * @param obj
 * @param <T>
 * @return
 */
<T> List<T> select(Class<T> clazz, @Param(PARAM) Object obj);

/**
 * 查询所有
 *
 * @param clazz
 * @param <T>
 * @return
 */
<T> List<T> selectAll(Class<T> clazz);

/**
 * 获取记录数
 *
 * @param clazz
 * @return
 */
long count(Class<?> clazz);
```

