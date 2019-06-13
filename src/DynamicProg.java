
public class DynamicProg
{

    void findLCS(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();
        int [][]c = new int[m+1][n+1];
        String [][]b = new String[m+1][n+1];

        for (int i = 0; i <= m; i++)
            c[i][0] = 0;

        for (int j = 0; j <= n; j++)
            c[0][j] = 0;

        for(int i=1; i <= m; i++)
        {
            for(int j=1; j <= n; j++)
            {
                if(X.charAt(i-1) == Y.charAt(j-1))
                {
                    c[i][j]= 1 + c[i-1][j-1];
                    b[i][j] = "UPLEFT";
                }

                else
                {
                    if (c[i-1][j] >= c[i][j-1])
                    {
                        c[i][j] = c[i-1][j];
                        b[i][j] = "UP";
                    }

                    else
                    {
                        c[i][j] = c[i][j-1];
                        b[i][j] = "LEFT";
                    }
                }
            }
        }

        System.out.println("Length of LCS is " + c[m][n]);
        System.out.print("Substring: ");
        printLCS(b,X,m,n);
        System.out.println();
    }


    void printLCS(String [][]b, String X, int i, int j)
    {
        if(i == 0 || j == 0)
            return;

        if(b[i][j] == "UPLEFT")
        {
            printLCS(b,X,i-1,j-1);
            System.out.print(X.charAt(i-1));
        }

        else if(b[i][j] == "UP")
                printLCS(b,X,i-1,j);
            else
                printLCS(b,X,i,j-1);
    }

    void editDistance(String X,String Y, int insertion, int deletion, int replacement)
    {
        int m = X.length();
        int n = Y.length();
        int [][]c = new int[m+1][n+1];
        String [][]b = new String[m+1][n+1];

        for(int i = 0; i <= m; i++)
        {
            c[i][0] = i * deletion;
            b[i][0] = "UP";
        }

        for(int j = 0; j <= n; j++)
        {
            c[0][j] = j * insertion;
            b[0][j] = "LEFT";
        }

        for(int i = 1; i <= m; i++)
        {
            for(int j =1; j <= n; j++)
            {
                insertion = c[i][j-1] + 1;
                deletion = c[i-1][j] + 1;
                replacement = c[i-1][j-1] + (X.charAt(i-1) == Y.charAt(j-1) ? 0 : 1);

                c[i][j] = insertion;
                b[i][j] = "LEFT";

                if(c[i][j] > deletion)
                {
                    c[i][j] = deletion;
                    b[i][j] = "UP";
                }

                if(c[i][j] > replacement)
                {
                    c[i][j] = replacement;
                    b[i][j] = "UPLEFT";
                }
            }
        }

        System.out.println("Distance is equal to " + c[m][n]);
        printDistance(b,X,Y,m,n);
    }

    void printDistance(String [][]b, String X, String Y, int i, int j)
    {
        if(i == 0 || j == 0)
            return;

        if(b[i][j] == "UPLEFT")
        {
            printDistance(b,X,Y,i-1,j-1);
            if(X.charAt(i-1) != Y.charAt(j-1))
            System.out.print(" SUB(" + X.charAt(i-1) + "," + Y.charAt(j-1) + ")");
            else
                System.out.print(X.charAt(i-1));
        }

        else if(b[i][j] == "UP")
        {
            printDistance(b,X,Y,i-1,j);
            System.out.print(" DEL(" + X.charAt(i-1) + ")");
        }

        else
        {
            printDistance(b,X,Y,i,j-1);
            System.out.print(" INS(" + Y.charAt(j-1) + ")");
        }
    }

    public static void main(String[] args)
    {
        DynamicProg dynamicProg = new DynamicProg();
        String X = "ABCBDAB";
        String Y =  "BDCABA";

        dynamicProg.findLCS(X,Y);

         X = "INTENTION";
         Y = "EXECUTION";

        System.out.println("\n" + "Distance for words " + X + " " + Y);
        dynamicProg.editDistance(X,Y,1,1,1);
    }
}
