package Hangman;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HangManGame {
	static int hak=0;
	public static void main(String[] args) {
		//Yaratma iþlemleri
		JFrame anaEkran = new JFrame();
		anaEkran.setContentPane(new JLabel(new ImageIcon("imgs/Tum.png")));
		anaEkran.setSize(416,317);
		anaEkran.setLocation(470, 100);
		anaEkran.setVisible(true);
		anaEkran.setTitle("Adam Asmaca");
		ImageIcon p1 = new ImageIcon("imgs/p1.png");
		ImageIcon p2 = new ImageIcon("imgs/p2.png");
		ImageIcon p3 = new ImageIcon("imgs/p3.png");
		ImageIcon p4 = new ImageIcon("imgs/p4.png");
		JLabel[] picBox = new JLabel[6];
		picBox[0]= new JLabel();
		picBox[0].setIcon(p4);
		picBox[0].setSize(34,33);
		picBox[0].setLocation(13,25);
		picBox[0].setVisible(false);
		anaEkran.add(picBox[0]);
		picBox[1]= new JLabel();
		picBox[1].setIcon(p3);
		picBox[1].setSize(66,13);
		picBox[1].setLocation(0,56);
		picBox[1].setVisible(false);
		anaEkran.add(picBox[1]);
		picBox[2]= new JLabel();
		picBox[2].setIcon(p2);
		picBox[2].setSize(5,29);
		picBox[2].setLocation(27,69);
		picBox[2].setVisible(false);
		anaEkran.add(picBox[2]);
		picBox[3]= new JLabel();
		picBox[3].setIcon(p1);
		picBox[3].setSize(36,37);
		picBox[3].setLocation(10,98);
		picBox[3].setVisible(false);
		anaEkran.add(picBox[3]);
		anaEkran.repaint();
		
		String[] Kelime = new String[] {
				"FRANSA","RUSYA","HOLLANDA","KANADA","KAMERUN",
				"ERZURUM","ZONGULDAK","ANTALYA","HATAY","KIRIKKALE",
				"KANGURU","PANDA","PENGUEN","ZEBRA","AKREP",
				"PORTAKAL","KARADUT","GREYFURT","AHUDUDU","AVOKADO"
		};
		Random r=new Random();
		int random = r.nextInt(20);
		//Random üretilen sayýya göre kelime seçimi.
		JLabel kategori = new JLabel();
		kategori.setLocation(250,50);
		kategori.setVisible(true);
		if(random<5) {
			kategori.setText("ÜLKE");
			kategori.setSize(120,30);
		}
		else if(random<10) {
			kategori.setText("ÞEHÝR");
			kategori.setSize(150,30);
		}
		else if(random<15) {
			kategori.setText("HAYVAN");
			kategori.setSize(180,30);
		}
		else {
			kategori.setText("MEYVE");
			kategori.setSize(150,30);
		}
		anaEkran.add(kategori);
		anaEkran.repaint();
		JLabel[] kelime = new JLabel[Kelime[random].length()];
		int x_kor=200,y_kor=100;
		for (int i = 0; i < Kelime[random].length(); i++) 
		{
			kelime[i]= new JLabel("_");
			kelime[i].setLocation(x_kor,y_kor);
			kelime[i].setSize(15,15);
			kelime[i].setVisible(true);
			anaEkran.add(kelime[i]);
			anaEkran.repaint();
			x_kor+=20;
		}
		while(hak<4) {
			anaEkran.repaint();
			oyun(Kelime,kelime,random,picBox);
			if(hak>3) 
			{
				JOptionPane.showMessageDialog(null, "Malesef Kaybettiniz...");
				anaEkran.dispose();
			}
			kazanma(kelime,anaEkran);
		}
	}
	
	public static void oyun(String[] Kelime,JLabel[] kelime,int random,JLabel[] picBox)
	{
		boolean kontrol = false;	
		
		String giris=JOptionPane.showInputDialog("Harf giriniz.").toUpperCase();
		char veri = giris.charAt(0);
		//Klavyeden girilen karakter "veri" deðiþkenine atanýr.
		
		char[] harf = Kelime[random].toCharArray(); 
		//Kelime dizimizden gelen random kelime harf adlý diziye atanýr.
		//Her bir harf bir indiste olacak þekilde.
		
		for (int i = 0; i < Kelime[random].length(); i++)  //Tahmin edilen harf kelimede var mý?
		{
			if(harf[i] == veri) 
			{
				kontrol = true;
				kelime[i].setText(Character.toString(veri));
			}
		}
		
		
		
		if(!kontrol)	//Hata yapýldýðýnda adamý çizme eventi.
		{
			picBox[hak].setVisible(true);
			hak+=1;
		}
	}
	
	public static void kazanma(JLabel[] kelime,JFrame anaEkran) {
		boolean kont = true;
		for (int i = 0; i < kelime.length; i++) {
			//Kelimede bulunamayan harf varsa kont deðiþkeni false olur
			if(kelime[i].getText()=="_") {
				kont=false;
				break;
			}
		}
		if(kont) 
		{	//Kelimede bulunamayan harf kalmadýysa oyun kazanýlmýþtýr.
			JOptionPane.showMessageDialog(null, "Tebrikler Kazandýnýz...");
			anaEkran.dispose();
			hak=4;
		}
	}
}