	Sheffield Sunlight Calculator Project

What is it ?
------------

It is an android application that calculates the sunlight exposure
of the user through the day. 


How is it accomplished ?
------------------------

First a map of the area was created for testing. Using Blender a simple 3D map
of a university area was created.
The map can be found in the "Map" folder of the main directory of the project.

Then the GPS data from the smartphone is used to transfer the user in his relative position
on the custom map. The map's buildings are triangulated and turned into an .obj type file.

GPS data is used to calculate the position of the sun relative to the user and 
the sunlight ray dimensions are calculated.

Ray-Triangle Intersection is used to combine the data and output if the rays are directly
hitting the user.

End result has 70-80% correctness. It is not 100% correct due to multiple
different error-margins stacking. 99% correctness shou

What is the point of this ? 
---------------------------

What is implemented here is just the foundation for a more complex application.
The next stage should use google 3D maps and display 2D google maps for the city of the user.
With input from the user with their personal data: age, skin colour, clothing and etc. will be used
to calculate an optimal path through the city for the minimum needed sunlight exposure.
Sunlight is needed for the skin to produce vitamin D,lack of which is connected with multiple health issue.
The reverse should also be possible, as in a route with the least exposure, for people with certain skin conditions. 


How to run it ?
---------------

Android studio.


There seems to be a problem ?
-----------------------------

One main issue is importing the vectmath library which is not part of the standard Android package.
