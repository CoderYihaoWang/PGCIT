Web Challenge 02
==========

Part 1 - Dolphins
----------------------

You are provided the components of an image — a selection of backgrounds of the sea and sky, and of some marine mammals. You’re also provided with `part01/create_scenery.html` and `part01/create_scenery.css` that set up the placement of the images and form controls. 

You will use JavaScript event handlers to make these form controls interactive - allowing users to modify the overall composite image made up of background and dolphin/orca images.


In the `part01/create_scenery.js` file, write the JavaScript code that allows for the following:

+ The radio buttons should allow the user to select between the different scenic backgrounds. When a new radio button is selected, the displayed background should be displayed.
  - Use a similar approach as used in the ex02 gallery to identify which radio button was selected. You will find that the `id` of the selected radio button will help you determine which file in the `images/dolphins/` directory should be loaded.
+ The checkboxes should enable the user to choose which dolphin/orca images should be displayed on the selected background.
  - Again, use attribute values from the clicked element to help determine what image should be show or hidden. As a further hint:
  
  ```javascript 1.8
    /* It is perfectly valid to concatenate a value together to form an argument
     * for querySelector(). The following will select the element with id="something"
     */
    let someString = "something";
    let selected = document.querySelector("#" + someString);
    ```
+ The range slider controls should permit the user to alter the size and position of the displayed dolphin images. 
  - *HINT*: The easiest approach to this is to apply a CSS transform property containing translateX(), translateY() and scale() transforms in a single line to each of the dolphins. You can accomplish this in code by storing the values to be passed to each of these methods in variables, modifying the values of these variables when the sliders move and then calling a helper function that sets this property on each dolphin.





Part 2 - Animated Book
----------------------------------------

You're provided with a web page containing an "animated book" of animal images in the `part02/animatedbook.html` file. The solution is pure CSS, and the page turning is not interactive but animated with key-frames using different delay times. 

Preview the animated book. Be aware that it takes 5 seconds before every page 'leaf', including the first one, starts turning over.

The purpose of this exercise is to use JavaScript so that the page turning animation is triggered when the user clicks on the book instead of this happening automatically.

*HINTS*: 
+ You will first want to clear the existing animation delays.
+ Rename the `.page` CSS style definition to `.pageAnimation` and set this programmatically on the specific page that is clicked for the animation to take place. 
+ When the page flipping animation on a clicked page has ended, it fires an [`animationend` event](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/animationend_event). At that point, you will need to bring the next page up front in the stacking order (by adjusting its z-index)
