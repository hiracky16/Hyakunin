import java.util.*;
import java.io.*;
public class UtaList{
	ArrayList<Uta> list = new ArrayList<Uta>();
	//CSVファイルから歌のデータを受け取り全リストを作る
	void createAllList(){
                Uta a;
                a= new Uta(0, "", "", "", 0);
                try{
                        BufferedReader br = new BufferedReader(new FileReader(new File("hyakunin.csv")));
                        String line = "";
                        while((line = br.readLine()) != null){
                                String[] tokens = line.split(",", 0);
                                int i=Integer.parseInt(tokens[0]);
                                int j=Integer.parseInt(tokens[7]);
                                a=new Uta(i, tokens[1], tokens[2], tokens[5], j);
                                addUta(a);
                        }
                        br.close();
                }catch(FileNotFoundException e){
                        System.out.println("File not found");
                }catch(IOException e){
                        System.out.println("IOException");
                }
	}
	//歌のIDからリスト内の歌のindexを返す
	Uta getUta(int id){
		int i=0;
		while(i<list.size()){
			if(id==list.get(i).getId()){
				break;
			}
			i++;
		}
		return list.get(i);
	}
	//リスト内の歌を参照する
	Uta referUta(int i){
		return list.get(i);
	}
	//リストに歌を追加する
	void addUta(Uta a){
		list.add(a);
	}
	//リスト内の歌を削除する
	void deleteUta(int n){
		list.remove(n);
	}
	//リストの長さを返す	
	int getSize(){
		return list.size();
	}
	//リストを表示する
	void print(){
		for(int i=0; i<list.size(); i++){
			int id=list.get(i).getId();
			String kami=list.get(i).getKaminoku();
			String shimo=list.get(i).getShimonoku();
			String aut=list.get(i).getAuthor();
			int kind=list.get(i).getKind();
			System.out.printf("%d %s %s %s %d\n", id, kami, shimo, aut, kind);
		}
	}
	//リストのindexからその歌のIDを返す
	int getUtaListId(int n){
		return list.get(n).getId();
	}
	//０～２の数を返す（クイズの答えになる）
	int choiceNum(){
		Random ran = new Random();
		int n = ran.nextInt(3);
		return list.get(n).getId();
	}
	//リスト内の歌の下の句を表示
	void shimoPrint(){
		for(int i=0; i<list.size(); i++){
			String shimo=list.get(i).getShimonoku();
			System.out.printf("[%d] %s\n", i+1, shimo);
		}
	}
	//リスト内の歌の作者を表示
	void authorPrint(){
                for(int i=0; i<list.size(); i++){
                        String author=list.get(i).getAuthor();
                        System.out.printf("[%d] %s\n", i+1, author);
                }
        }
	//リストをシャッフルする
	void shuffleList(){
		Collections.shuffle(list);
	}
	//絵札の種類を表示する
	void printKind(int i){
		if(list.get(i).getKind()==0){
			System.out.println("坊主");
		}else if(list.get(i).getKind()==1){
			System.out.println("殿");
		}else if(list.get(i).getKind()==2){
			System.out.println("姫");
		}else{
			System.out.println("エラー！");
		}
	}
			
}		




