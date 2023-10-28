package p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import p1.Transaction;

public class Start {
  int count = 0;
  List<String> FileRecords;

  public void StartBank() {

    Scanner input = new Scanner(System.in);
    char mainYesOrNo = 'Y';

    while (mainYesOrNo == 'Y') {

      System.out.print("\t\t\t\t\t   ______________________________________\n\n");
      System.out.print("\t\t\t\t\t   |  Welcome To Bank Managment System  |\n\n");
      System.out.print("\t\t\t\t\t   |____________________________________|\n\n");

      System.out.print("\n\t\t\tEnter your choice : \n\n");
      System.out.print("\t\t\t\t\t1 : Start\n");
      System.out.print("\t\t\t\t\t2 : Exit\n\n");
      System.out.println("\t\t\t\tYou Select : ");
      int switchChoice = input.nextInt();
      switch (switchChoice) {

        case 1: {
          start();
          break;
        }
        case 2: {
          System.exit(0);
        }
        default: {
          System.out.println("\t\t\t\t\tInvalid Selection");
          break;
        }
      }
    }
  }

  private List<String> readFile(String filename) {
    List<String> records = new ArrayList<String>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null) {
        records.add(line);
      }
      reader.close();
      return records;
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read '%s'.", filename);
      e.printStackTrace();
      return null;
    }
  }

  private void start() {
    String choice, ch, operation;
    Transaction transac = new Transaction();
    Scanner sc = new Scanner(System.in);
    double amount;

    long accountNo = 1;

    do {

      System.out.print("\t\t\tEnter your choice : \n\n");
      System.out.print("\t\t\t\t\t1 : New account\n\n");
      System.out.print("\t\t\t\t\t2 : Transaction\n\n");
      System.out.print("\t\t\t\t\t3 : View Account Information\n\n");
      System.out.print("\t\t\t\t\t4 : Exit\n\n");
      System.out.println("\t\t\t\tYour choice : ");
      choice = sc.next();
      switch (choice) {
        case "1":
          double openingBalance;
          int pass;
          int userId = (int) (findMaxId2() + 1);
          System.out.println("\t\t\t\tYour Account Number :" + (userId));
          System.out.println("\t\t\t\tEnter the Password :");
          pass = sc.nextInt();
          System.out.println("\t\t\t\tEnter the opening balance :");
          openingBalance = sc.nextDouble();
          transac.transaction(accountNo, "Opening", pass, openingBalance);
          accountNo = accountNo + 1;
          break;
        case "2":
          System.out.print("\t\t\tEnter your choice : \n\n");
          System.out.print("\t\t\t\t\ta : Deposit\n\n");
          System.out.print("\t\t\t\t\tb : Withdraw\n\n");
          ch = sc.next();
          if (ch.equalsIgnoreCase("a"))
            operation = "Deposit";
          else if (ch.equalsIgnoreCase("b"))
            operation = "Withdraw";
          else {
            operation = "Invalid option";
          }
          System.out.println("\t\t\t\tAccount Number:");
          accountNo = sc.nextLong();
          System.out.println("\t\t\t\tEnter the Password :");
          pass = sc.nextInt();
          validation(accountNo, pass);
          System.out.println("\t\t\t\tAmount:");
          amount = sc.nextDouble();
          transac.transaction(accountNo, operation, pass, amount);
          break;
        case "3":

          System.out.println("\t\t\t\tAccount Number:");
          accountNo = sc.nextLong();
          System.out.println("\t\t\t\tEnter the Password :");
          pass = sc.nextInt();
          validation(accountNo, pass);
          operation = "showInfo";
          transac.transaction(accountNo, operation, pass, 0);
          break;
        case "4":
          System.out.println("\t\t\t\t\tThank you!");
          System.exit(0);
          break;
        default:
          System.out.print("\t\t\t\t\tYou Select some thin wrong\n");
      }
    } while (choice != "4");
    sc.close();
  }

  private int findMaxId2() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"));
      int count = 0;
      while ((reader.readLine()) != null) {
        count = count + 1;
      }
      reader.close();
      // Logic for finding maximum Id
      return count / 3;
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read '%s'.", "MyFile.txt");
      e.printStackTrace();
    }
    return 0;
  }

  void validation(long accno, int pass) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"));
      String line;
      String trmpaccountNum = Long.toString(accno);
      String temp = Integer.toString(pass);
      int count = 1;
      while ((line = reader.readLine()) != null) {
        // my logic for displaying 2 more lines after found
        if (count > 1) {
          trmpaccountNum = line;
        }
        if ((line.equals(trmpaccountNum)) && (count < 5)) {
          line = reader.readLine();
          if ((line.equals(temp))) {
            System.out.println("Correct Pssword");
          } else {
            System.out.println("Incorrect Password");
            System.exit(0);
          }
        }
      }

      count = count + 1;

      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read '%s'.", "MyFile.txt");
      e.printStackTrace();

    }
  }
}
