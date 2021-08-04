import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * TowerGame
 */
public class TowerGame {

    Stack<Integer> rodA = new Stack<>();
    Stack<Integer> rodB = new Stack<>();
    Stack<Integer> rodC = new Stack<>();
    int call = 1;
    Stack inRod = null;
    String last = "";
    
    // Generating Rod Values -->
    public void rodValues(int xV) {
        Random rand = new Random();
        int x = xV;
        if (x < 7) {
            int value = rand.nextInt(7);
            checkValue(value, x);
        }
    }
    
    // Adding Values -->
    public void addValues(int value, int callValue, int x) {
        call = callValue;
        if (call < 4) {
            rodA.push(value);
            rodValues(x + 1);
        } else {
            rodB.push(value);
            rodValues(x + 1);
        }
    }

    // Checking Values -->
    public void checkValue(int value, int x) {

        if (rodA.empty() && rodB.empty()) {
            addValues(value, call, x);
        } else {
            int positionA = rodA.search(value);
            int positionB = rodB.search(value);

            if (positionA == -1 && positionB == -1) {
                addValues(value, call + 1, x);
            } else {
                rodValues(x);
            }
        }
    }

    // Moving Disk from Rods
    public void moveDisk ()
    {
        System.out.println("Ready To Play!");
        Scanner diskRod = new Scanner(System.in);
        System.out.print("Select Rod For Remove Disk: ");
        String disk = diskRod.next().toUpperCase();
        if (disk.equals("A"))
        {
            System.out.println("You have Selected Disk: " + disk);
            removeAdd(rodA);
        }
        if (disk.equals("B"))
        {
            System.out.println("You have Selected Disk: " + disk);
            removeAdd(rodB);
        }
        if (disk.equals("C"))
        {
            System.out.println("You have Selected Disk: " + disk);
            removeAdd(rodC); 
        }  
    }
    
    public void removeAdd (Stack <Integer> diskS)
    {
        int popDisk =  diskS.pop();
        //System.out.println(popDisk);
        System.out.println(diskS);  
        insertDisk(popDisk);        
    }

    public void insertDisk (int poped)
    {   
        Scanner insertRod = new Scanner(System.in);
        System.out.print("Rod for Insert Removed Disk: ");
        String insert = insertRod.next().toUpperCase();
        if (insert.equals("A"))
        {
            System.out.println("You have Selected Insert In: " + insert);
            inRod = rodA; 
        }
        if (insert.equals("B"))
        {
            System.out.println("You have Selected Insert In: " + insert);
            inRod = rodB;
        }
        if (insert.equals("C"))
        {
            System.out.println("You have Selected Insert In: " + insert);
            inRod = rodC; 
        }

        // Following The Rule -->
        if (inRod.empty())
        {
            inRod.push(poped);
            System.out.println(poped + " Added in " + insert + "\n" + inRod);
            nextStep();
        }
        else
        {
            Integer currRodValue = (Integer) inRod.peek();
            if (currRodValue < poped)
            {
                System.out.println("\n-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\n");
                System.out.println("Not Valid Move!\nChoose Valid Move\n");
                System.out.println("\n-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
                moveDisk();
            }
            else
            {
                inRod.push(poped);
                System.out.println(poped + " Added in " + insert + "\n" + inRod);
                nextStep();  
            }
        }
        
    }

    // Moving Forward & Checking Any Disk Full Or Not -->
    public void nextStep ()
    {
        if (6 == rodA.size() || 6 == rodB.size() || 6 == rodC.size())
        {
    
            if (6 == rodA.size())
            {
                result(rodA ,rodA.size());
            }
            if (6 == rodB.size())
            {
                result(rodB ,rodB.size());
            }
            if (6 == rodC.size())
            {
                result(rodC ,rodC.size());
            }

        }
        else
        {
            Scanner conti = new Scanner(System.in);
            System.out.print("Do you want to Continue: True Or False: ");
            boolean next = conti.nextBoolean();

            if (next)
            {
                System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
                System.out.println("Rod-A\n" + rodA + "\n");
                System.out.println("Rod-B\n" + rodB  + "\n");
                System.out.println("Rod-C\n" + rodC);
                System.out.println("\nDiagram:\nRod-A");
                rodDiagram(rodA);
                System.out.println("Rod-B");
                rodDiagram(rodB);
                System.out.println("Rod-C");
                rodDiagram(rodC);
                moveDisk();
            }
            else
            {  
                if (6 == rodA.size())
                {
                    result(rodA ,rodA.size());
                }
                if (6 == rodB.size())
                {
                    result(rodB ,rodB.size());
                }
                if (6 == rodC.size())
                {
                    result(rodC ,rodC.size());
                }
            }
       }
    }

    // Checking Win Or Lose -->
    public void result (Stack <Integer> fDisk,int size)
    {
        int loo = 1;
        System.out.println("\nTower Diagram:");
        rodDiagram(fDisk);
        while (loo < size)
        {
            int smaller = fDisk.peek();
            fDisk.pop();
            int bigger = fDisk.peek();

            if (smaller > bigger)
            {
                System.out.println("You Lost!");
                return;
            }
            loo++;
        }
        System.out.println("You Won!");
    }
    
    public String diagram (int dia)
    {
        String zero = "       |";
        String first = "      [-]";
        String second = "     [-:-]";
        String third = "    [-:-:-]"; 
        String fourth = "   [-:-:-:-]";
        String fifth = "  [-:-:-:-:-]";
        String six = " [-:-:-:-:-:-]";
        
        switch (dia) {
            case 1:
                last = first + "\n";
                return last; 
            case 2:
                last = second + "\n";
                return last;   
            case 3:
                last = third + "\n";
                return last;
            case 4:
                last = fourth + "\n";
                return last;
            case 5:
                last = fifth + "\n";
                return last;
            case 6:
                last = six + "\n";
                return last;
        
            default:
                last = zero + "\n";
                return last;
        }  
        
    }

    public void rodDiagram (Stack <Integer> fDisk)
    {
        String tempDiagram = "";
        int value = 0;
        ListIterator <Integer> listIterator = fDisk.listIterator(fDisk.size());
        
        while (listIterator.hasPrevious())
        {
             value = listIterator.previous();
             tempDiagram = tempDiagram + diagram(value);
        }

        System.out.println(tempDiagram);
    }

    public static void main(String[] args) {

        TowerGame t1 = new TowerGame();
        t1.rodValues(1);
        System.out.println("\nRod-A\n" + t1.rodA);
        System.out.println("Rod-B\n" + t1.rodB);
        System.out.println("Rod-C\n" + t1.rodC + "\n");
        t1.moveDisk();

    }
}
