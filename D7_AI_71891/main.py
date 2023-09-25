import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm
from kmeans import KMeans


if __name__ == '__main__':
    file_name = input("Please input the name of the file: ")
    number_of_clusters = int(input("Please input the amount of clusters: "))
    file = open(file_name, 'r')
    data_x = []
    data_y = []
    for f in file:
        curr = f.split()
        data_x.append(float(curr[0]))
        data_y.append(float(curr[1]))

    kmeans = KMeans(data_x, data_y, number_of_clusters)

    kmeans.kmeans()


