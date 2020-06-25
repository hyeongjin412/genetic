import numpy as np
from matplotlib import pyplot as plt

data = np.array([[1,5],
                [1,8],
                [2,7],
                [2,6],
                [3,9],
                [3,11],
                [4,14],
                [4,17],
                [5,16],
                [5,19],
                [6,18],
                [6,24],
                [7,21],
                [7,25],
                [8,23],
                [8,27],
                [9,26],
                [9,30],
                [10,29],
                [10,31]])

x = [-3,-2-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35]
y = [(3*number+2)for number in x]
plt.plot(data[:,0], data[:,1],"ro",label = "Data")
plt.plot(x,y,label = "measured")

plt.legend()

plt.title("Linear regression analysis")
plt.xlabel("x")
plt.ylabel("y")
plt.axis([0,13,0,35])
plt.show()                    
