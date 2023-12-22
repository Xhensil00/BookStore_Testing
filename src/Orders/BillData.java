package Orders;

import javax.swing.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BillData {
    ArrayList<BuyOrders> buys=new ArrayList<>();
    private static boolean testing= false;
    ArrayList<PurchaseOrders> purchases=new ArrayList<>();
    File purchasefile=new File("PurchaseBillData.dat");
    File buyfile=new File("BuysBillData.dat");
    public BillData (){
       if(!testing){
           readPurchaseBillsData();
           readBuyBillsData();
       }
    }
    public void readPurchaseBillsData()  {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(purchasefile))) {
			PurchaseOrders p;
            for(int i=0;i<10000;i++){
                System.out.println("tits");
                p=(PurchaseOrders)reader.readObject();
                
                purchases.add(p);
            }
		} catch (EOFException e) {
			System.out.println("Read all the bills from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from bill file");
		}
    }
    public ArrayList<PurchaseOrders> getPurchases(){
        return this.purchases;
    }
    public ArrayList<PurchaseOrders> getBuys(){
        return this.purchases;
    }

    public void readBuyBillsData()  {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(buyfile))) {
			BuyOrders p;
            for(int i=0;i<10000;i++){
                p=(BuyOrders)reader.readObject();
                buys.add(p);
            }
		} catch (EOFException e) {
			System.out.println("Read all the bills from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from bill file");
		}
    }
    public static void setTestingTrue(){testing= true;}
    public static void setTestingFalse(){testing= false;}
    public void setBuysFile(File file){
        this.buyfile=file;
    }
    public void setPurchasesFile(File file){
        this.purchasefile=file;
    }
}
