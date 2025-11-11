Use this file to record your reflection on this assignment.

- Which methods did you decide to `overload`, and why?
- What worked, what didn't, what advice would you give someone taking this course in the future?

For the House and Library classes, I chose to overload methods very similarly. I added a House constructor that doesn't take a value for `hasDiningRoom` or `hasElevator` and a Library constructor that doesn't take a value for `hasElevator`; in both overloaded constructors, these booleans are set to default values instead (a House has no dining room or elevator by default, and a Library has an elevator by default). I also chose to overload House.moveIn() and Library.checkOut() to take arrays, so that you can add multiple residents to a House or multiple titles to a Library at once.

For Cafe, I chose to overload both sellCoffee() and restock() without any arguments, which call their overridden counterparts with "default" values for each argument. In particular, I figured that since restock() is only ever called with static arguments in sellCoffee(), I might as well just make those static arguments easier to call on. In a more complex implementation of Cafe, other methods could still call on restock() with specific arguments instead.