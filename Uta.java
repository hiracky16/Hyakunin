import java.util.*;
public class Uta{
	private int id;
	private String kaminoku;
	private String shimonoku;
	private String author;
	private int kind;
	//コンストラクタ
	Uta(int id, String kaminoku, String shimonoku, String author, int kind){
		this.id=id;
		this.kaminoku=kaminoku;
		this.shimonoku=shimonoku;
		this.author=author;
		this.kind=kind;
	}
	//歌のIDを返す
	public int getId(){
		return id;
	}
	//歌を返す
	public String getKamiShimo(){
		return kaminoku+shimonoku;
	}
	//歌の上の句を返す
	public String getKaminoku(){
		return kaminoku;
	}
	//歌の下の句を返す
	public String getShimonoku(){
		return shimonoku;
	}
	//歌の作者を返す
	public String getAuthor(){
		return author;
	}
	//札の種類を返す
	public int getKind(){
		return kind;
	}
	
}
		
