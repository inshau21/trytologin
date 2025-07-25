package mygroup.myproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@Transactional
@RequestMapping("/user")
public class UserController {
	@Autowired
    private final UserService userService;
	@Autowired
    private ReportService reportService;
//	@Autowired
//	DataSourceTransactionManager datasrcTranM;
//	@Autowired
//	JdbcTemplate jdbcT;
    
    public UserController(UserService userService) {
    	this.userService = userService;
    }
    
    @GetMapping("/{id}")
	public void getUserModelById_pdf(HttpServletResponse rsp, @PathVariable Integer id) {
    	//(json)回傳查詢的結果
		//return userService.getUserModelById(id);
    	try {
    		// 拿取資料庫的資料(搜尋結果為一對多)
    		List<UserModel_afterJoin> dataSrc = userService.getUserModelById_byJoin(id);
    		// 報表參數
    		Map<String, Object> parameters = new HashMap<>();
            parameters.put("reportTitle", "查詢單一id的資料");
            // 設置response的格式
            rsp.setContentType("application/pdf");
            rsp.setHeader("Content-Disposition", "inline; filename=report.pdf");
            // 生成報表
            reportService.generateReport("demo_Blank_A4", parameters, dataSrc, rsp.getOutputStream());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
    // 網址的port號8080之後加"/user"(按照上面的RequestMapping的設定) method:'get'走這裡
    @GetMapping
	public List<UserModel> getAllUserModels(){
		return userService.getAllUserModels();
	}
	
    @GetMapping("/pdf")
	public void getAllUserModels_pdf(HttpServletResponse rsp, @RequestParam(required = false) String reportTitle){
    	try {
    		//假資料
    		//List<UserModel> dataSrc = List.of(new UserModel(1,"1st","英文","莎士比亞"), new UserModel(2,"2nd","國文","老子"));
    		// 拿取資料庫的資料
    		//List<UserModel> dataSrc = userService.getAllUserModels();
    		// 拿取資料庫的資料(搜尋結果為一對多)
    		List<UserModel_afterJoin> dataSrc = userService.getAllUserModels_byJoin();
    		// 報表參數
    		Map<String, Object> parameters = new HashMap<>();
            parameters.put("reportTitle", "查詢資料表內全部的資料");
            // 設置response的格式
            rsp.setContentType("application/pdf");
            rsp.setHeader("Content-Disposition", "inline; filename=report.pdf");
            // 生成報表
            reportService.generateReport("demo_Blank_A4", parameters, dataSrc, rsp.getOutputStream());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
    
    // 網址的port號8080之後加"/user"(按照上面的RequstMapping的設定) method:'post'走這裡
    @PostMapping
	public String addUserModels(@RequestBody UserModel userModel) {
    	userService.addUserModels(userModel);
    	return "User added successfully!";
	}
    
    @PostMapping("/addusers")
	public String addUserModels_plural(@RequestBody List<UserModel> list_userModel) {
    	//傳入的參數原本為 @RequestBody UserModel userModel
////  	使用jdbcTemplate取得 sql_server的 dataSrc,然後把它帶給 dataSrcTransactionManager,才能有效使用 commit()和 rollback()
//    	datasrcTranM.setDataSource(jdbcT.getDataSource());
//    	DefaultTransactionDefinition default_TranDef = new DefaultTransactionDefinition();
//    	default_TranDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//    	TransactionStatus tran_status = datasrcTranM.getTransaction(default_TranDef);
    	
    	try {
    		//新增多筆資料
    		System.out.println("新增多筆資料開始");
    		userService.addUserModels_list(list_userModel);
    		System.out.println("新增多筆資料結束");
    		//UserModel user_2 = new UserModel(1,"第22個","test_12","test_12",1); // id故意重複
    		//System.out.println("新增第二筆資料");
    		//userService.addUserModels(user_2); // id故意重複
    		//System.out.println("新增第二筆資料結束");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "userlist added successfully~";
//    	
//    	catch (RuntimeException e) {
//    		datasrcTranM.rollback(tran_status);
//    	}
	}
    
    // 修改1筆資料
    @GetMapping("/update_1/{name}/{class_}/{book}/{club_id}")
    //@RequestMapping
	public String updateUserModel(@PathVariable String name,@PathVariable String class_,@PathVariable String book,
			@PathVariable int club_id) {
    	try {
        	if(name.equals("第25個")) {
            	userService.updateUserModel(name,class_,book,club_id);
        	} else {
        		name = "第25個";
        		class_ = "test_15_2";
        		book = "test_15_2";
        		club_id = 2;
        		userService.updateUserModel(name,class_,book,club_id);
        	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "來自後端回傳的訊息_修改完成";
	}
}
