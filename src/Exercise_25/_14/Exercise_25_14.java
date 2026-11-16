// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._14;

public class Exercise_25_14 {
	public static void main(String[] args) throws CloneNotSupportedException {

		BST<GeometricObject> tree =
			new BST<GeometricObject>(new GeometricObjectComparator());


		GeometricObject object1 = new Circle(5);
		GeometricObject object2 = new Rectangle(4, 5);
		GeometricObject object3 = new Circle(6.5);
		GeometricObject object4 = new Rectangle(2.4, 5);


		tree.insert(object1);
		tree.insert(object2);
		tree.insert(object3);
		tree.insert(object4);


		System.out.println("Create a tree of geometric objects and display it: ");
		tree.preorder();


		System.out.println("\n\nClone the tree ...");
		BST<GeometricObject> tree2 = tree.clone();
		System.out.println("Is tree #1 equal to tree #2? " + tree.equals(tree2));


		System.out.println("\nInsert a new geometric object in tree2 ...");
		tree2.insert(new Circle(4));
		System.out.println("Is tree #1 equal to tree #2? " + tree.equals(tree2));
	}
}
