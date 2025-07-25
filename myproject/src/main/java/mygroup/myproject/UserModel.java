package mygroup.myproject;

import lombok.Data;

@Data
public class UserModel {
	//使用者id
	private Integer id;
	//使用者名稱
	private String name;
	//課程名稱
	private String class_;
	//書本名稱
	private String book;
	//社團編號
	private Integer club_id;
	
	
	//getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}
	
	public Integer getClub_id() {
		return club_id;
	}

	public void setClub_id(Integer club_id) {
		this.club_id = club_id;
	}
	
	//建構子
	public UserModel(Integer id, String name, String class_, String book, Integer club_id) {
		super();
		this.id = id;
		this.name = name;
		this.class_ = class_;
		this.book = book;
		this.club_id = club_id;
	}
	
	//toString
	@Override
	public String toString() {
		return "userModel [id=" + id + ", name=" + name + ", class_=" + class_ + ", book=" + book +  ", club_id=" + club_id +"]";
	}
}
