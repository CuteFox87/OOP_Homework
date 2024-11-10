public class SearchDemo {
	public static void main(String args[]) {
		//MySearchable s1 = new SequenceSearch();
		MySearchable s2 = new BinarySearchTree();

		for(int i=1; i<args.length; i++) {
			s2.put(Integer.parseInt(args[i]));
		}
		
		System.out.println(s2.exist(Integer.parseInt(args[0])));
	}
}