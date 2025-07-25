package mygroup.myproject;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Mapper
public interface UserMapper {
//    @Select("SELECT * FROM user WHERE id = #{id}")
//    @Results({
//    	@Result(property = "id", column = "id"),
//    	@Result(property = "name", column = "user_name"),
//    	@Result(property = "class_", column = "class"),
//    	@Result(property = "book", column = "book")
//    })
    UserModel findById(@Param("id") Integer id);
    
//  @Select("select * from user natural join clubs WHERE id = #{id}")
//  @Results({
//  	@Result(property = "id", column = "id"),
//  	@Result(property = "name", column = "user_name"),
//  	@Result(property = "class_", column = "class"),
//  	@Result(property = "book", column = "book"),
//	  	@Result(property = "club_id", column = "club_id"),
//  	@Result(property = "club", column = "club"),
//  	@Result(property = "item", column = "item"),
//  	@Result(property = "fee", column = "fee")
//  })
    UserModel_afterJoin findById_byJoin(@Param("id") Integer id);
    
    
//    @Select("SELECT * FROM user")
//    @Results({
//    	@Result(property = "id", column = "id"),
//    	@Result(property = "name", column = "user_name"),
//    	@Result(property = "class_", column = "class"),
//    	@Result(property = "book", column = "book")
//    })
    List<UserModel> findAll();

//  @Select("select * from user natural join clubs")
//  @Results({
//  	@Result(property = "id", column = "id"),
//  	@Result(property = "name", column = "user_name"),
//  	@Result(property = "class_", column = "class"),
//  	@Result(property = "book", column = "book"),
//	  	@Result(property = "club_id", column = "club_id"),
//  	@Result(property = "club", column = "club"),
//  	@Result(property = "item", column = "item"),
//  	@Result(property = "fee", column = "fee")
//  })
    List<UserModel_afterJoin> findAll_byJoin();
    
    
  //新增1筆資料  
//  @Insert("INSERT INTO user(user_name,class,book,club_id) VALUES(#{name}, #{class_}, #{book}, #{club_id})")
//  @Results({
//  	@Result(property = "id", column = "id"),
//  	@Result(property = "name", column = "user_name"),
//  	@Result(property = "class_", column = "class"),
//  	@Result(property = "book", column = "book")
//  	@Result(property = "club_id", column = "club_id")
//  })
  void insertUserModel(UserModel userModel);
  //新增多筆資料  
  void inserUserModelBatch(List<UserModel> list_userModel);

  //修改1筆資料 
  void updateUserModel(String name,String class_,String book,int club_id);
  
}
