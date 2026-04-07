import matplotlib.pyplot as plt
import os
import random

def plot_scatter(values, x_axis, y_axis, title):
    if len(values) == 0:
        raise ValueError("values can't be empty")

    x = list(range(len(values)))
    y = values

    plt.figure()
    plt.scatter(x, y)
    plt.xlabel(x_axis)
    plt.ylabel(y_axis)
    plt.title(title)
    plt.grid()

    plt.show()

# def safeAcceleration( current_speed, target_speed ):
#     target_speed = target_speed/max_speed
#     current_speed = current_speed/max_speed
#     to_return = smoothAtEnd( current_speed, target_speed )
#     if current_speed < target_speed*0.05:
#         to_return = current_speed + target_speed*0.01
#     if to_return > target_speed:
#         to_return = target_speed
#     return to_return


def smoothAtEnd( current_speed, target_speed ):
    difference = target_speed-current_speed
    fraction = difference/20
    return fraction

current_position = 0
current_speed = 0
target_position = 20

positions = [current_position]
speeds = [current_speed]

for i in range( 200 ):
    speed = smoothAtEnd( current_position, target_position )
    current_position += speed
    if i == 100:
        target_position = 0
    positions.append( current_position )
    speeds.append( speed )


plot_scatter( speeds, "Time", "Speed", "Speed over time" )
plot_scatter( positions, "Time", "Position", "Position over time" )