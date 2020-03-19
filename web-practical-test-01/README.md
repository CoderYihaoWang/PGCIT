# Question One (25 marks)
In this question, you will use a combination of HTML and CSS to create and style a page with text, images, and tables. A skeleton HTML file is given to you (`Question-One.html`). You will complete this file, and add your own external CSS file for this question.

The end result should look something like this:

![](./spec/question-one-complete.PNG)

### 1A) HTML elements (5 marks)
We will start by adding content to our HTML page.

1. To begin, in the page body of `Question-One.html`, add **three** paragraphs of dummy text. The text can come from a "Lorem Ipsum" website such as <http://www.catipsum.com/>.

2. Next, add a main header before the first paragraph, and a sub-heading before each of the other two paragraphs. These can contain any text you wish.

3. Now, add an image before the first paragraph, after the main heading. The image should be `200px` square, and may come from a dummy image website such as <https://placekitten.com/> (for example: <http://placekitten.com/200/200>).

4. Finally, add another main heading at the bottom of the page. Then, below that, add a table with at least three columns, a single header row, and at least three body rows. The contents of the table can be anything you like - feel free to use the given screenshots as inspiration.

When part **1A)** is complete, your page should look something like this:

![](./spec/question-one-after-step-1.PNG)

### 1B) Basic styling (5 marks)
Next, we will add some basic styling to the page. Unless otherwise mentioned, *all* CSS selectors for part B should be *element* selectors - they should not refer to any `class` or `id` attributes.

1. First, create a new CSS file in the `question1` folder, called `Question-One.css`. Link to this file from within `Question-One.html`. All CSS for this question should be written in this file.

2. Style the page body to have a background color of `gainsboro`.
 
3. Surround all page content in a single `<div>`. Give that div an `id` of your choice. Then, style that div (referenced by its id) so that it:
   - Takes up `60%` of the available width
   - Has `auto` margins on the left and right
   - Has a `whitesmoke` background color
   - Has `20px` of padding on all sides
   - Has a `5px` by `5px`, slightly blurred, `lightslategray` box shadow.
   
4. Style all paragraphs so that their text alignment is justified.

When part **1B)** is complete, your page should look something like this:

![](./spec/question-one-after-step-2.PNG)

### 1C) Intermediate styling (8 marks)
We will continue to beautify our page in this step.

1. Define a custom font face pointing to the `Catamaran-Regular.ttf` font which is located in the `assets` folder.

2. Style all headings, paragraphs, and table cells on the page to have that font (or `sans-serif` font if the font file can't be loaded for any reason).

3. Add a `class` to the image on the page. Style all elements with that class so that they:
   - Are floated to the left
   - Have `2px` of padding on all sides
   - Have a dotted, `1px` wide, `lightslategray` border
   - Have a `10px` margin on the right
   
When part **1C)** is complete, your page should look something like this:

![](./spec/question-one-after-step-3.PNG)

### 1D) Advanced styling (7 marks)
We will complete the development of our page by styling the table so it looks like the one shown in the screenshot at the start of this section. For part 1D), you **may not** add any `class` or `id` attributes to the table or any of its children.

The table and its contents should be styled as follows:

1. All table cells should have centered text and should have `5px` of padding on the top and bottom.

2. The table should take up the full available width, and there should be no space between cells in the table (**Hint:** Investigate the `border-collapse` property).

3. The table header should have an `oldlace` background color.

4. Every second row in the table body should have a `gainsboro` background color. **Hint:** you should look at how to style odd and/or even rows in tables

5. The cells in the first row of the table header should have a `1px` solid black top border. The cells in the last row of the table header should have a similar bottom border.

6. Similarly, the cells in the last row of the table body should have a `1px` solid black bottom border.

**Hint:** Styling `<tr>` elements with a border will have no effect. You must style the cells themselves.

When steps **1A)** through **1D)** are complete, your completed webpage should look something like this:

![](./spec/question-one-complete.PNG)



# Question Two (25 marks)
In this question you will use a variety of techniques in HTML and CSS to add images and complete the formatting of an HTML page. 

Some of the HTML and CSS has been added already; there are some images in the `question2/assets` folder. You should familiarise yourself with the structure of the project and the existing code.  

The end result should look something like this: 
![](./spec/oranges_webpage_complete.PNG)

### 2A) Main image and navbar (10 marks)
In this step, you will begin to create the main image, and right-align the navbar.

1. Add an image element that will display the `background_field.jpg` image within the main content div. The image (along with other images required for Question Two) is located in the `question2/assets` folder.

2. Use CSS to make the links (`<a>` elements) in the nav bar appear on the right of the nav bar and have 30 pixels of space to the left and right of each link. **Hint:** You can do this by aligning text.

When part **2A)** is complete, your page should look something like this:

![](./spec/oranges_step_one.PNG) 

### 2B) Positioning orange trees (10 marks)
In this step, you will add three smaller trees to the main image.

1. Add three image elements using the `tree.png` image file that will display in front of the background image.

2. Position the images in CSS using appropriate CSS position settings. The trees should not move relative to the background image when the screen size changes. Specifically:
   - The largest tree image should be the default size of the image
   - The smaller tree images should be `180px` wide
   - The larger tree image should be `200px` from the left and `70px` from the bottom
   - The smaller tree on the left should be `130px` from the left and `150px` from the bottom
   - The smaller tree on the right should be `420px` from the left and `200px` from the bottom
   - The larger tree should display in front of the two smaller trees.

   **Hint:** You may add classes or ids to some HTML elements to be able to access them more easily within your CSS.

When part **2B)** is complete, your page should look something like this:

![](./spec/background_trees.png)

### 2C) Positioning the logo (5 marks)
In this step, we will add an additional heading to the page. 

1. Add an `<h1>` element to the main content div that will have the text "Lovely Orange Trees".
   - The font should be orange
   - The text should be horizontally centered within the main div
   - The text should overlap with the orange trees and display in front of them
   - The font of the h1 should be roughly four times larger than a normal h1 is by default.

When parts **2A)** through **2C)** are complete, your completed page should look something like this:

![](./spec/oranges_webpage_complete.PNG)

You should test your page to check that content does not move out of alignment on different screen sizes.