import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AminoAcidLLTester {

    @Test                                               //I chose to pass an unsorted array to test whether or not my
    public void isSortedTest(){                         //my program could tell if it was in the incorrect order
        String rna = "ggggcaugugaugag"; //ACDEG         //my test passed
        AminoAcidLL head = new AminoAcidLL();
        head = head.createFromRNASequence(rna);
        assertFalse(head.isSorted());
    }

    @Test
    public void sortTest(){                                  //I chose to pass an unsorted array to test whether or not my
        String rna = "ggggcgugugaugag"; //GACDE//////////   //my program could sort the array in the correct order
        String sortedRNA = "gcaugugaugagggg";               //my test failed and I am not sure what part of the code is not working
        AminoAcidLL head = new AminoAcidLL();
        head = head.createFromRNASequence(rna);
        head = head.sort(head);
        AminoAcidLL sorted = new AminoAcidLL();
        sorted = sorted.createFromRNASequence(sortedRNA);
        char[] array = new char[5];
        char[] sort = new char[5];
        sort[0] = 'A';
        sort[1] = 'C';
        sort[2] = 'D';
        sort[3] = 'E';
        sort[4] = 'G';
        for (int i = 0; i < sort.length; i++){
            System.out.println(head.aminoAcid);
            head = head.next;
        }

    }

    @Test
    public void aminoAcidCompareTest(){                             //I chose to pass 2 of the exact same lists to see if my
        String rna = "gcaugugaugagggg";                             //program would return 0 when the lists were compared against
        AminoAcidLL head = new AminoAcidLL();                       //each other. My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);                     //program is not working
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(rna);
        assertEquals(0, head.aminoAcidCompare(list));
    }

    @Test
    public void codonDiffTest(){                            //I chose to pass 2 of the exact same lists to see if my
        String rna = "gcaugugaugagggg";                     //program would return 0 when the lists were compared against
        AminoAcidLL head = new AminoAcidLL();               //each other. My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);             //program is not working
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(rna);
        assertEquals(0, head.codonCompare(list));
    }

    @Test
    public void aminoAcidListTest(){                    //I chose to pass a sorted array and create a char[] to test if the
        String rna = "gcaugugaugagggg";                 //program correctly returned a char[] in the correct order
        AminoAcidLL head = new AminoAcidLL();           //My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);         //program is not working
        char[] array = new char[5];
        array[0] = 'A';
        array[1] = 'C';
        array[2] = 'D';
        array[3] = 'E';
        array[4] = 'G';
        int i = 0;
        char[] arr = new char[5];
        while (head.next != null){
            arr[i] = head.aminoAcid;
            head = head.next;
            i++;
        }
        assertArrayEquals(array, arr);
    }

    @Test
    public void isSortedTest2(){                //I wanted to pass a sorted array to make sure my program was able to correctly
        String rna = "gcguguaggagu"; //ACDEG    //identify a sorted array
        AminoAcidLL head = new AminoAcidLL();   //My test passed
        head = head.createFromRNASequence(rna);
        boolean sorted = head.isSorted();
        assertTrue(sorted);
    }

    @Test
    public void sortTest2(){                                //I wanted to pass an unsorted array to make sure the program
        String rna = "gcuuguaggagu"; //GACDE//////////      //would return a sorted array
        AminoAcidLL head = new AminoAcidLL();               //My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);             //program is not working
        head = head.sort(head);
        char[] sort = new char[5];
        sort[0] = 'A';
        sort[1] = 'C';
        sort[2] = 'D';
        sort[3] = 'E';
        sort[4] = 'G';
        assertArrayEquals(sort, head.aminoAcidList());
    }

    @Test
    public void aminoAcidCompareTest2(){                         //I wanted to pass 2 arrays of different lenghts to make sure the
        String rna = "gcaugugaugaggggacgggg";                    //program would return 0 when the lists were compared against
        AminoAcidLL head = new AminoAcidLL();                    //each other. My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);                  //program is not working
        String diffRna = "gcaugugaugagggg";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(rna);
        assertEquals(0, head.aminoAcidCompare(list));
    }

    @Test
    public void codonCompareTest2() {                            //I wanted to pass 2 arrays of different lenghts to make sure the
        String rna = "gcaugugaugaggggacgggg";                    //program would return 0 when the lists were compared against
        AminoAcidLL head = new AminoAcidLL();                    //each other. My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);                  //program is not working
        String diffRna = "gcaugugaugagggg";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(rna);
        assertEquals(0, head.codonCompare(list));
    }

    @Test
    public void aminoAcidCountsTest(){                      //I wanted to pass a list where every amino acid appears once to
        String rna = "ggggcaugugaugag"; //ACDEG             //make sure the program would return the correct array when the method
        AminoAcidLL head = new AminoAcidLL();               //was called. My test failed and I am not sure what part of the
        head = head.createFromRNASequence(rna);             //program is not working.
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++){
            array[i] = 1;
        }
        assertArrayEquals(array, head.aminoAcidCounts());
    }
}
