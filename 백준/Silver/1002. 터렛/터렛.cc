#include <iostream>
#include <cmath>

using namespace std;

int main()
{
	int x1, x2, y1, y2, r1, r2;
	int N;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		//scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);

		cin >> x1 >> y1 >> r1 >> x2 >> y2 >> r2;

		double d = sqrt(pow(x2 - x1,2) + pow(y2 - y1, 2));

		if ((double)(r1 + r2) > d && fabs((double)(r1 - r2)) < d)
			cout << "2" << endl;
		else if ((double)(r1 + r2) < d || fabs((double)(r1 - r2)) > d || (d == 0 && r1 != r2))
			cout << "0" << endl;
		else if (d == 0 && r1 == r2)
			cout << "-1" << endl;
		else
			cout << "1" << endl;
	}
}