#include <stdio.h>
#include <stdlib.h>

int main()
{
    int C, N;
    int *score = NULL;
    int i, j;
    int sum, count;
    double avg;
    
    scanf("%d", &C);
    
    for (i = 1; i <= C; i++)
    {
        count = 0;
        
        sum = 0;
        
        scanf("%d", &N);
        
        score = (int *) malloc (N * sizeof(int));
        
        for (j = 0; j < N; j++)
        {
            scanf("%d", &score[j]);
            sum += score[j];
        }
        
        avg = (double)sum / N;
        
        for (j = 0; j < N; j++)
        {
            if (score[j] > avg)
            {
                count++;
            }
        }
        
        printf("%.3lf%\n", ((double)count / N) * 100);
    }
 
    free(score);
    
    return 0;
}