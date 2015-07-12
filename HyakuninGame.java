import java.util.*;
import java.io.*;
public class HyakuninGame{
		static UtaList alllist;
		static int wins=0;
	public static void main(String[] arg) throws IOException{
		alllist=new UtaList();
		//全リストを作成
		alllist.createAllList();
		//ゲーム開始
		System.out.println("百人一首ゲーム");
		System.out.printf("\n");
		System.out.println("[1]下の句当てゲーム");
		System.out.println("[2]作者当てゲーム");
		System.out.println("[3]坊主めくり");
		System.out.printf("\n");
		System.out.print("どのゲームで遊びますか:");
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		System.out.printf("\n");
		switch (n){
			case 1: quiz(n); break;
			case 2: quiz(n); break;
			case 3: bozuMekuri(); break;
			default: System.out.println("番号が違います"); break;
		}
	}
	//全リストからクイズリストを作る
	static UtaList createQuiz(){
		UtaList utas;
		utas = new UtaList();
		int[] na = new int[3];
		Random ran = new Random();
		do{
			for(int i=0; i<3; i++){
				na[i]=ran.nextInt(100);
			}
		}while(na[0]==na[1]||na[0]==na[2]||na[1]==na[2]);
		for(int i=0; i<3; i++){
			utas.addUta(alllist.getUta(na[i]));
		}
		return utas;
	}
	//クイズを行う
	static void quiz(int n){
		UtaList que;
		Scanner in =new Scanner(System.in);
		System.out.print("出題回数は？：");
		int t=in.nextInt();
		int i=0;
		while(i<t){
			que = createQuiz();
			int ansid = que.choiceNum();
			System.out.printf("第%d問\n", i+1);
			if(n==1){
				System.out.println(que.getUta(ansid).getKaminoku()+"...");
				que.shimoPrint();
			}else{
				System.out.println(que.getUta(ansid).getKamiShimo());
                        	que.authorPrint();
			}
			System.out.print("答えは:");
			int ans=0;
			do{
				ans = in.nextInt();
				if(ans<1||ans>3){
					System.out.println("エラー");
					System.out.print("もう一回入力してください:");
				}
			}while(ans<1||ans>3);
			if(que.getUtaListId(ans-1)==ansid){
				System.out.println("正解！");
				wins++;
			}else{
				System.out.println("違います");
			}
			i++;
		}
		System.out.println(t + "問中" + wins + "問正解でした。");
		System.out.printf("正答率は%3.0f％です。\n", (double)wins/t*100);	
		System.out.printf("\n\n");
	}
	//坊主めくりを行う
	static void bozuMekuri() throws IOException{
		Scanner in=new Scanner(System.in);
		int n = 0;
		do{
			System.out.print("何人で遊びますか？：");
			n=in.nextInt();
		}while(n<2);
		int[] pCards = new int[n];
		int i;
		for(i=0; i<n; i++){
			pCards[i]=0;
		}
		System.out.println("引く際はEnterキーを押してください");
		alllist.shuffleList();
		for(i=0; i<99; i++){
			BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
			for(int j=0; j<n; j++){
				String str = c.readLine();
				if(str.equals("")){
					System.out.printf("プレイヤー%dさん:", j+1);
					alllist.printKind(i);
					System.out.println();
					if(alllist.referUta(i).getKind()==0){
						pCards[j]=0;
					}else if(alllist.referUta(i).getKind()==1){
						pCards[j]+=1;
					}else if(alllist.referUta(i).getKind()==2){
						pCards[j]+=1;
						do{
							pCards[j]+=1;
							System.out.print("姫が出たのでもう一枚:");
							i++;
							if(i>99){
								break;
							}
							alllist.printKind(i);
							System.out.println();
						}while(alllist.referUta(i).getKind()==2);
					}
				}else{
					System.out.println("エラー");
				}
			}
		}
		int max = pCards[0];
		for(i=0; i<n; i++){
			if(max<pCards[i]){
				max=pCards[i];
			}
			System.out.printf("プレイヤー%dさん%d枚\n", i+1, pCards[i]);
		}
		System.out.println("優勝は"+max+"枚の人です。");
	}			
}
