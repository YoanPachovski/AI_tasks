import random
import math
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm
from statistics import mean


class KMeans:
    def __init__(self, data_x, data_y, number_of_centroids):
        self.data_x = data_x  # data x
        self.data_y = data_y  # data y
        self.number_of_centroids = number_of_centroids
        self.data_number = len(data_x)
        self.centroids = []
        self.clusters = {}
        self.number_of_clusterizations = 1

        self.__init_centroid()
        self.__init_clusters()
        self.__clusterize()

    def __init_centroid(self):
        for _ in range(self.number_of_centroids):
            while True:
                index = random.randint(0, self.data_number - 1)
                centroid = (self.data_x[index], self.data_y[index])
                if centroid not in self.centroids:
                    self.centroids.append(centroid)
                    break

    def __init_clusters(self):
        for cluster_number in range(self.number_of_centroids):
            self.clusters[cluster_number] = ([], [])

    def __clusterize(self):
        for x, y in zip(self.data_x, self.data_y):
            min_index = 0
            curr_min_distance = float("inf")
            for i in range(len(self.centroids)):
                distance = math.sqrt((self.centroids[i][0] - x) ** 2 + (self.centroids[i][1] - y) ** 2)
                if distance < curr_min_distance:
                    curr_min_distance = distance
                    min_index = i
            self.clusters[min_index][0].append(x)
            self.clusters[min_index][1].append(y)

    def __show(self):
        for cluster in self.clusters.values():
            plt.scatter(cluster[0], cluster[1])
        plt.show()

    def __calculate_new_centroids(self):
        new_centroids = []
        for cluster in self.clusters.values():
            new_centroids.append((sum(cluster[0]) / len(cluster[0]), sum(cluster[1]) / len(cluster[1])))
        return new_centroids

    def __print_centroid_locations(self):
        for centroid in self.centroids:
            print('Centroid coordinates : ({}, {})'.format(centroid[0], centroid[1]))

    def kmeans(self):
        new_centroids = self.__calculate_new_centroids()

        while self.centroids != new_centroids:
            print("Cluster #{}".format(self.number_of_clusterizations))
            self.centroids = new_centroids
            self.__init_clusters()
            self.__clusterize()
            self.number_of_clusterizations += 1
            new_centroids = self.__calculate_new_centroids()
        self.__show()
