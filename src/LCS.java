public class LCS
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
}
