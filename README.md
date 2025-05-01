# Android Inventory App
### Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

The goal of my app was to create a lightweight inventory management tool that would allow users to keep track of items, stock levels, and basic details. It was designed to be simple enough for individuals and small business owners who needed a quick way to add, edit, or remove inventory without needing an internet connection. The user need it was built around was making inventory management easy, fast, and available offline with minimal setup.

### What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The app needed a login and registration screen, an inventory screen that showed all the items in a grid layout, and an edit screen that could dynamically add, edit, or delete an item. It also had a separate screen for handling SMS permission settings. I kept users in mind by making sure every screen was minimal and functional. I avoided any extra clutter that would slow someone down when using the app for work. I focused on organization, clear labels, easy navigation, and making sure all inputs were accessible even when the keyboard popped up. I think the designs were successful because everything was focused on getting users where they needed to go as quickly as possible.

### How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?

I broke the app down into small sections and worked screen by screen. Each screen had smaller tasks that I focused on one at a time instead of trying to finish everything at once. I always tried to get the basic functionality working first before worrying about small details. If something did not work, I would test small parts separately until I figured it out. In the future, I can use this same approach of breaking projects into smaller steps because it helped me stay organized and not get overwhelmed.

### How did you test to ensure your code was functional? Why is this process important, and what did it reveal?

I tested the app by using both the emulator and a physical Android device. Testing on a real device helped me catch problems I would not have seen otherwise, like the keyboard blocking fields when trying to type in landscape mode. After every major change, I made sure to run the app, test each feature, and see how the screens responded. Testing often is important because it catches bugs early and saves a lot of rework later. It showed me how even small changes can break things if you are not careful.

### Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?

One of the biggest challenges was making the edit item screen work for both adding and editing items without creating two separate screens. I figured out I could pass a flag through the intent and change the screen behavior dynamically based on that flag. It helped keep the app simpler and avoid repeating code. Another small thing was getting the database to refresh when a user returned to the inventory screen, which I solved by using onResume() to reload the data every time.

### In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

I think the CRUD functionality for the inventory items was where I really showed my skills. Setting up the database access, writing the DAO, and making sure adding, editing, and deleting worked correctly was a big part of the app. It tied together the UI with the database and made sure the app was actually usable in a real-world situation. That part of the project showed not just that I could code screens, but that I understood how everything had to work together behind the scenes.
