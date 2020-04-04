class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/

  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid((aminoAcid));
    counts = incrCodons(inCodon);
    next = null;
  }

  public int[] incrCodons(String codon){  //updates the counts for a specific codon//
    for (int i = 0; i < codons.length; i++){
      if (codons[i].equals(codon)){
        counts[i] += 1;
      }
    }

    return counts;
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)){  //checks if this amino acid already exists//
      // find codon in list of codon and incrememnet counter//
      incrCodons(inCodon);//I want to stop at the amino acid that this codon belongs to//
    }
    else if (next != null){   //moves on to check next node//
      next.addCodon(inCodon);
    }
    else{ //the amino Acid does not exist//
      next = new AminoAcidLL(inCodon);
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int sum = 0;
    for (int i = 0; i < codons.length; i++){
      sum += counts[i];
    }
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    //calculate differences in the number of codons//
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){   //NEEDS TO BE CHECKED///////////////////////////////
    if(inList == null && this == null){
      return 0;
    }
    if (inList == null){
      return totalCount() + next.aminoAcidCompare(inList);
    }
    else if (this == null){
      return inList.totalCount() + aminoAcidCompare(inList.next);
    }
    if (aminoAcid == inList.aminoAcid){
      return ((totalCount() - inList.totalCount()) + next.aminoAcidCompare(inList.next));
    }
    else if (inList.aminoAcid > aminoAcid){
      return totalCount() + next.aminoAcidCompare(inList);
    }
    else {
      return inList.totalCount() + aminoAcidCompare(inList.next);
    }


    //find the difference in the number of counts in the two amino acids//
    //base case if the list is empty
    //base case if the node is null
    //base case if they have the same counts
    //base case if they dont have the same count which is greater and what is the difference
  }

  /********************************************************************************************/
  /* Same as above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){    //NEEDS TO BE CHECKED//////////////
    if(inList == null && this == null){
      return 0;
    }
    if (inList == null){
      return totalCount() + next.codonCompare(inList);
    }
    else if (this == null){
      return inList.totalCount() + codonCompare(inList.next);
    }
    if (aminoAcid == inList.aminoAcid){
      return (codonDiff(inList)) + next.codonCompare(inList.next);
    }
    else if (inList.aminoAcid > aminoAcid){
      return totalCount() + next.codonCompare(inList);
    }
    else{
      return inList.totalCount() + codonCompare(inList.next);
    }
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if (next == null){      ///Needs to be checked///////////////
      return new char[] {aminoAcid};
    }
    else{
      char[] array = next.aminoAcidList();
      char[] arr = new char[array.length + 1];
      arr[0] = aminoAcid;
      for (int i = 1; i < array.length; i++){
        arr[i] = array[i - 1];
      }
      return arr;
    }

    //gets all the amino acids and puts them in the array
    //base case if next == null
    //
    //

  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    if (next == null){      //NEEDS TO BE CHECKED////////////////
      return new int[] {totalCount()};
    }
    else{
      int[] array = next.aminoAcidCounts();
      int[] arr = new int[array.length + 1];
      arr[0] = totalCount();
      for (int i = 1; i < array.length; i++){
        arr[i] = array[i - 1];
      }
      return arr;
    }
    //totals the count of all of the nodes//
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if (next == null){    //NEEDS TO BE CHECKED///////////////////
      return true;
    }
    else if(aminoAcid < next.aminoAcid){
      return next.isSorted();
    }
    else{
      return false;
    }

  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){ //NEEDS TO BE CHECKED////////
    AminoAcidLL list = new AminoAcidLL();
    while (!(inSequence.equals(""))){
      if (inSequence.length() <= 3){
        list.addCodon(inSequence);
        inSequence = "";
      }
      else{
        list.addCodon(inSequence.substring(0, 3));
        inSequence = inSequence.substring(3);
      }
    }
    return list;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){   //CONFUSED/////////
    AminoAcidLL head = new AminoAcidLL();
    head = inList;
    AminoAcidLL iter = inList;

    AminoAcidLL smallest = inList;
    while (inList != null) {
      while (iter != null) {
        if (inList.aminoAcid > iter.aminoAcid) {
          AminoAcidLL oldNext = inList.next;
          inList.next = iter.next;
          iter.next = oldNext;
        }
        iter = iter.next;
      }
      inList = inList.next;
      iter = inList;
    }
    return head;

    //sorts using any sorting algorithm we like//
  }
}