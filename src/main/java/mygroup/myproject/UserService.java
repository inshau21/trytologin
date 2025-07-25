package mygroup.myproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {
	@Autowired
	private final UserMapper userMapper;
	
	//有參數的建構子
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public UserModel getUserModelById(Integer id) {
		return userMapper.findById(id);
	}
	
	
	// 查詢一筆(使用join的)
	public List<UserModel_afterJoin> getUserModelById_byJoin(Integer id){
		UserModel_afterJoin oneResult = userMapper.findById_byJoin(id);
		// 因為報表需要配合List型別來呈現
		List<UserModel_afterJoin> result_asList = new ArrayList<UserModel_afterJoin>();
		result_asList.add(0, oneResult);
		return result_asList;
	}
	// 查詢多筆
	public List<UserModel> getAllUserModels(){
		return userMapper.findAll();
	}
	// (使用join的)
	public List<UserModel_afterJoin> getAllUserModels_byJoin(){
		return userMapper.findAll_byJoin();
	}
	
	
    // 新增一筆
	public void addUserModels(UserModel userModel) {
		userMapper.insertUserModel(userModel);
	}
    // 新增多筆
	public void addUserModels_list(List<UserModel> list_userModel) {
		//確認資料不是null或空清單
		if(list_userModel==null || list_userModel.isEmpty()) {
			throw new IllegalArgumentException("資料清單不可為空");
		}
		//呼叫DAO進行批量新增
		userMapper.inserUserModelBatch(list_userModel);
	}
	
	
	// 修改一筆資料
	public void updateUserModel(String name,String class_,String book,int club_id) {
		userMapper.updateUserModel(name,class_,book,club_id);
	}
}
