
public class Main
{
    public static void main(String[] args)
    {
        LCS lcs = new LCS();
        EditDistance editDistance = new EditDistance();
        String X = "ABCBDAB";
        String Y =  "BDCABA";

        lcs.findLCS(X,Y);

        X = "INTENTION";
        Y = "EXECUTION";

        System.out.println("\n" + "Distance for words " + X + " " + Y);
        editDistance.FindEditDistance(X,Y,1,1,1);
    }
}
